package br.acsousa.javagpt1.models;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    private List<ChatCompletionChoice> choices;
}
