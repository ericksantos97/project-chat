package br.edu.logatti.chatinteligente.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pergunta")
public class Pergunta implements Serializable {

    private static final long serialVersionUID = 4125090462184893812L;

    @Id
    @SequenceGenerator(name = "perguntaGenerator", sequenceName = "pergunta_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perguntaGenerator")
    private Integer id;

    @Column(nullable = false, length = 500, unique = true)
    private String descricao;

    @Column(name = "palavras_chave", nullable = false, length = 500, unique = true)
    private String palavrasChave;

    @Column(nullable = false, length = 500)
    private String resposta;

}
