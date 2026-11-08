package com.krakedev.proyectos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.EmpleadoRepository;

@Service
public class EmpleadoServices {
	private final EmpleadoRepository repository;

	// Inyeccion de dependencias
	public EmpleadoServices(EmpleadoRepository repository) {
		this.repository = repository;
	}

	// guardar empledado
	public Empleado guardar(Empleado empleado) {
		return repository.save(empleado);
	}

	// listar empleado
	public List<Empleado> listar() {
		return repository.findAll();
	}

	// buscar proyecto por id
	public Empleado buscar(int id) {

		// contenedor que puede tener un Proyecto o estar vacío
		Optional<Empleado> resultado = repository.findById(id);
		return resultado.orElse(null);
	}
	
	//actulizar empleado
	public Empleado actualizar(int id, Empleado datos) {
		Empleado empleado = buscar(id);
		if(empleado==null) {
			return null;
		}
		
		empleado.setNombre(datos.getNombre());
		empleado.setCargo(datos.getCargo());
		
		return repository.save(datos);
	}
	
	//eliminar empleado
	public boolean eliminar (int id) {
		Empleado empleado = buscar(id);
		if(empleado==null) {
			return false;
		}
		
		repository.deleteById(id);
		return true;
	}
}
