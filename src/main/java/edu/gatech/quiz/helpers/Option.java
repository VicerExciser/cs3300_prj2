package edu.gatech.quiz.helpers;

/**
 * CS3300 Project 2
 */
public class Option {
    final private String optionText;
    final private boolean correct;

    public Option(String optionText, boolean correct) {
        this.optionText = optionText;
        this.correct = correct;
    }

    public String getOptionText() {
        return optionText;
    }

    public boolean isCorrect() {
        return correct;
    }
}