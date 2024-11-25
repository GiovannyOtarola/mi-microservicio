package com.miempresa.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miempresa.exception.ResourceNotFoundException;
import com.miempresa.model.Libro;
import com.miempresa.service.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Listar todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.listarLibros();
        return ResponseEntity.ok(libros);
    }

    // Crear un nuevo libro con validación
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@Valid @RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED); // Devolver 201 Created
    }

    // Obtener un libro por su ID con manejo de excepción
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibro(id)
                .orElseThrow(() -> new ResourceNotFoundException("El libro con ID " + id + " no fue encontrado."));
        return ResponseEntity.ok(libro);
    }

    // Eliminar un libro por su ID con manejo de excepción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        // Verifica si el libro existe, si no, lanza la excepción
        libroService.obtenerLibro(id)
                .orElseThrow(() -> new ResourceNotFoundException("El libro con ID " + id + " no fue encontrado."));

        // Elimina el libro si existe
        libroService.eliminarLibro(id);

        return ResponseEntity.noContent().build(); // Devolver 204 No Content
    }

    // Actualizar un libro existente con manejo de excepción
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @Valid @RequestBody Libro detallesLibro) {
        Libro libro = libroService.obtenerLibro(id)
                .orElseThrow(() -> new ResourceNotFoundException("El libro con ID " + id + " no fue encontrado."));

        libro.setTitulo(detallesLibro.getTitulo());
        libro.setAutor(detallesLibro.getAutor());
        libro.setAnioPublicacion(detallesLibro.getAnioPublicacion());
        libro.setGenero(detallesLibro.getGenero());
        Libro libroActualizado = libroService.guardarLibro(libro);
        return ResponseEntity.ok(libroActualizado);
    }
}
