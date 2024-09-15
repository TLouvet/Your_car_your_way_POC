import { Component, OnDestroy, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Message } from '../../domain/Message';
import { ChatMessageInputComponent } from '../shared/message-input/message-input.component';

import { CustomerSocketService } from './customer-chat.service';

@Component({
  selector: 'app-customer-chat',
  standalone: true,
  imports: [ReactiveFormsModule, ChatMessageInputComponent],
  templateUrl: './customer-chat.component.html',
  styleUrl: './customer-chat.component.scss',
})
export class CustomerChatComponent implements OnInit, OnDestroy {
  messages: Message[] = [];

  constructor(private socketService: CustomerSocketService) {}

  ngOnInit(): void {
    this.socketService.joinSession();
    this.socketService.getMessages().subscribe((message) => {
      this.messages.push(message);
    });
  }

  ngOnDestroy(): void {
    this.socketService.deactivate();
  }

  onSendMessage(content: string): void {
    try {
      this.socketService.sendMessage(content);
      this.messages.push({
        content,
        role: 'customer',
      });
    } catch (error) {
      console.error('Failed to send message:', error);
    }
  }
}
