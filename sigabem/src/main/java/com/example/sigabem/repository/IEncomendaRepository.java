package com.example.sigabem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sigabem.model.Encomenda;

@Repository
public interface IEncomendaRepository extends JpaRepository<Encomenda, Long>{

}

