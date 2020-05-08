package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Request;
import br.edu.logatti.chatinteligente.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    @Transactional(readOnly = true)
    public Request findLastByCliente(final String user) {
        return repository.findFirstByClienteIgnoreCaseOrderByDataCompraDesc(user);
    }

}
