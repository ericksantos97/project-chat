package br.edu.logatti.chatbot.service;

import br.edu.logatti.chatbot.model.entity.Question;
import br.edu.logatti.chatbot.repository.QuestionRepository;
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
