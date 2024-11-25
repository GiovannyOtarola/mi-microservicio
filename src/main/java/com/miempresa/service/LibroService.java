package com.miempresa.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.miempresa.model.Libro;
import com.miempresa.repository.LibroRepository;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;

    
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibro(Long id) {
        return libroRepository.findById(id);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

}
