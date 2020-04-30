package br.edu.logatti.chatinteligente.service;

import br.edu.logatti.chatinteligente.model.entity.Chat;
import br.edu.logatti.chatinteligente.model.entity.Mensagem;
import br.edu.logatti.chatinteligente.model.entity.Pedido;
import br.edu.logatti.chatinteligente.model.entity.Pergunta;
import br.edu.logatti.chatinteligente.repository.MensagemRepository;
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
public class MensagemService {

    private final MensagemRepository repository;
    private final PerguntaService perguntaService;
    private final PedidoService pedidoService;

    @Transactional(readOnly = true)
    public List<Mensagem> findByChatId(final Integer chatId) {
        return repository.findByChatId(chatId);
    }

    public Mensagem create(final Mensagem mensagem) {
        final Mensagem response = repository.save(mensagem);
        createMessageRobot(response);
        return response;
    }

    public Mensagem createFirstMessage(final Integer chatId) {
        final Mensagem mensagem = new Mensagem();
        mensagem.setMensagem("Olá, em que posso ajudar?");
        mensagem.setUsuario("Atendente");
        mensagem.setChat(createChat(chatId));
        return repository.save(mensagem);
    }

    private Chat createChat(final Integer chatId) {
        final Chat chat = new Chat();
        chat.setId(chatId);
        return chat;
    }

    private void createMessageRobot(final Mensagem messageUser) {
        final Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(responder(messageUser));
        mensagem.setUsuario("Atendente");
        mensagem.setChat(createChat(messageUser.getChat().getId()));
        repository.save(mensagem);
    }

    private String responder(final Mensagem mensagem) {
        final Pergunta pergunta = encontarPergunta(mensagem.getMensagem());
        if (Objects.isNull(pergunta)) {
            return "Não consegui entender, seja mais específico ...";
        }
        final Pedido pedido = pedidoService.findLastByCliente(mensagem.getUsuario());
        if (Objects.isNull(pedido)) {
            return "Você não possui pedido";
        }
        switch (pergunta.getId()) {
            case 1:
                return String.format(pergunta.getResposta(), pedido.getNumeroPedido().toString());
            case 2:
                return String.format(pergunta.getResposta(), pedido.getNumeroNotaFiscal());
            case 3:
                return String.format(pergunta.getResposta(), pedido.getStatus());
        }
        return null;
    }

    private String formatarPalavra(String palavra) {
        palavra = Normalizer.normalize(palavra, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return palavra.replaceAll("[^A-Za-z0-9]+", "");
    }

    private Pergunta encontarPergunta(final String mensagem) {
        final List<Pergunta> perguntas = perguntaService.findAll();
        final List<String> palavrasMensagem = Arrays.asList(mensagem.split(" ")).stream().map(x -> x = formatarPalavra(x)).collect(Collectors.toList());
        Pergunta perguntaEncotrada = null;
        for (Pergunta pergunta : perguntas) {
            final List<String> palavrasChaves = Arrays.asList(pergunta.getPalavrasChave().split(" "));
            if (palavrasMensagem.containsAll(palavrasChaves)) {
                perguntaEncotrada = pergunta;
            }
        }
        return perguntaEncotrada;
    }

}
