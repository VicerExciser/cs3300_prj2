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
