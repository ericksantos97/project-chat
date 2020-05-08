package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Question;
import br.edu.logatti.chatinteligente.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return repository.findAll();
    }

}
