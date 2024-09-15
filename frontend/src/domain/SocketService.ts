import { Observable } from 'rxjs';
import { Message } from './Message';

export interface SocketService {
  joinSession(): void;
  sendMessage(content: string, conversationID?: string): void;
  onMessage(): Observable<{ message: Message; conversationID: string }>;
  disconnect(): void;
}
