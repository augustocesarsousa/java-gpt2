package br.acsousa.javagpt1.services;

import java.util.List;

import br.acsousa.javagpt1.models.Materia;
import br.acsousa.javagpt1.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository materiaRepository;
	
	public Materia getById(Long id) {
		return materiaRepository.findById(id).get();
	}
	
	public List<Materia> getAll(){
		return materiaRepository.findAll();
	}
	
	public Materia save(Materia materia) {
		return materiaRepository.save(materia);
	}
}
