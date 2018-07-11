package edu.gatech.quiz.data;

import edu.gatech.quiz.helpers.*;

import java.sql.*;
import java.util.*;

/**
 * CS3300 Project 2
 *
 * This class manages the data source
 * (i.e., connections with the database and fetching info).
 */
public class QuizDB {
    public List<String> getQuestionCategories() {
        Connection c = getConnection();
        List<String> categories = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

//        String query = "SELECT title FROM categories";
        String catQuery = "SELECT * FROM categories";
        try {
            stmt = c.prepareStatement(catQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {
                categories.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tearDown(rs, stmt, c);
        }

        return categories;
    }

    public List<Question> getCategoryQuestions(String category) {
        Connection c = getConnection();
        List<Question> questions = new ArrayList<>();


//        List<String[]> qDataSets = new ArrayList<>();
//        String[] qData = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int catId;

//        int answId;

        String catIdQuery = "SELECT id FROM categories WHERE title=?";
        String questQuery = "SELECT id, body, explanation FROM questions " +
                "WHERE category_id=?";
        String answQuery = "SELECT correct, body FROM answers WHERE " +
                "question_id=?";

        try {
            stmt = c.prepareStatement(catIdQuery);
            stmt.setString(1, category);
            catId = stmt.executeQuery().getInt("id");

//            qData = new String[3];
            stmt = c.prepareStatement(questQuery);
            stmt.setInt(1, catId);
            rs = stmt.executeQuery();
            // boolean hasMoreSets = stmt.execute();
            // while (hasMoreSets) { rs = stmt.getResultSet(); ... }
            while (rs.next()) {
                int questId = rs.getInt("id");
                String qBody = rs.getString("body");
//                qData[0] = rs.getString("body");
                String qExpl  = rs.getString("explanation");
//                qData[2] = rs.getString("explanation");

                List <Option> answers = new ArrayList<>();
                PreparedStatement subStmt = c.prepareStatement(answQuery);
                subStmt.setInt(1, questId);
                ResultSet subRs = subStmt.executeQuery();

                while (subRs.next()) {
                    String aBody = subRs.getString("body");
                    boolean aCorrect = subRs.getBoolean("correct");
                    answers.add(new Option(aBody, aCorrect));
                }

                Question q = new Question(category, qBody, answers, qExpl);
                questions.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tearDown(rs, stmt, c);
        }

        return questions;
    }

    private void tearDown(ResultSet rs, PreparedStatement stmt, Connection c) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (c != null)
                c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:quiz.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }
}