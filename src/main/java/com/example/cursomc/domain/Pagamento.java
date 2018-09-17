package com.example.cursomc.domain;

import com.example.cursomc.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@Entity
@Inheritance(strategy =InheritanceType.JOINED) // Mapeamento de herancas -------- Definicao de Estrategia //
// Uma tabela para todas as tabelas da generalizacao ou uma tabela para cara cada entidade
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer estadoPagamento;

    @JsonIgnore
    @JoinColumn(name="pedido_id")
    @OneToOne
    @MapsId
    private Pedido pedido;

    public Pagamento(){

    }

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = estadoPagamento.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(estadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
