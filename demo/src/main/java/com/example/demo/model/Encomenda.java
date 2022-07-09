package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peso;

    private String cepOrigem;

    private String cepDestino;

    private String nomeDestinatario;

    private Double vlTotalFrete;

    private Date dataPrevistaEntrega;

    private Date dataConsulta;

}
