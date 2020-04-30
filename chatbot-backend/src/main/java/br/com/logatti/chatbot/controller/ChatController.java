package br.edu.logatti.chatinteligente.controller;

import br.edu.logatti.chatinteligente.model.entity.Chat;
import br.edu.logatti.chatinteligente.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/chat")
public class ChatController {

    private final ChatService service;

    @GetMapping(path = "/create")
    public ResponseEntity<Chat> create() {
        return ResponseEntity.ok().body(service.create());
    }

}
