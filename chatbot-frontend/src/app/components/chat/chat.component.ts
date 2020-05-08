import { Component, OnInit } from '@angular/core';
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

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {}

  enterName(): void {
    if (this.name &&
      this.name.trim() &&
      this.name.toLocaleLowerCase() !== 'atendente') {
        this.enableChat = true;
        this.criarNovoChat();
      }
  }

  doTextareaValueChange(event): void {
    try {
      this.message = event.target.value;

      if (event.key === '13') {
        event.preventDefault();
        document.getElementById('enviar').click();
      }
    } catch (e) {
      console.log('Could not set textarea-value');
    }
  }

  async criarNovoChat() {
    this.chatService.createChat().subscribe((id) => (this.chatId = id));
    await this.recuperarMensagensPorChat();
  }

  async recuperarMensagensPorChat() {
    this.chatService.findByChatId(this.chatId).subscribe(values => this.messages = values);
    document.getElementById('chat').innerHTML = '';
    let message = '';
    this.messages.forEach((it) => {
      message += `</img><p class='titulo-${
        it.usuario === 'Atendente' ? 'atendente' : 'cliente'
      }'><img style='width: 6%;' src='img/${
        it.usuario === 'Atendente' ? 'atendente' : 'sem-foto'
      }.jpg'>&nbsp;${it.usuario} - ${this.formatarData(
        it.data
      )}</p> <p class='conteudo'>message: ${it.mensagem}</p>`;
    });
    document.getElementById('chat').innerHTML = message;
    const textarea = document.getElementById('chat');
    textarea.scrollTop = textarea.scrollHeight;
  }

  async enviarmessage() {
    if (this.message.trim()) {
      const body = new Message();
      body.usuario = this.name,
      body.mensagem = this.message,
      body.chat = { id: this.chatId };

      this.chatService.sendMessage(body).subscribe();
      await this.recuperarMensagensPorChat();
      // document.getElementById('message').value = '';
      // input.focus();
    } else {
      alert('O campo mensagem é obrigatório.');
    }
  }

  formatarData(data) {
    return `${this.formatarValor(data[2])}/${this.formatarValor(data[1])}/${
      data[0]
    } ${this.formatarValor(data[3])}:${this.formatarValor(
      data[4]
    )}:${this.formatarValor(data[5])}`;
  }

  formatarValor(valor) {
    return `${valor.toString().length === 1 ? '0' + valor : valor}`;
  }
}
