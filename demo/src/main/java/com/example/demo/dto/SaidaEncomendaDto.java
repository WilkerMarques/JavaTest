package com.example.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaidaEncomendaDto {

    private Double vlTotalFrete;
    private Date dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;

}
