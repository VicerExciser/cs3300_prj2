import edu.gatech.quiz.data.QuizDB;
import edu.gatech.quiz.helpers.Option;
import edu.gatech.quiz.helpers.Question;
import edu.gatech.quiz.helpers.QuizSession;
import edu.gatech.quiz.ui.QuizCLI;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Scanner;


public class QuizExtraTests {
    private QuizDB db;
    private QuizCLI app;

    @Rule
    public final TextFromStandardInputStream userInputMock
            = emptyStandardInputStream();

    @Before
    public void setUp() {
        db = new QuizDB();
        app = new QuizCLI(db);
    }

    //====================================== CLI TESTS ==========================
    @Test
    public void testClampIndexResponse_invalidInput() {
        userInputMock.provideLines("0", "100", "asdf", "3");
        assertEquals(2, app.clampIndexResponse(
                1, 9, "", new Scanner(System.in)));
    }

    @Test
    public void testClampIndexResponse_min() {
        userInputMock.provideLines("-1", "0", "1");
        assertEquals(0, app.clampIndexResponse(
                1, 9, "", new Scanner(System.in)));
    }

    @Test
    public void testClampIndexResponse_max() {
        userInputMock.provideLines("11", "10", "9", "8");
        assertEquals(8, app.clampIndexResponse(
                1, 9, "", new Scanner(System.in)));
    }

    @Test
    public void testDisplayCategoryMenu() {
        userInputMock.provideLines("0", "15", "asdf", "2");
        String answer = "C++ Programming Mock Tests";
        assertEquals(answer, app.displayCategoryMenu(new Scanner(System.in)));
    }

    @Test
    public void testDetermineShortSession_true() {
        userInputMock.provideLines("7", "asdf", "S");
        assertTrue(app.determineShortSession(new Scanner(System.in)));
    }

    @Test
    public void testDetermineShortSession_false() {
        userInputMock.provideLines("7", "asdf", "L");
        assertFalse(app.determineShortSession(new Scanner(System.in)));
    }

    @Test
    public void testQuizScorePlayThrough() {
        // Plays a short session, providing correct answers.
        userInputMock.provideLines("s", "7", "3", "3", "4", "4", "4");
        app.run();
        assertEquals(app.session.getScore(), 4);
    }

    @Test
    public void testMissedQuestionExplanation() {
        // Plays a short session, missing the first question.
        // Chooses to view question 1's explanation.

        String q1ExpectedExplanation = db.getCategoryQuestions(
                "Theory of Computation Mock Tests").get(0).getExplanation();
        String q1ActualExplanation = null;
        userInputMock.provideLines("s", "7", "1", "3", "4", "4", "1", "4");
        app.run();
        for (Question q : app.session.getQuestions()) {
            Option userAnswer = app.session.getUserAnswer(q);
            if (!userAnswer.isCorrect()) {
                q1ActualExplanation = q.getExplanation();
                break;
            }
        }
        assertNotNull(q1ActualExplanation);
        assertEquals(q1ExpectedExplanation, q1ActualExplanation);
    }

    @Test
    public void testCumulativeScoreAfterTwoSessions() {
        // Plays a short session, missing one question the first time.
        // Plays the same quiz again, this time providing a correct answer
        // for the previously missed question.
        String[] userInputs = {"s", "7", "1", "3", "4", "4", "2",      // quiz 1
                               "s", "7", "3", "3", "4", "4", "4" };    // quiz 2
        userInputMock.provideLines(userInputs);
        app.run();
        String actualCumulativeScore = app.dashReport.get("Theory of Computation Mock Tests");
        String expectedCumulativeScore = "4/4";
        assertEquals(expectedCumulativeScore, actualCumulativeScore);
    }
}
