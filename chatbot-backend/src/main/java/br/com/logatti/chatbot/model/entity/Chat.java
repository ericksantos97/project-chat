package br.edu.logatti.chatinteligente.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1849001930558044012L;

    @Id
    @SequenceGenerator(name = "chatGenerator", sequenceName = "chat_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatGenerator")
    private Integer id;

    @Column(name = "data_inicio", columnDefinition = "timestamp with time zone default current_timestamp", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataInicio;

}
