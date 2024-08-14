# Simple Java Quiz System

## Overview

This project is a simple Java-based quiz application designed to manage quizzes and facilitate quiz-taking for students. The application has functionalities for both admins and students, including quiz management and quiz-taking features.

## Features

https://github.com/user-attachments/assets/bde101cc-c10b-4250-87d6-f37bac7b99c8



- **Admin Capabilities**:
  - Add multiple MCQ questions to the quiz bank.
  - Each question can have four options and an associated answer key.
  - Ability to manage and save questions in a `quiz.json` file.

- **Student Capabilities**:
  - Take a quiz consisting of 10 randomly selected questions from the quiz bank.
  - Receive a score based on the number of correct answers.
  - Immediate feedback on quiz performance.


### Video preview 
https://drive.google.com/file/d/1Lvdknjy9iFrRp1BWRYNOjDUIN9T6Gyqq/view?usp=sharing 


## Project Structure

- `src/`: Contains the Java source code.
- `resources/`:
  - `users.json`: Contains user credentials and roles.
  - `quiz.json`: Stores quiz questions, options, and answer keys.
- `build.gradle`: Gradle build configuration file.

## Getting Started

### Prerequisites

- Java JDK 22 or later
- Gradle 8.x or later

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/YourUsername/Simple-project-on-Java.git
    cd Simple-project-on-Java
    ```

2. **Build the project using Gradle:**

    ```bash
    ./gradlew build
    ```

3. **Run the application:**

    ```bash
    ./gradlew run
    ```

### Usage

1. **Admin Login:**
   - Username: `admin`
   - Password: `1234`
   - Admins can add questions to the quiz bank and manage existing ones.

2. **Student Login:**
   - Username: `salman`
   - Password: `1234`
   - Students can start the quiz and answer questions.

### Adding Questions

As an admin, you can continuously add questions to the quiz bank until you press 'q'. Here’s a sample of how to add questions:

```text
System:> Input your question
Admin:> Which is not part of system testing?
System: Input option 1:
Admin:> Regression Testing
System: Input option 2:
Admin:> Sanity Testing
System: Input option 3:
Admin:> Load Testing
System: Input option 4:
Admin:> Unit Testing
System: What is the answer key?
Admin:> 4
System:> Saved successfully! Do you want to add more questions? (press s for start and q for quit)
