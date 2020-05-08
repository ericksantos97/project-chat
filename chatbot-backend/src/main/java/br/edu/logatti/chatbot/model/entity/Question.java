package br.edu.logatti.chatbot.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pergunta")
public class Question implements Serializable {

    private static final long serialVersionUID = 4125090462184893812L;

    @Id
    @SequenceGenerator(name = "perguntaGenerator", sequenceName = "pergunta_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perguntaGenerator")
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 500, unique = true)
    private String description;

    @Column(name = "palavras_chave", nullable = false, length = 500, unique = true)
    private String keyWords;

    @Column(name = "resposta", nullable = false, length = 500)
    private String answer;

}
