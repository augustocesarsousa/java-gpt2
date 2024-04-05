package br.acsousa.javagpt1.services;

import java.util.List;

import br.acsousa.javagpt1.models.Assunto;
import br.acsousa.javagpt1.models.Materia;
import br.acsousa.javagpt1.repositories.AssuntoRepository;
import br.acsousa.javagpt1.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AssuntoService {

	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	public Assunto getById(Long id) {
		return assuntoRepository.findById(id).get();
	}
	
	public List<Assunto> getAll(){
		return assuntoRepository.findAll();
	}
	
	public Assunto save(Assunto assunto) {
		
		Materia materia = materiaRepository.findById(assunto.getMateria().getId()).get();
		assunto.setMateria(materia);
		
		return assuntoRepository.save(assunto);
	}
}
