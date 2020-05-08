package br.edu.logatti.chatinteligente.repository;

import br.edu.logatti.chatinteligente.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByChatId(final Integer ChatId);

}