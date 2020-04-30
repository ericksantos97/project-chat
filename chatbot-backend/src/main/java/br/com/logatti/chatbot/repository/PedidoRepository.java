package br.edu.logatti.chatinteligente.repository;

import br.edu.logatti.chatinteligente.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findFirstByClienteIgnoreCaseOrderByDataCompraDesc(final String cliente);

}
