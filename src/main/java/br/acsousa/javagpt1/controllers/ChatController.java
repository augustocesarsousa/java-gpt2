package br.acsousa.javagpt1.controllers;

import br.acsousa.javagpt1.models.QuestionRequest;
import br.acsousa.javagpt1.models.QuestionResponse;
import br.acsousa.javagpt1.services.ChatService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService service;

    @PostMapping("/chat")
    public List<String> getAnswers(@RequestBody QuestionRequest request) {

        QuestionResponse response = service.generateAnswer(request);
        List<String> answers = new ArrayList<>();

        for(ChatCompletionChoice choice : response.getChoices()) {
            answers.add((choice.getMessage().getContent()));
        }

        return answers;
    }
}
