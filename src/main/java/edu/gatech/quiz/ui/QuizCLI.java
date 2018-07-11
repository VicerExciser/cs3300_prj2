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
            String prompt = "\nPlease enter an answer ID from 1 to " + options.size() + ": ";
            int response = clampIndexResponse(1, options.size(), prompt, cli);
            Option answer = options.get(response);
            session.setUserAnswer(q, answer);
            System.out.println(barrier2);
    	}
    	int missed = session.getQuestions().size() - session.getScore();
    	System.out.println("\n*** Your score in this session:  " + session.getScore() + " correct / " + missed + " missed ***");
    	System.out.println("\nPlease choose any of the following options: \n\t1) See explanations for missed"
    			+ "\n\t2) Take a new quiz\n\t3) See Dashboard\n\t4) Quit\n");
    	int choice = clampIndexResponse(1, 4, "Please choose your option {1 | 2 | 3 | 4}: ", cli);
    	if (choice == 0) {
    		displayExplanations();
    	} else if (choice == 1) {
    		this.run();
    		return;
    	} else if (choice == 2) {
    		displayDashboard();
    	}
    	System.out.println("Thanks for playing GeeQuiz!\n" + barrier1);
    	return;
    }
    
    private boolean determineShortSession(Scanner cli) {
    	String errMsg = "Please enter 'L' for a (L)ong quiz session or 'S' for only 10 (S)hort questions: ";
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
    	String prompt = "Please enter a category ID from 1 to " + categoryNumber + ": ";
    	int categoryId = clampIndexResponse(1, categoryNumber, prompt, cli);
    	System.out.println(barrier2);
    	return categories.get(categoryId); 
    }
    
// ======================  TODO  ===================================
    private void displayExplanations() {
    	
    }
    
    private void displayDashboard() {
    	
    }
    
 // ======================  TODO  ===================================
    
    // min and max are both exclusive, so (min=1, max=10) will return an index of [0 : 9]
    private int clampIndexResponse(int min, int max, String prompt, Scanner cli) {
    	int response = 0;
    	do {
        	try {
            	System.out.print(prompt);
            	response = Integer.parseInt(cli.next());
        	} catch (NumberFormatException nfe) {
        		response = 0;
        	}
        } while (response < min || response >= max);
    	return response - 1;
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
