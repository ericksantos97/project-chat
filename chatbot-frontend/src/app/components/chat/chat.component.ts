import { Component, OnInit, ViewChild } from '@angular/core';
import { ChatService } from './service/chat-service.service';
import { Message } from './model/message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {

  public name = '';
  public chatId = 0;
  public message = '';
  public messages: Message[] = [];
  public enableChat = false;

  @ViewChild('buttonSend') buttonSend: HTMLElement;
  @ViewChild('chat') chat: HTMLElement;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {}

  enterName(): void {
    if (this.name &&
      this.name.trim() &&
      this.name.toLocaleLowerCase() !== 'atendente') {
        this.enableChat = true;
        this.createNewChat();
      }
  }

  doTextareaValueChange(event): void {
    try {
      this.message = event.target.value;

      if (event.key === '13') {
        event.preventDefault();
        this.buttonSend.click();
      }
    } catch (e) {
      console.log(e);
    }
  }

  async createNewChat() {
    this.chatService.createChat().subscribe((id) => (this.chatId = id));
    await this.getMessageByChat();
  }

  async getMessageByChat() {
    this.chatService.findByChatId(this.chatId).subscribe(values => this.messages = values);
    this.chat.innerHTML = '';
    let message = '';

    this.messages.forEach((it) => {
      message += `</img><p class='titulo-${
        it.user === 'Atendente' ? 'atendente' : 'cliente'
      }'><img style='width: 6%;' src='../../../assets/${
        it.user === 'Atendente' ? 'atendente' : 'sem-foto'
      }.jpg'>&nbsp;${it.user} - ${this.dateFormat(
        it.data
      )}</p> <p class='conteudo'>message: ${it.message}</p>`;
    });

    this.chat.innerHTML = message;
    const textarea = this.chat;
    textarea.scrollTop = textarea.scrollHeight;
  }

  async sendMessage() {
    if (!this.message || this.message.trim()) {
      const body = new Message();
      body.user = this.name,
      body.message = this.message,
      body.chat = { id: this.chatId };

      this.chatService.sendMessage(body).subscribe();
      await this.getMessageByChat();
      // document.getElementById('message').value = '';
      // input.focus();
    } else {
      alert('O campo mensagem é obrigatório.');
    }
  }

  dateFormat(data) {
    return `${this.valueFormat(data[2])}/${this.valueFormat(data[1])}/${
      data[0]
    } ${this.valueFormat(data[3])}:${this.valueFormat(
      data[4]
    )}:${this.valueFormat(data[5])}`;
  }

  valueFormat(valor) {
    return `${valor.toString().length === 1 ? '0' + valor : valor}`;
  }

  reloadPage(): void {
    window.location.reload();
  }

}
