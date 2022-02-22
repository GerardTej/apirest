package com.candidato.cliente.api.controller;

import com.candidato.cliente.api.entity.Persona;
import com.candidato.cliente.api.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gerardo
 */
@RestController
@RequestMapping("/persona")
public class PersonaCtrl {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @GetMapping
    public List<Persona> getAll(){
        return personaRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) throws Exception{
    	Optional<Persona> model = personaRepository.findById(id);
    	if(!model.isPresent()) {
    		return new ResponseEntity<>("Ningun registro con ese ID",HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(model.get(),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Persona model){
    	try {
			model = personaRepository.save(model);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al guardar",HttpStatus.NOT_FOUND);
		}
    	
    	return new ResponseEntity<>(model,HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id")Integer id, @RequestBody Persona model){
    	Optional<Persona> modelFromDB = personaRepository.findById(id);
    	if(!modelFromDB.isPresent()) {
    		return new ResponseEntity<>("Ningun registro con ese ID",HttpStatus.NOT_FOUND);
    	}
    	Persona personaFromDB = modelFromDB.get();
    	personaFromDB = personaRepository.saveAndFlush(model);
    	
    	return new ResponseEntity<>(personaFromDB,HttpStatus.OK);
    }
    
}
