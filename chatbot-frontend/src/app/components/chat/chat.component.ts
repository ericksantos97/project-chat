import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  public nome = '';
  public chatId = 0;
  public mensagem = '';

  constructor() {}

  ngOnInit(): void {
    while (
      !this.nome ||
      !this.nome.trim() ||
      this.nome.length > 200 ||
      this.nome.toLocaleLowerCase() === 'atendente'
    ) {
      this.nome = window.prompt('Informe seu nome:');
    }

    this.criarNovoChat();
  }

  doTextareaValueChange(event): void {
    try {
      this.mensagem = event.target.value;

      if (event.key === '13') {
        event.preventDefault();
        document.getElementById('enviar').click();
      }
    } catch (e) {
      console.log('Could not set textarea-value');
    }
  }

  async criarNovoChat() {
    const response = await fetch('http://localhost:8080/api/chat/create', {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    });
    const content = await response.json();
    this.chatId = content.id;
    await this.recuperarMensagensPorChat();
  }

  async recuperarMensagensPorChat() {
    const response = await fetch(
      `http://localhost:8080/api/mensagem/findByChatId/${this.chatId}`,
      {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
      }
    );
    const content = await response.json();
    document.getElementById('chat').innerHTML = '';
    let mensagem = '';
    content.forEach((it) => {
      mensagem += `</img><p class='titulo-${
        it.usuario === 'Atendente' ? 'atendente' : 'cliente'
      }'><img style='width: 6%;' src='img/${
        it.usuario === 'Atendente' ? 'atendente' : 'sem-foto'
      }.jpg'>&nbsp;${it.usuario} - ${this.formatarData(
        it.data
      )}</p> <p class='conteudo'>Mensagem: ${it.mensagem}</p>`;
    });
    document.getElementById('chat').innerHTML = mensagem;
    const textarea = document.getElementById('chat');
    textarea.scrollTop = textarea.scrollHeight;
  }

  async enviarMensagem() {
    const mensagem = this.mensagem;
    if (mensagem.trim()) {
      await fetch(`http://localhost:8080/api/mensagem`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          usuario: this.nome,
          mensagem: this.mensagem,
          chat: { id: this.chatId },
        }),
      });
      await this.recuperarMensagensPorChat();
      // document.getElementById('mensagem').value = '';
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
