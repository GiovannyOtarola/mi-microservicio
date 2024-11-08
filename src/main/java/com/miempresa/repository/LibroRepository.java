package com.miempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.model.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {
}

