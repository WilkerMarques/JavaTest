package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncomendaDto{

    private Long id;
    private Double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private Double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataConsulta;
}
