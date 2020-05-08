import { Chat } from './chat';

export class Message {
  id?: number;
  data?: Date;
  usuario?: string;
  mensagem?: string;
  chat?: Chat;
}
