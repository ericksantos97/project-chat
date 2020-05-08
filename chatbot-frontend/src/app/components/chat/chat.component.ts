import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ChatService } from './service/chat-service.service';
import { Message } from './model/message';
import { Chat } from './model/chat';
import * as moment from 'moment';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {

  public name = '';
  public message = '';
  public messages: Message[] = [];
  public enableChat = false;
  public chat: Chat = new Chat();

  @ViewChild('buttonSend') buttonSend: ElementRef;
  @ViewChild('chatRef') chatRef: ElementRef;
  @ViewChild('messageArea') messageArea: ElementRef;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {}

  enterName() {
    if (this.name &&
      this.name.trim() &&
      this.name.toLocaleLowerCase() !== 'cold') {
        this.enableChat = true;
        this.createNewChat();
      }
  }

  doTextareaValueChange(event): void {
    try {
      this.message = event.target.value;
    } catch (e) {
      console.log(e);
    }
  }

  createNewChat() {
    this.chatService.createChat().subscribe((chat) => {
      this.chat = chat;
      this.getMessageByChat();
    });
  }

  getMessageByChat() {
    this.chatService.findByChatId(this.chat.id).subscribe(values => this.messages = values);
    let message = '';

    if (this.chatRef) {
      this.chatRef.nativeElement.innerHTML = '';
    }

    this.messages.forEach((it) => {
      message += `</img><p class='titulo-${
        it.user === 'Cold' ? 'cold' : 'cliente'
      }'><img style='width: 6%;' src='../../../assets/${
        it.user === 'Atendente' ? 'cold' : 'sem-foto'
      }.jpg'>&nbsp;${it.user} - ${this.momentFormatDateActual()}
      )}</p> <p class='conteudo'>message: ${it.message}</p>`;
    });

    if (this.chatRef) {
      this.chatRef.nativeElement.innerHTML = message;
      const textarea = this.chatRef;
      textarea.nativeElement.scrollTop = textarea.nativeElement.scrollHeight;
    }
  }

  sendMessage() {
    if (!this.message || this.message.trim()) {
      const body = new Message();
      body.user = this.name,
      body.message = this.message,
      body.chat = { id: this.chat.id };

      this.chatService.sendMessage(body).subscribe();
      this.getMessageByChat();
      this.messageArea.nativeElement.innerHTML = '';
      this.messageArea.nativeElement.focus();
    } else {
      alert('O campo mensagem é obrigatório.');
    }
  }

  momentFormatDateActual(): string {
    return moment(new Date()).format('DD/MM/YYYY HH:mm:ss');
  }

  reloadPage(): void {
    window.location.reload();
  }

}
