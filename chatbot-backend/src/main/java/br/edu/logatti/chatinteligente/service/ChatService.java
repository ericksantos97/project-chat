package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Chat;
import br.edu.logatti.chatinteligente.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final MensagemService mensagemService;

    public Chat create() {
        Chat chat = repository.save(new Chat());
        mensagemService.createFirstMessage(chat.getId());
        return chat;
    }

}
