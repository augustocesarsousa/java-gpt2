package br.acsousa.javagpt1;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JavaGpt1Application {

	private static final String API_KEY = "";
	private static final String MODEL = "gpt-3.5-turbo";

	public static void main(String[] args) {
		SpringApplication.run(JavaGpt1Application.class, args);

		OpenAiService service = new OpenAiService(API_KEY);

		ChatMessage chat = new ChatMessage("user", "Defina a linguagem Java");

		ChatCompletionRequest request = ChatCompletionRequest.builder()
				.messages(List.of(chat))
				.n(1)
				.temperature(1.0)
				.maxTokens(50)
				.model(MODEL)
				.build();

		System.out.println(service.createChatCompletion(request).getChoices().get(0).getMessage().getContent());

	}

}
