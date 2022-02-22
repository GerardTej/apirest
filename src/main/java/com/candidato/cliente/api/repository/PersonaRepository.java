package com.candidato.cliente.api.repository;

import com.candidato.cliente.api.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerardo
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    
}
