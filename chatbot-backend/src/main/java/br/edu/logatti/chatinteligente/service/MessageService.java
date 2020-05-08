package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Chat;
import br.edu.logatti.chatinteligente.model.entity.Message;
import br.edu.logatti.chatinteligente.model.entity.Question;
import br.edu.logatti.chatinteligente.model.entity.Request;
import br.edu.logatti.chatinteligente.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final QuestionService questionService;
    private final RequestService requestService;

    @Transactional(readOnly = true)
    public List<Message> findByChatId(final Integer chatId) {
        return repository.findByChatId(chatId);
    }

    public Message create(final Message message) {
        final Message response = repository.save(message);
        createMessageRobot(response);
        return response;
    }

    public Message createFirstMessage(final Integer chatId) {
        final Message message = new Message();
        message.setMessage("Olá, meu nome é Cold, em que posso ajudar ?");
        message.setUser("Atendente");
        message.setChat(createChat(chatId));
        return repository.save(message);
    }

    private Chat createChat(final Integer chatId) {
        final Chat chat = new Chat();
        chat.setId(chatId);
        return chat;
    }

    private void createMessageRobot(final Message messageUser) {
        final Message message = new Message();
        message.setMessage(responder(messageUser));
        message.setUser("Atendente");
        message.setChat(createChat(messageUser.getChat().getId()));
        repository.save(message);
    }

    private String responder(final Message message) {
        final Question question = findOutMore(message.getMessage());
        if (Objects.isNull(question)) {
            return "Não consegui entender a sua pergunta, tente novamente por favor...";
        }
        final Request request = requestService.findLastByCliente(message.getUser());
        if (Objects.isNull(request)) {
            return "Você não possui pedido";
        }
        switch (question.getId()) {
            case 1:
                return String.format(question.getAnswer(), request.getRequestNumber().toString());
            case 2:
                return String.format(question.getAnswer(), request.getInvoiceNumber());
            case 3:
                return String.format(question.getAnswer(), request.getStatus());
        }
        return null;
    }

    private String formatWord(String word) {
        word = Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return word.replaceAll("[^A-Za-z0-9]+", "");
    }

    private Question findOutMore(final String message) {
        final List<Question> questions = questionService.findAll();
        final List<String> wordsMessage = Arrays.asList(message.split(" ")).stream().map(x -> x = formatWord(x)).collect(Collectors.toList());
        Question questionFound = null;
        for (Question question : questions) {
            final List<String> keyWords = Arrays.asList(question.getKeyWords().split(" "));
            if (wordsMessage.containsAll(keyWords)) {
                questionFound = question;
            }
        }
        return questionFound;
    }

}
