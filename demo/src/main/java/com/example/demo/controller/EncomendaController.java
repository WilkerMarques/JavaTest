package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EntradaEncomendaDto;
import com.example.demo.dto.SaidaEncomendaDto;
import com.example.demo.service.EncomendaService;

@RestController
@RequestMapping(path = "/api")
public class EncomendaController {
    
    @Autowired
    private EncomendaService encomendaService;

    @PostMapping(path = "/encomendas")
    public ResponseEntity<?> post(@RequestBody EntradaEncomendaDto objDto) {
        try {
            SaidaEncomendaDto saidaEncomendaDto = encomendaService.salvarConsulta(objDto);
            return ResponseEntity.ok().body(saidaEncomendaDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
