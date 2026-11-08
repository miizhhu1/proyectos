package com.krakedev.proyectos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.services.EmpleadoServices;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoServices service;

    // Inyección de dependencias
    public EmpleadoController(EmpleadoServices service) {
        this.service = service;
    }

    // POST: guardar un empleado
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoGuardado = service.guardar(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el empleado: " + e.getMessage());
        }
    }

    // GET: listar todos los empleados
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Empleado> empleados = service.listar();
            return ResponseEntity.ok(empleados);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los empleados: " + e.getMessage());
        }
    }

    // GET: buscar empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try {
            Empleado empleado = service.buscar(id);

            if (empleado == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(empleado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar el empleado: " + e.getMessage());
        }
    }

    // PUT: actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = service.actualizar(id, empleado);

            if (empleadoActualizado == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(empleadoActualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    // DELETE: eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            Empleado empleado = service.buscar(id);

            if (empleado == null) {
                return ResponseEntity.notFound().build();
            }

            service.eliminar(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el empleado: " + e.getMessage());
        }
    }
}
