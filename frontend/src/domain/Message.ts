export type MessageSender = 'customer' | 'employee';

export type Message = {
  content: string;
  role: MessageSender;
};
