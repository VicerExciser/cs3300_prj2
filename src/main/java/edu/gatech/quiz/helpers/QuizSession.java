package edu.gatech.quiz.helpers;

import edu.gatech.quiz.data.QuizDB;

import java.util.*;

/**
 * CS3300 Project 2
 */
public class QuizSession {
    /*final*/ private List<Question> questions;
    /*final*/ private Map<Question, Option> userAnswers;

    private QuizSession() {
        questions = new ArrayList<Question>();
        userAnswers = new HashMap<Question, Option>();
    }

    public static QuizSession createShortSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();
        List<Question> allQuestions = db.getCategoryQuestions(category);

        // Add code here

        return session;
    }

    public static QuizSession createLongSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();

        // Add code here

        return session;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Map<Question, Option> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Map<Question, Option> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Option getUserAnswer(Question q) {
        return this.userAnswers.get(q);
    }

    public void setUserAnswer(Question q, Option o) {
        this.userAnswers.put(q, o);
    }

    public int getScore() {
        return 0;
    }

    public boolean solvedAll() {
        return false;
    }
}
