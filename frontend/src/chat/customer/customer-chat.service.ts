import { effect, Injectable, signal } from '@angular/core';
import { Observable, Subject, Subscription, take } from 'rxjs';
import { RxStompService } from '../rx-stomp.service';

@Injectable({
  providedIn: 'root',
})
export class CustomerSocketService {
  private sessionID = signal<string | null>(null);
  private destroy$ = new Subscription();
  private messagesSubject = new Subject<any>();

  constructor(private rxStompService: RxStompService) {
    effect(() => {
      if (this.sessionID()) {
        this.destroy$ = this.rxStompService
          .watch(`/queue/messages/${this.sessionID()}`)
          .subscribe((message) => {
            const { content } = JSON.parse(message.body);
            this.messagesSubject.next({
              content,
              role: 'employee',
            });
          });
      }
    });
  }

  joinSession(): void {
    this.rxStompService.activate();
    this.publish('/chat/join_session', JSON.stringify({ type: 'Customer' }));
    this.rxStompService
      .watch('/topic/login')
      .pipe(take(1))
      .subscribe((message) => {
        this.sessionID.set(message.body);
      });
  }

  deactivate(): void {
    this.rxStompService.deactivate();
    this.destroy$.unsubscribe();
  }

  sendMessage(content: string): void {
    if (!this.sessionID()) {
      throw new Error('Session not initialized');
    }

    this.publish(
      `/chat/customer_message/${this.sessionID()}`,
      JSON.stringify({ content })
    );
  }

  private publish(destination: string, body: string): void {
    this.rxStompService.publish({
      destination,
      body,
    });
  }

  getMessages(): Observable<any> {
    return this.messagesSubject.asObservable();
  }
}
