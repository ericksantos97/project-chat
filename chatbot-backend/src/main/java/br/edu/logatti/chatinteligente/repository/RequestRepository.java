package br.edu.logatti.chatinteligente.repository;

import br.edu.logatti.chatinteligente.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findFirstByClienteIgnoreCaseOrderByDataCompraDesc(final String cliente);

}
