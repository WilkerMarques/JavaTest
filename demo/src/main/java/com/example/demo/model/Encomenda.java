package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Preenchimento Peso obrigatório!")
    private Double peso;

    @NotEmpty(message = "Preenchimento Cep Origem obrigatório!")
    @Size(max = 8, message = "Cep Origem deve conter menos de 8 caracteres")
    private String cepOrigem;

    @NotEmpty(message = "Preenchimento Cep Destino obrigatório!")
    @Size(max = 8, message = "Cep Destino deve conter menos de 8 caracteres")
    private String cepDestino;

    @NotEmpty(message = "Preenchimento Nome Destinarario obrigatório!")
    @Size(max = 255, message = "Nome Destinarario deve conter menos de 255 caracteres")
    private String nomeDestinatario;

    @NotNull(message = "Preenchimento Valor Total Frete obrigatório!")
    private Double vlTotalFrete;

    @NotNull(message = "Preenchimento Data Prevista Entrega obrigatório!")
    private LocalDate dataPrevistaEntrega;

    @NotNull(message = "Preenchimento Data Consulta obrigatório!")
    private LocalDate dataConsulta;

}
