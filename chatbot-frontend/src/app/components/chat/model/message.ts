import { Chat } from './chat';

export class Message {
  id?: number;
  data?: Date;
  user?: string;
  message?: string;
  chat?: Chat;
}
