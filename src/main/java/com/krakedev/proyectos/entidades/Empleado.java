package com.krakedev.proyectos.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {

//	Atributos:
	// Identificador generado automáticamente por la base de datos
	// poner integer para que no de problema cuando se envie null el id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Nombre obligatorio, máximo 100 caracteres
	@Column(nullable = false, length = 100)
	private String nombre;

	// cargo obligatorio, máximo 50 caracteres
	@Column(nullable = false, length = 50)
	private String cargo;

	// Un proyecto puede tener muchas tareas
	// JsonIgnore evita ciclos infinitos al convertir a JSON
	@JsonIgnore
	@OneToMany(mappedBy = "empleados")
	private List<Tarea> tareas;

	public Empleado() {
		super();
	}

	public Empleado(Integer id, String nombre, String cargo, List<Tarea> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cargo = cargo;
		this.tareas = tareas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	
	

}
