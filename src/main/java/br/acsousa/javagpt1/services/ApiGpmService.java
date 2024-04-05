package br.acsousa.javagpt1.services;

import java.util.List;

import br.acsousa.javagpt1.models.QuestionResponse;
import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

@Service
public class ApiGpmService {

	private static final String API_KEY = System.getenv("CHAT_GPT_API_KEY");;
	private static final String MODEL = "gpt-3.5-turbo";
	private static final String ROLE = "user";
	
	public QuestionResponse generate(String prompt) {
		
		OpenAiService service = new OpenAiService(API_KEY);
		
		ChatMessage chat = new ChatMessage(ROLE, prompt);
		
		ChatCompletionRequest request = ChatCompletionRequest.builder()
				.messages(List.of(chat))
				.n(null)
				.temperature(0.5)
				.model(MODEL)
				.build();
		
		QuestionResponse response  = new QuestionResponse();
		response.setChoices(service.createChatCompletion(request).getChoices());
		
		return response;
	}
}
