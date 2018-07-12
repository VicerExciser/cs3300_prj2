import edu.gatech.quiz.data.QuizDB;
import edu.gatech.quiz.ui.QuizCLI;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.List;
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

    @Test
    public void testClampIndexResponse_invalidInput() {
        userInputMock.provideLines("0", "100", "meow", "3");
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
        userInputMock.provideLines("0", "15", "meow", "2");
        String answer = "C++ Programming Mock Tests";
        assertEquals(answer, app.displayCategoryMenu(new Scanner(System.in)));
    }

    @Test
    public void testDetermineShortSession_true() {
        userInputMock.provideLines("Z", "A", "C", "H", "7", "meow", "S");
        assertTrue(app.determineShortSession(new Scanner(System.in)));
    }

    @Test
    public void testDetermineShortSession_false() {
        userInputMock.provideLines("Z", "A", "C", "H", "7", "meow", "L");
        assertFalse(app.determineShortSession(new Scanner(System.in)));
    }
}
