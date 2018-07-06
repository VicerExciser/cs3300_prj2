package edu.gatech.quiz.data;

import edu.gatech.quiz.helpers.*;

import java.sql.*;
import java.util.*;

/**
 * CS3300 Project 2
 */
public class QuizDB {
    public List<String> getQuestionCategories() {
        Connection c = getConnection();
        List<String> categories = new ArrayList<>();

        // Add code here

        return categories;
    }

    public List<Question> getCategoryQuestions(String category) {
        Connection c = getConnection();
        List<Question> questions = new ArrayList<>();

        // Add code here

        return questions;
    }

    private Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:quiz.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }
}