import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * CS3300 Project 2
 */

public class QuizTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(QuizTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
