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
    private static final int SHORT_SESSION_LIMIT = 10;

    private QuizSession() {
        questions = new ArrayList<>();
        userAnswers = new HashMap<>();
        score = 0;
    }

    public static QuizSession createShortSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();
        List<Question> allQuestions = db.getCategoryQuestions(category);

        if (allQuestions.size() > SHORT_SESSION_LIMIT) {
            int numQuestions = allQuestions.size();
            Random r = new Random();
            List<Question> selectQuestions = new ArrayList<>();
            for (int i = 0; i < SHORT_SESSION_LIMIT; i++) {
                int index = r.nextInt(numQuestions);
                Question selected = allQuestions.get(index);
                if (!selectQuestions.contains(selected)) {
                    selectQuestions.add(selected);
                }
            }
            session.questions = selectQuestions;
        } else {
            session.questions = allQuestions;
        }
        return session;
    }

    public static QuizSession createLongSession(String category, QuizDB db) {
        QuizSession session = new QuizSession();

        session.questions = db.getCategoryQuestions(category);

        return session;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Option getUserAnswer(Question q) {
        return userAnswers.get(q);
    }

    public void setUserAnswer(Question q, Option o) {
        userAnswers.put(q, o);
        if (o.isCorrect()) {
        	score++;
        }
    }

    public int getScore() {
        return score;
    }

    public boolean solvedAll() {
        return questions.size() == userAnswers.size();
    }
}
