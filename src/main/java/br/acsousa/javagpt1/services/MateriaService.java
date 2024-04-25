package br.acsousa.javagpt1.services;

import java.util.ArrayList;
import java.util.List;

import br.acsousa.javagpt1.entities.Materia;
import br.acsousa.javagpt1.dtos.MateriaDTO;
import br.acsousa.javagpt1.repositories.MateriaRepository;
import br.acsousa.javagpt1.services.exceptions.EntityAlreadyExisting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public Materia getById(Long id) {
		return materiaRepository.findById(id).get();
	}
	
	public List<MateriaDTO> getAll(){
		List<Materia> materiaList = materiaRepository.findAll();
		List<MateriaDTO> materiaListDTO = new ArrayList<>();

		for (Materia materia : materiaList) {
			materiaListDTO.add(modelMapper.map(materia, MateriaDTO.class));
		}

		System.out.println(materiaList);
		return materiaListDTO;
	}
	
	public MateriaDTO save(MateriaDTO materiaDTO) {
		if(materiaRepository.findByNome(materiaDTO.getNome()) != null) {
			throw new EntityAlreadyExisting("Já existe uma matéria com essa descrição (" + materiaDTO.getNome() + ")");
		}

		Materia materia = modelMapper.map(materiaDTO, Materia.class);

		materia = materiaRepository.save(materia);
		materiaDTO = modelMapper.map(materiaRepository.save(materia), MateriaDTO.class);

		return materiaDTO;
	}
}
