package com.krakedev.proyectos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.services.TareaServices;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaServices service;

    // Inyección de dependencias
    public TareaController(TareaServices service) {
        this.service = service;
    }

    // POST: guardar una tarea
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Tarea tarea) {
        try {
            Tarea tareaGuardada = service.guardar(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body(tareaGuardada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la tarea: " + e.getMessage());
        }
    }

    // GET: listar todas las tareas
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Tarea> tareas = service.listar();
            return ResponseEntity.ok(tareas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las tareas: " + e.getMessage());
        }
    }
}