package br.edu.logatti.chatbot.controller;

import br.edu.logatti.chatbot.model.entity.Message;
import br.edu.logatti.chatbot.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/mensagem")
public class MessageController {

    private final MessageService service;

    @GetMapping(path = "/findByChatId/{chatId}")
    public ResponseEntity<List<Message>> findByChatId(@PathVariable final Integer chatId) {
        return ResponseEntity.ok().body(service.findByChatId(chatId));
    }

    @PostMapping
    public ResponseEntity<Message> create(@RequestBody final Message message) {
        return ResponseEntity.ok().body(service.create(message));
    }

}
