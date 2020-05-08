import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatRoutingModule } from './chat-routing.module';
import { ChatComponent } from './chat/chat.component';


@NgModule({
  imports: [
    ChatRoutingModule,
    FormsModule,
    CommonModule
  ],
  declarations: [
    ChatComponent
  ]
})
export class ChatModule { }
