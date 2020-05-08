import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../model/message';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private readonly BASE_URL = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}

  public createChat(): Observable<number> {
    return this.http.get<number>(`${this.BASE_URL}chat/create`);
  }

  public findByChatId(chatId: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.BASE_URL}findByChatId/${chatId}`);
  }

  public sendMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(`${this.BASE_URL}message`, message);
  }
}
