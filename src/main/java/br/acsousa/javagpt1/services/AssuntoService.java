package br.acsousa.javagpt1.services;

import java.util.ArrayList;
import java.util.List;

import br.acsousa.javagpt1.dtos.AssuntoDTO;
import br.acsousa.javagpt1.entities.Assunto;
import br.acsousa.javagpt1.entities.Materia;
import br.acsousa.javagpt1.repositories.AssuntoRepository;
import br.acsousa.javagpt1.repositories.MateriaRepository;
import br.acsousa.javagpt1.services.exceptions.EntityAlreadyExisting;
import br.acsousa.javagpt1.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AssuntoService {

	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public AssuntoDTO findById(Long id) {
		var assunto = assuntoRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Assunto id (" + id + ") não encontrado.")
		);

		return modelMapper.map(assunto, AssuntoDTO.class);
	}
	
	public List<AssuntoDTO> getAll(){
		List<Assunto> assuntoList = assuntoRepository.findAll();
		List<AssuntoDTO> assuntoListDTO = new ArrayList<>();

		for (Assunto assunto : assuntoList) {
			assuntoListDTO.add(modelMapper.map(assunto, AssuntoDTO.class));
		}

		return assuntoListDTO;
	}

	public List<AssuntoDTO> getAssuntoByMateriaId(Long materiaId) {
		List<Assunto> assuntoList = assuntoRepository.findByMateriaId(materiaId);
		List<AssuntoDTO> assuntoListDTO = new ArrayList<>();

		for (Assunto assunto : assuntoList) {
			assuntoListDTO.add(modelMapper.map(assunto, AssuntoDTO.class));
		}

		return assuntoListDTO;
	}
	
	public AssuntoDTO save(AssuntoDTO assuntoDTO) {
		Materia materia = materiaRepository.findById(assuntoDTO.getMateria().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Matéria id " +
				assuntoDTO.getMateria().getId() + " não encontrada"));

		if(assuntoRepository.findByMateriaIdAndNome(
				assuntoDTO.getMateria().getId(),
				assuntoDTO.getNome())
				!= null) {
			throw new EntityAlreadyExisting("Assunto (" + assuntoDTO.getNome() +
					"), já existe para a matéria (" + materia.getNome() + ")");
		}

		Assunto assunto = modelMapper.map(assuntoDTO, Assunto.class);
		assunto.setMateria(materia);

		assunto = assuntoRepository.save(assunto);
		
		return modelMapper.map(assunto, AssuntoDTO.class);
	}
}
