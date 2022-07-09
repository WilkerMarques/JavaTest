package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EncomendaDto;
import com.example.demo.model.Encomenda;
import com.example.demo.repository.IEncomendaRepository;

@Service
public class EncomendaService {

    @Autowired
    private IEncomendaRepository repository;

    public void criar(EncomendaDto obj) {
//teste subir
        Encomenda mod = converteModel(obj);
        mod.setId(null);
        try {
            repository.save(mod);
        } catch (Exception e) {
            throw e;
        }
    }

    public Encomenda converteModel(EncomendaDto dto) {
        Encomenda mod = new Encomenda();
        mod.setId(dto.getId());
        mod.setCepDestino(dto.getCepDestino());
        mod.setCepOrigem(dto.getCepOrigem());
        mod.setDataConsulta(dto.getDataConsulta());
        mod.setDataPrevistaEntrega(dto.getDataPrevistaEntrega());
        mod.setNomeDestinatario(dto.getNomeDestinatario());
        mod.setPeso(dto.getPeso());
        mod.setVlTotalFrete(dto.getVlTotalFrete());
        return mod;
    }

}
