# CS 3300 Project 2 (TDD Project)

## Deliverables
### Assignment 1  (7/12)
* Provided:
  * Story/task cards
  * Junit test cases
* Expected:
  * Code that makes the test cases
pass

### Assignment 2  (7/15)
* Provided:
  * Story cards
* Expected:
  * Task cards
  * JUnit test cases
  
### Assignment 3  (7/17)
* Provided:
  * Test cases (ours, possibly augmented)
* Expected:
  * Code that makes the test cases
pass

##
## Assignment 1
### Story Card
  "The user launches the GeeQuiz application to
test their programming knowledge. The
application allows them to select a quiz
category and asks a series of questions. It
lets them choose one of the 4 answers for
each question and shows them their score at
the end. 
There are 2 kinds of sessions: short and long.
Short session is supposed to be randomly
chosen questions from a given category, up to
10 questions. If the category has > 10
questions, choose 10 random ones; otherwise,
choose them all.
Long session includes all questions from a
given category."

### Data
* All data is stored in a SQLite Database(quiz.db)
* The database and wrapper code will be provided
as an attachment to the assignment on TSquare
* Use basic JDBC (PreparedStatements) to
access the database from your code

### Task Cards
* Task 1: 
  * "Develop a class (QuizDB) that
manages the data source (i.e., connections
with the database and fetches info)."
* Task 2: 
  * "Develop a class (QuizSession) that
manages the current quiz session and keeps
track of the user’s score."

* Keeping in mind the story card and
the corresponding tasks, do the
following:
  * Write code that makes the provided
test cases pass (the test cases are
already provided)
  * The team leader must upload the ZIP
archive of the project’s source by the
deadline.


##
## Assignment 2
### Story Cards
* Story Card 1:
  * "The user can select a category to take the quiz in the 
console app. A short quiz session consists of a series of
10 randomly picked questions. At the end of the quiz,
the user can see their score for the session."
* Story Card 2:
  * "The app keeps track of the user’s responses in a
session and shows the explanations of their wrong
answers."
* Story Card 3:
  * "The app shows a dashboard to report the status of the
user’s cumulative scores (correct answers) over
different categories."

### Task Card
* The teams shall:
 1. Break down the story cards in (development) task
cards
 2. Write a set of JUnit test cases that cover the
additional functionality
     1. Use your judgment and the guidelines provided in class to
decide when the set of test cases is adequate
     2. The set of test cases should include (1) tests for all
relevant methods of the new classes you plan to develop for
this second iteration, and (2) tests that target the new or
modified functionality in existing classes
     3. The set of test cases should include at least two JUnit test cases for the front-end you will develop
 3. By the due date, submit your project with the
following:
     1. The task cards, which you will put in a newly-created directory called TaskCards (at the root level)
     2. The test cases, which you will put in a newly-created file
called QuizExraTests.java
