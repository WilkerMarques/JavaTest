package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EncomendaDto;
import com.example.demo.service.EncomendaService;

@RestController
@RequestMapping(path = "/api")
public class EncomendaController {

    @Autowired
    private EncomendaService service;

    @PostMapping(path = "/encomendas")
    public ResponseEntity<?> post(@RequestBody EncomendaDto objDto) {
        try {
            service.criar(objDto);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
