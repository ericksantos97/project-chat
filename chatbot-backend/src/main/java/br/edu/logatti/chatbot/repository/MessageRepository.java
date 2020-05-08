package br.edu.logatti.chatbot.repository;

import br.edu.logatti.chatbot.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByChatId(final Integer ChatId);

}