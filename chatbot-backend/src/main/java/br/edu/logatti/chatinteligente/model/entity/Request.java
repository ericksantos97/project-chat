package br.edu.logatti.chatinteligente.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pedido")
public class Request implements Serializable {

    private static final long serialVersionUID = 6885967284608862716L;

    @Id
    @Column(name = "numero_pedido")
    @SequenceGenerator(name = "pedidoGenerator", sequenceName = "pedido_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidoGenerator")
    private Integer requestNumber;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(name = "data_compra", columnDefinition = "date default current_date", nullable = false, insertable = false, updatable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private String status;

    @Column(name = "numero_nota_fiscal", length = 50, nullable = false, unique = true)
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_produto")
    private Product product;

    @Column(length = 200, nullable = false)
    private String client;

}
