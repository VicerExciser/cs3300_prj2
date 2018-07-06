import edu.gatech.quiz.data.QuizDB;
import edu.gatech.quiz.ui.QuizCLI;

/**
 * CS3300 Project 2
 */
public class QuizApplication {
    public static void main(String[] args) {
        QuizDB db = new QuizDB();
        new QuizCLI(db).run();
    }
}
