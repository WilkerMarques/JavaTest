package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaEncomendaDto {

    private Double peso;

    private String cepOrigem;

    private String cepDestino;

    private String nomeDestinatario;
}
