package edu.gatech.quiz.helpers;

import edu.gatech.quiz.data.QuizDB;
import java.util.*;

/**
 * CS3300 Project 2
 */
public class QuizSession {
    /*final*/ private List<Question> questions;
    /*final*/ private Map<Question, Option> userAnswers;
    private int score;

    private QuizSession() {
        questions = new ArrayList<Question>();
        userAnswers = new HashMap<Question, Option>();
    }

    public static QuizSession createShortSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();
        List<Question> allQuestions = db.getCategoryQuestions(category);

        int numQuestions = allQuestions.size();
        int cap = numQuestions < 10 ? numQuestions : 10;
        Random r = new Random();
        List<Question> selectQuestions = new ArrayList<>();
        for (int i = 0; i < cap; i++) {
        	int index = r.nextInt(numQuestions);
        	Question selected = allQuestions.get(index);
        	if (!selectQuestions.contains(selected)) {
        		selectQuestions.add(selected);
        	}
        }
        session.setQuestions(selectQuestions);
        session.setScore(0);
        
        return session;
    }

    public static QuizSession createLongSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();

        session.setQuestions(db.getCategoryQuestions(category));
        session.setScore(0);

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
        if (o.isCorrect()) {
        	this.score++;
        }
    }

    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
    	this.score = score;
    }

    public boolean solvedAll() {
        return questions.size() == userAnswers.size();
    }

}
