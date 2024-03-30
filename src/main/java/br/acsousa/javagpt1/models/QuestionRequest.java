package br.acsousa.javagpt1.models;

import lombok.Data;

@Data
public class QuestionRequest {

    private String prompt;
    private int n;
    private double temperature;
}
