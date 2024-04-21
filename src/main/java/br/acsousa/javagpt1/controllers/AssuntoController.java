package br.acsousa.javagpt1.controllers;

import java.util.List;

import br.acsousa.javagpt1.dtos.AssuntoDTO;
import br.acsousa.javagpt1.entities.Assunto;
import br.acsousa.javagpt1.services.AssuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assuntos")
public class AssuntoController {

	@Autowired
	private AssuntoService assuntoService;

	@GetMapping
	public ResponseEntity<List<AssuntoDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(assuntoService.getAll());
	}

	@GetMapping(value = "/{materiaId}")
	public ResponseEntity<List<AssuntoDTO>> getAssuntoByMateriaId(@PathVariable Long materiaId){
		return ResponseEntity.status(HttpStatus.OK).body(assuntoService.getAssuntoByMateriaId(materiaId));
	}
	
	@PostMapping
	public ResponseEntity<AssuntoDTO> save(@RequestBody AssuntoDTO assuntoDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(assuntoService.save(assuntoDTO));
	}
	
}
