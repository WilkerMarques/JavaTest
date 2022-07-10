package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaidaEncomendaDto {

    private Double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;

}
