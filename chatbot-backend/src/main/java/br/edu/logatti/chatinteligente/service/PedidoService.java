package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Pedido;
import br.edu.logatti.chatinteligente.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    @Transactional(readOnly = true)
    public Pedido findLastByCliente(final String usuario) {
        return repository.findFirstByClienteIgnoreCaseOrderByDataCompraDesc(usuario);
    }

}
