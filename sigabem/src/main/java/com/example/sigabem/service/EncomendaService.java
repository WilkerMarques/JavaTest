package com.example.sigabem.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.sigabem.dto.CepDto;
import com.example.sigabem.dto.EncomendaDto;
import com.example.sigabem.dto.EntradaEncomendaDto;
import com.example.sigabem.dto.SaidaEncomendaDto;
import com.example.sigabem.model.Encomenda;
import com.example.sigabem.repository.IEncomendaRepository;

@Service
public class EncomendaService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private IEncomendaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public CepDto consultarCep(String cep) throws Exception {
        return webClient
                .method(HttpMethod.GET).uri("https://viacep.com.br/ws/" + cep + "/json/")
                .retrieve()
                .bodyToMono(CepDto.class)
                .block();
    }

    public Double calcularDesconto(CepDto cepOrigem, CepDto cepDestino) {
        if (cepOrigem.getDdd().equals(cepDestino.getDdd())) {
            return 0.5;
        } else if (cepOrigem.getUf().equals(cepDestino.getUf())) {
            return 0.25;
        }
        return 1.0;
    }

    public Long calcularPrazoEntrega(CepDto cepOrigem, CepDto cepDestino) {
        if (cepOrigem.getDdd().equals(cepDestino.getDdd())) {
            return 1L;
        } else if (cepOrigem.getUf().equals(cepDestino.getUf())) {
            return 3L;
        }
        return 10L;
    }

    public Double calcularTotalDoFrete(EntradaEncomendaDto entradaEncomendaDto) throws Exception {
        CepDto cepOrigem = consultarCep(entradaEncomendaDto.getCepOrigem());
        CepDto cepDestino = consultarCep(entradaEncomendaDto.getCepDestino());

        return entradaEncomendaDto.getPeso() * calcularDesconto(cepOrigem, cepDestino);
    }

    public LocalDate calcularPrevisaoDeEntrega(EntradaEncomendaDto entradaEncomendaDto, LocalDate dataConsulta)
            throws Exception {
        CepDto cepOrigem = consultarCep(entradaEncomendaDto.getCepOrigem());
        CepDto cepDestino = consultarCep(entradaEncomendaDto.getCepDestino());
        Long PrazoEmDias = calcularPrazoEntrega(cepOrigem, cepDestino);

        return dataConsulta.plusDays(PrazoEmDias);
    }

    public SaidaEncomendaDto encomendaParaSaidaEncomendaDto(EncomendaDto encomendaDto) {
        SaidaEncomendaDto saidaEncomendaDto = new SaidaEncomendaDto();

        saidaEncomendaDto.setCepOrigem(encomendaDto.getCepOrigem());
        saidaEncomendaDto.setCepDestino(encomendaDto.getCepDestino());
        saidaEncomendaDto.setVlTotalFrete(encomendaDto.getVlTotalFrete());
        saidaEncomendaDto.setDataPrevistaEntrega(encomendaDto.getDataPrevistaEntrega());

        return saidaEncomendaDto;
    }

    public EncomendaDto entradaEncomendaDtoParaEncomendaDto(EntradaEncomendaDto entradaEncomendaDto) {
        EncomendaDto encomendaDto = new EncomendaDto();

        encomendaDto.setNomeDestinatario(entradaEncomendaDto.getNomeDestinatario());
        encomendaDto.setCepOrigem(entradaEncomendaDto.getCepOrigem());
        encomendaDto.setCepDestino(entradaEncomendaDto.getCepDestino());
        encomendaDto.setPeso(entradaEncomendaDto.getPeso());

        return encomendaDto;
    }

    public SaidaEncomendaDto salvarConsulta(EntradaEncomendaDto entradaEncomendaDto) throws Exception{
        EncomendaDto encomendaDto = entradaEncomendaDtoParaEncomendaDto(entradaEncomendaDto);

        encomendaDto.setDataConsulta(LocalDate.now());
        encomendaDto.setVlTotalFrete(calcularTotalDoFrete(entradaEncomendaDto));
        encomendaDto.setDataPrevistaEntrega(calcularPrevisaoDeEntrega(entradaEncomendaDto, encomendaDto.getDataConsulta()));
        repository.save(modelMapper.map(encomendaDto,Encomenda.class));

        return encomendaParaSaidaEncomendaDto(encomendaDto);
    }
}
