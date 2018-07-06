import edu.gatech.quiz.data.QuizDB;
import static org.junit.Assert.*;

import edu.gatech.quiz.helpers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * CS3300 Project 2
 */
public class QuizDBTest {
    QuizDB db;

    @Before
    public void setUp() {
        db = new QuizDB();
    }

    @Test
    public void testCategoryLoading() {
        List<String> categories = db.getQuestionCategories();
        assertEquals(14, categories.size());
        assertTrue(categories.contains(
                "Theory of Computation Mock Tests"));
        assertTrue(categories.contains(
                "C Programming Mock Tests"));
        assertFalse(categories.contains(""));
    }

    @Test
    public void testQuestionLoading1() {
        List<Question> questions = db.getCategoryQuestions(
                "Theory of Computation Mock Tests");
        assertEquals(4, questions.size());
    }

    @Test
    public void testQuestionLoading2() {
        List<Question> questions = db.getCategoryQuestions(
                "C Programming Mock Tests");
        assertEquals(214, questions.size());
    }

    @Test
    public void testQuestionLoading3() {
        List<Question> questions = db.getCategoryQuestions(
                "Invalid Category");
        assertNull(questions);
    }

    @Test
    public void testQuestionValidity1() {
        List<Question> questions = db.getCategoryQuestions(
                "C Programming Mock Tests");
        for (Question q : questions) {
            assertNotEquals("", q.getBodyText());
        }
    }

    @Test
    public void testQuestionValidity2() {
        List<Question> questions = db.getCategoryQuestions(
                "Theory of Computation Mock Tests");
        for (Question q : questions) {
            assertNotEquals("", q.getBodyText());
        }
    }

    @Test
    public void testOptions1() {
        List<Question> questions = db.getCategoryQuestions(
                "C Programming Mock Tests");
        for (Question q : questions) {
            assertNotEquals(0, q.getOptions().size());
        }
    }

    @Test
    public void testOptions2() {
        List<Question> questions = db.getCategoryQuestions(
                "Theory of Computation Mock Tests");
        for (Question q : questions) {
            assertNotEquals(0, q.getOptions().size());
        }
    }

    @Test
    public void testCorrectAnswer1() {
        List<Question> questions = db.getCategoryQuestions(
                "C Programming Mock Tests");
        for (Question q : questions) {
            List<Option> correctAnswers = new ArrayList<Option>();

            for (Option o : q.getOptions()) {
                if (o.isCorrect()) {
                    correctAnswers.add(o);
                }
            }

            assertEquals(1, correctAnswers.size());
        }
    }

    @Test
    public void testCorrectAnswer2() {
        List<Question> questions = db.getCategoryQuestions(
                "Theory of Computation Mock Tests");
        for (Question q : questions) {
            List<Option> correctAnswers = new ArrayList<Option>();

            for (Option o : q.getOptions()) {
                if (o.isCorrect()) {
                    correctAnswers.add(o);
                }
            }

            assertEquals(1, correctAnswers.size());
        }
    }
}
