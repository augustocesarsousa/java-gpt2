package br.acsousa.javagpt1.controllers;

import br.acsousa.javagpt1.models.AnswerRequest;
import br.acsousa.javagpt1.models.Assunto;
import br.acsousa.javagpt1.models.QuestionRequest;
import br.acsousa.javagpt1.models.QuestionResponse;
import br.acsousa.javagpt1.services.ApiGpmService;
import br.acsousa.javagpt1.services.AssuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGpmController {

	@Autowired
	private ApiGpmService apiGpmService;
	
	@Autowired
	private AssuntoService assuntoService;
	
	@PostMapping("/chat/question")
	public String getQuestion(@RequestBody QuestionRequest request){
		
		Assunto assunto = assuntoService.getById(request.getIdAssunto());
		
		String prompt = "Me faça uma pergunta de " + assunto.getMateria().getNome() + " sobre " + assunto.getNome() + ". O fomrato da resposta deve ser Pergunta: mais a descrição da pergunta.";
		
		QuestionResponse response = apiGpmService.generate(prompt);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	@PostMapping("/chat/answer")
	public String getAnswers(@RequestBody AnswerRequest request){

		QuestionResponse response = apiGpmService.generate(request.getPrompt());
		
		return response.getChoices().get(0).getMessage().getContent();
	}
}
