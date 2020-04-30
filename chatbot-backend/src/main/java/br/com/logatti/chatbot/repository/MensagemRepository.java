package br.edu.logatti.chatinteligente.repository;

import br.edu.logatti.chatinteligente.model.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    List<Mensagem> findByChatId(final Integer ChatId);

}