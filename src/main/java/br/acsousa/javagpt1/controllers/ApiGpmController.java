package br.acsousa.javagpt1.controllers;

import br.acsousa.javagpt1.dtos.AssuntoDTO;
import br.acsousa.javagpt1.entities.AnswerRequest;
import br.acsousa.javagpt1.entities.Assunto;
import br.acsousa.javagpt1.entities.QuestionRequest;
import br.acsousa.javagpt1.entities.QuestionResponse;
import br.acsousa.javagpt1.services.ApiGpmService;
import br.acsousa.javagpt1.services.AssuntoService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiGpmController {

	@Autowired
	private ApiGpmService apiGpmService;
	
	@Autowired
	private AssuntoService assuntoService;
	
	@PostMapping("/chat/question")
	public String getQuestion(@RequestBody QuestionRequest request){
		
		AssuntoDTO assuntoDTO = assuntoService.getById(request.getIdAssunto());
		
		String prompt = "Me faça uma pergunta de " + assuntoDTO.getMateria().getNome() + " sobre " + assuntoDTO.getNome() + ". O fomrato da resposta deve ser Pergunta: mais a descrição da pergunta e em seguida a resposta.";
		
		QuestionResponse response = apiGpmService.generate(prompt);
		
		return response.getChoices().get(0).getMessage().getContent();
	}

	@PostMapping("/chat/question-list")
	public List<String> getQuestionList(@RequestBody QuestionRequest request){

		List<String> responses = new ArrayList<>();

		AssuntoDTO assuntoDTO = assuntoService.getById(request.getIdAssunto());

		String prompt = "Me faça uma pergunta de " + assuntoDTO.getMateria().getNome() + " sobre " + assuntoDTO.getNome() + ". O fomrato da resposta deve ser Pergunta: mais a descrição da pergunta e em seguida a resposta.";

		QuestionResponse response = apiGpmService.generate(prompt);

		for(ChatCompletionChoice choice : response.getChoices()){
			responses.add(choice.getMessage().getContent());
		}

		return responses;
	}
	
	@PostMapping("/chat/answer")
	public String getAnswers(@RequestBody AnswerRequest request){

		QuestionResponse response = apiGpmService.generate(request.getPrompt());
		
		return response.getChoices().get(0).getMessage().getContent();
	}
}
