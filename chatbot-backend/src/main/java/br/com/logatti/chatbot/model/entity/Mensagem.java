package br.edu.logatti.chatinteligente.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 8045133947379919499L;

    @Id
    @SequenceGenerator(name = "mensagemGenerator", sequenceName = "mensagem_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagemGenerator")
    private Integer id;

    @Column(columnDefinition = "timestamp with time zone default current_timestamp", nullable = false, insertable = false, updatable = false)
    private LocalDateTime data;

    @Column(length = 200, nullable = false)
    private String usuario;

    @Column(length = 500, nullable = false)
    private String mensagem;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_chat")
    private Chat chat;

}
