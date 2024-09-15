import { Component, OnDestroy, OnInit, signal, effect } from '@angular/core';
import { Message } from '../../domain/Message';
import { ChatMessageInputComponent } from '../shared/message-input/message-input.component';
import { Subscription, take } from 'rxjs';
import { RxStompService, rxStompServiceFactory } from '../rx-stomp.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ChatMessageInputComponent, CommonModule],
  providers: [
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
    },
  ],
  templateUrl: './employee-chat.component.html',
  styleUrl: './employee-chat.component.scss',
})
export class EmployeeChatComponent implements OnInit, OnDestroy {
  chats: Record<string, Message[]> = {};
  selectedConversationID: string | null = null;
  private destroy$ = new Subscription();
  private sessionID = signal<string | null>(null);

  constructor(private rxStompService: RxStompService) {
    effect(() => {
      if (this.sessionID()) {
        this.destroy$ = this.rxStompService
          .watch(`/queue/messages/${this.sessionID()}`)
          .subscribe((message) => {
            const { conversationID, content } = JSON.parse(message.body);
            this.chats[conversationID] = [
              ...(this.chats[conversationID] || []),
              { content, role: 'customer' },
            ];
          });
      }
    });
  }

  ngOnInit(): void {
    this.rxStompService.publish({
      destination: '/chat/join_session',
      body: JSON.stringify({
        type: 'Employee',
      }),
    });

    this.rxStompService
      .watch('/topic/login')
      .pipe(take(1))
      .subscribe((message) => {
        this.sessionID.set(message.body);
      });
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
    this.rxStompService.deactivate();
  }

  onConversationSelected(conversationID: string): void {
    this.selectedConversationID = conversationID;
  }

  onSendMessage(content: string): void {
    if (!this.selectedConversationID) {
      return;
    }

    this.chats[this.selectedConversationID].push({
      content,
      role: 'employee',
    });

    this.rxStompService.publish({
      destination: `/chat/employee_message/${this.selectedConversationID}`,
      body: JSON.stringify({
        content,
      }),
    });
  }

  getChatKeys(): string[] {
    return Object.keys(this.chats);
  }

  getChatMessages(): Message[] {
    if (!this.selectedConversationID) {
      return [];
    }

    return this.chats[this.selectedConversationID];
  }
}
