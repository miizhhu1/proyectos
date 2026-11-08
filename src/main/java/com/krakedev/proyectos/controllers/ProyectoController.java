package com.krakedev.proyectos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.services.ProyectoServices;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoServices service;

    // Inyección de dependencias
    public ProyectoController(ProyectoServices service) {
        this.service = service;
    }

    // POST: guardar un proyecto
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Proyecto proyecto) {
        try {
            Proyecto proyectoGuardado = service.guardar(proyecto);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectoGuardado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el proyecto: " + e.getMessage());
        }
    }

    // GET: listar todos los proyectos
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Proyecto> proyectos = service.listar();
            return ResponseEntity.ok(proyectos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los proyectos: " + e.getMessage());
        }
    }

    // GET: buscar un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try {
            Proyecto proyecto = service.buscar(id);

            if (proyecto == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(proyecto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar el proyecto: " + e.getMessage());
        }
    }

    // PUT: actualizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @RequestBody Proyecto proyecto) {
        try {
            Proyecto proyectoActualizado = service.actualizar(id, proyecto);

            if (proyectoActualizado == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(proyectoActualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el proyecto: " + e.getMessage());
        }
    }

    // DELETE: eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            Proyecto proyecto = service.buscar(id);

            if (proyecto == null) {
                return ResponseEntity.notFound().build();
            }

            service.eliminar(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el proyecto: " + e.getMessage());
        }
    }
}