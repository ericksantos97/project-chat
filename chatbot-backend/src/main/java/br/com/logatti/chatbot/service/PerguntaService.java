package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Pergunta;
import br.edu.logatti.chatinteligente.repository.PerguntaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PerguntaService {

    private final PerguntaRepository repository;

    @Transactional(readOnly = true)
    public List<Pergunta> findAll() {
        return repository.findAll();
    }

}
