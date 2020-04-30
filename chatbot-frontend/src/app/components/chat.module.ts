import { NgModule } from '@angular/core';
import { ChatRoutingModule } from './chat-routing.module';
import { ChatComponent } from './chat/chat.component';


@NgModule({
  imports: [
    ChatRoutingModule,
  ],
  declarations: [
    ChatComponent
  ]
})
export class ChatModule { }
