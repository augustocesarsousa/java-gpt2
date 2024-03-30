package br.acsousa.javagpt1.services;

import br.acsousa.javagpt1.models.QuestionRequest;
import br.acsousa.javagpt1.models.QuestionResponse;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private static final String API_KEY = "";
    private static final String MODEL = "gpt-3.5-turbo";
    private static final String ROLE = "user";

    public QuestionResponse generateAnswer(QuestionRequest question) {

        OpenAiService service = new OpenAiService(API_KEY);

        ChatMessage chat = new ChatMessage(ROLE, question.getPrompt());

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(List.of(chat))
                .n(question.getN())
                .temperature(question.getTemperature())
                .maxTokens(10)
                .model(MODEL)
                .build();

        QuestionResponse response = new QuestionResponse();
        response.setChoices(service.createChatCompletion(request).getChoices());

        return response;
    }

}
