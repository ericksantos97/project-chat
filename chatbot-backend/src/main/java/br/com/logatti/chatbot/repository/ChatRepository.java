package br.edu.logatti.chatinteligente.repository;

import br.edu.logatti.chatinteligente.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

}