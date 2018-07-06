package edu.gatech.quiz.helpers;

import java.util.*;

/**
 * CS3300 Project 2
 */
public class Question {
    final private String category;
    final private String bodyText;
    final private List<Option> options;
    final private String explanation;

    public Question(String category, String bodyText, List<Option> options,
            String explanation) {
        this.category = category;
        this.bodyText = bodyText;
        this.options = options;
        this.explanation = explanation;
    }

    public String getCategory() {
        return category;
    }

    public String getBodyText() {
        return bodyText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String getExplanation() {
        return explanation;
    }
}
