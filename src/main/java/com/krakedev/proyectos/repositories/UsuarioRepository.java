package com.krakedev.proyectos.repositories;

import java.util.Optional;


import com.krakedev.proyectos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public class UsuarioRepository extends JpaRepository<Usuario,Integer>{
	Optional<Usuario> findByUsername(String username);
}
