package com.krakedev.proyectos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.ProyectoRepository;

@Service
public class ProyectoServices {
	private final ProyectoRepository repository;

	// Inyeccion de dependencias
	public ProyectoServices(ProyectoRepository repository) {
		this.repository = repository;
	}
	
	//guardar proyecto
	public Proyecto guardar(Proyecto proyecto) {
		return repository.save(proyecto);
	}
	
	//listar proyectos
	public List<Proyecto> listar(){
		return repository.findAll();
	}
	
	//buscar proyecto por id
	public Proyecto buscar (int id) {
		
		//contenedor que puede tener un Proyecto o estar vacío
		Optional<Proyecto> resultado = repository.findById(id);
		return resultado.orElse(null);
	}
	
	//actualizar proyecto
	public Proyecto actualizar (int id, Proyecto datos) {
		Proyecto proyecto = buscar(id);
		if(proyecto==null) {
			return null;
		}
		
		proyecto.setNombre(datos.getNombre());
		proyecto.setDescripcion(datos.getDescripcion());
		proyecto.setFechaInicio(datos.getFechaInicio());
		
		return repository.save(proyecto);
	}
	
	//eliminar proyecto
	public boolean eliminar (int id) {
		Proyecto proyecto = buscar(id);
		if(proyecto==null) {
			return false;
		}
		repository.deleteById(id);

        return true;
	}
	
	

}
