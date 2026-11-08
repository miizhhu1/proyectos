package com.krakedev.proyectos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import com.krakedev.proyectos.repositories.TareaRepository;

@Service
public class TareaServices {

	private final ProyectoRepository proyectoRepository;
	private final EmpleadoRepository empleadoRepository;
	private final TareaRepository tareaRepository;

	public TareaServices(ProyectoRepository proyectoRepository, EmpleadoRepository empleadoRepository,
			TareaRepository tareaRepository) {

		this.proyectoRepository = proyectoRepository;
		this.empleadoRepository = empleadoRepository;
		this.tareaRepository = tareaRepository;
	}

	// guardar tareas

	public Tarea guardar(Tarea tarea) {
		Proyecto proyecto = proyectoRepository.findById(tarea.getProyecto().getId())
				.orElseThrow(() -> new RuntimeException("Poyecto no encontrado"));
		List<Empleado> proyectos_db = new ArrayList<>();

		for (Empleado empleado : tarea.getEmpleados()) {
			Empleado empleadoReal = empleadoRepository.findById(empleado.getId())
					.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
			proyectos_db.add(empleadoReal);
		}
		tarea.setProyecto(proyecto);
		tarea.setEmpleados(proyectos_db);
		return tareaRepository.save(tarea);

	}

	// Consultar todas las tareas
	public List<Tarea> listar() {
		return tareaRepository.findAll();
	}

	// Buscar una tarea por su ID
	public Tarea buscar(Integer id) {
		Optional<Tarea> resultado = tareaRepository.findById(id);
		return resultado.orElse(null);
	}
	
	// actualizar tare
	public Tarea actualizar (int id, Tarea datos) {
		Tarea tarea = buscar(id);
		if(tarea==null) {
			return null;
		}
		// son los datos que se traen desde postman
		tarea.setDescripcion(datos.getDescripcion());
		tarea.setFechaLimite(datos.getFechaLimite());
		tarea.setCostoEstimado(datos.getCostoEstimado());
		tarea.setProyecto(datos.getProyecto());
		
		return tareaRepository.save(tarea);
		
	}

	//eliminar tarea
	public boolean eliminar(int id) {
		Tarea tarea = buscar(id);
		if(tarea==null) {
			return false;
		}
		tareaRepository.deleteById(id);
		return true;
	}
	

}
