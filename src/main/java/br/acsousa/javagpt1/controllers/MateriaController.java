package br.acsousa.javagpt1.controllers;

import java.util.List;

import br.acsousa.javagpt1.dtos.MateriaDTO;
import br.acsousa.javagpt1.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/materias")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;
	
	@PostMapping
	public ResponseEntity<MateriaDTO> save(@RequestBody MateriaDTO materiaDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(materiaService.save(materiaDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<MateriaDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(materiaService.getAll());
	}
}
