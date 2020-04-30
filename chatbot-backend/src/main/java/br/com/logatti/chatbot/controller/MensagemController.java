package br.edu.logatti.chatinteligente.controller;

import br.edu.logatti.chatinteligente.model.entity.Mensagem;
import br.edu.logatti.chatinteligente.service.MensagemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/mensagem")
public class MensagemController {

    private final MensagemService service;

    @GetMapping(path = "/findByChatId/{chatId}")
    public ResponseEntity<List<Mensagem>> findByChatId(@PathVariable final Integer chatId) {
        return ResponseEntity.ok().body(service.findByChatId(chatId));
    }

    @PostMapping
    public ResponseEntity<Mensagem> create(@RequestBody final Mensagem mensagem) {
        return ResponseEntity.ok().body(service.create(mensagem));
    }

}
