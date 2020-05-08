package br.edu.logatti.chatinteligente.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 8054608464579383000L;

    @Id
    @SequenceGenerator(name = "produtoGenerator", sequenceName = "produto_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoGenerator")
    private Integer id;

    @Column(length = 200, nullable = false, unique = true)
    private String descricao;

    @Column(length = 200, nullable = false)
    private String tipo;

}
