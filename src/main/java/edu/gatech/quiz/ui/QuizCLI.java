package edu.gatech.quiz.ui;

import edu.gatech.quiz.data.QuizDB;
import edu.gatech.quiz.helpers.*;
import java.util.*;

/**
 * CS3300 Project 2
 * This class can be left AS IS. No implementation needed.
 *
 * Extra Credit will be given if the interface (IE this class) is implemented correctly.
 */
public class QuizCLI {
    final private QuizDB db;
    final private String barrier1 = "======================================================================\n";
//    final private String barrier2 = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
    final private String barrier2 = "______________________________________________________________________\n";

    public static void main(String[] args) {
       // Provide menu driven quiz access via CLI
    	new QuizCLI(new QuizDB()).run();
    }

    public QuizCLI(QuizDB db) {
        this.db = db;
    }

    public void run() {
    	Scanner cli = new Scanner(System.in);
    	// start session (determine if long or short)
    	boolean shortType = determineShortSession(cli);
    	String category = displayCategoryMenu(cli, db);
    	QuizSession session = shortType ? QuizSession.createShortSession(category, db)
    			: QuizSession.createLongSession(category, db);
    	System.out.println("\t\t||  " + category + "  ||\n");
    	// prompt question (for each session.getQuestions())
    	// take answer input then call session.setUserAnswer(question, answer);
    	int questionNumber = 1;
    	for (Question q : session.getQuestions()) {
    		List<Option> options = q.getOptions();
            String text = q.getBodyText();
            System.out.println("Question " + questionNumber + ": ");
            questionNumber++;
//            System.out.println(Arrays.toString(text.split("(?s)(?<=\\G.{50})") + "\n"));
            if (text.length() > 80) {
	            for (String line : splitTextEqually(text, 80)) {
	            	System.out.println(" " + line);
	            }
	            System.out.println();
            } else {
            	System.out.println(" " + text + "\n");
            }
            int optionNumber = 1;
            for (Option o : options) {
            	System.out.println("\t" + optionNumber + ".)  " + o.getOptionText());
            	optionNumber++;
            }
            int response = 0;	//cli.nextInt() - 1;
            String prompt = "\nPlease enter an answer ID from 1 to " + options.size() + ": ";
            do {
            	try {
	            	System.out.print(prompt);
	            	response = Integer.parseInt(cli.next()) - 1;
            	} catch (NumberFormatException nfe) {
            		response = 0;
            	}
            } while (response < 1 || response >= options.size());
            Option answer = options.get(response);
            session.setUserAnswer(q, answer);
            System.out.println(barrier2);
    	}
    	int missed = session.getQuestions().size() - session.getScore();
    	System.out.println("\n Final score:  " + session.getScore() + " correct / " + missed + " missed");
    	System.out.println("Thanks for playing GeeQuiz!\n" + barrier1);
    }
    
    private boolean determineShortSession(Scanner cli) {
//    	String sessString = "(L)ong session or (S)hort session? ";
    	String errMsg = "Please enter 'L' for a (L)ong quiz session or 'S' for only 10 (S)hort questions: ";
//    	System.out.println(sessString);
    	System.out.print(errMsg);
    	String sessType = cli.next();
    	while (!"L".equalsIgnoreCase(sessType) && !"S".equalsIgnoreCase(sessType)) {
    		System.out.print(errMsg);
    		sessType = cli.next();
    	}
    	return "S".equalsIgnoreCase(sessType);
    }
    
    private String displayCategoryMenu(Scanner cli, QuizDB db) {
    	String welcome = "Welcome to GeeQuiz, a Computer Science quiz app!\n\nPlease choose a question category from the list below:";
    	List<String> categories = db.getQuestionCategories();
    	int categoryNumber = 1;
    	System.out.println(barrier1 + welcome);
    	for (String cat : categories) {
    		String prefix = categoryNumber < 10 ? (" \t" + categoryNumber + ")   ") : ("\t" + categoryNumber + ")  ");
    		System.out.println(prefix + cat);
    		categoryNumber++;
    	}
    	categoryNumber--;
    	String errMsg = "category ID must be an integer from 1 to " + categoryNumber + ": ";
    	System.out.print("Please enter a category ID: ");
    	int categoryId = 0;
    	do {
    		try {
    			categoryId = Integer.parseInt(cli.next());
    			if (categoryId < 1 || categoryId >= categoryNumber) {
    				throw new NumberFormatException();
    			}
    		}
    		catch (NumberFormatException nfe) {
    			System.out.print(errMsg);
    			categoryId = 0;
    		}
    	} while (categoryId < 1 || categoryId >= categoryNumber);
    	System.out.println(barrier2);
    	return categories.get(categoryId - 1); 
    }
    
    // TODO: Maybe clean up how this handles specially formatted question body segments
    private List<String> splitTextEqually(String text, int size) {
        List<String> ret = new ArrayList<>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }
}
