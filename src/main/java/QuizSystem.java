import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuizSystem {

    static class User {
        String username;
        String password;
        String role;

        public String getRole() {
            return role;
        }
    }

    static class Question {
        String question;
        String option1;
        String option2;
        String option3;
        String option4;
        int answerkey;
    }

    private static List<User> loadUsers() throws IOException {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        FileReader reader = new FileReader("src/main/resources/users.json");
        return gson.fromJson(reader, userListType);
    }

    private static List<Question> loadQuestions() throws IOException {
        Gson gson = new Gson();
        Type questionListType = new TypeToken<List<Question>>() {}.getType();
        FileReader reader = new FileReader("src/main/resources/quiz.json");
        return gson.fromJson(reader, questionListType);
    }

    private static void saveQuestions(List<Question> questions) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("src/main/resources/quiz.json");
        JsonWriter jsonWriter = new JsonWriter(writer);
        gson.toJson(questions, new TypeToken<List<Question>>() {}.getType(), jsonWriter);
        jsonWriter.close();
    }

    private static void adminLogin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        List<User> users = loadUsers();
        User admin = users.stream()
                .filter(u -> "admin".equals(u.getRole()) && username.equals(u.username) && password.equals(u.password))
                .findFirst()
                .orElse(null);

        if (admin != null) {
            System.out.println("Welcome admin! Please create new questions in the question bank.");
            List<Question> questions = loadQuestions();

            while (true) {
                System.out.print("Input your question: ");
                String questionText = scanner.nextLine();

                System.out.print("Input option 1: ");
                String option1 = scanner.nextLine();
                System.out.print("Input option 2: ");
                String option2 = scanner.nextLine();
                System.out.print("Input option 3: ");
                String option3 = scanner.nextLine();
                System.out.print("Input option 4: ");
                String option4 = scanner.nextLine();

                System.out.print("What is the answer key? (1, 2, 3, or 4): ");
                int answerKey = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Question newQuestion = new Question();
                newQuestion.question = questionText;
                newQuestion.option1 = option1;
                newQuestion.option2 = option2;
                newQuestion.option3 = option3;
                newQuestion.option4 = option4;
                newQuestion.answerkey = answerKey;

                questions.add(newQuestion);

                System.out.println("Saved successfully! Do you want to add more questions? (press s for start and q for quit)");
                String response = scanner.nextLine();
                if ("q".equalsIgnoreCase(response)) {
                    break;
                }
            }
            saveQuestions(questions);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void studentLogin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        List<User> users = loadUsers();
        User student = users.stream()
                .filter(u -> "student".equals(u.getRole()) && username.equals(u.username) && password.equals(u.password))
                .findFirst()
                .orElse(null);

        if (student != null) {
            List<Question> questions = loadQuestions();
            Random random = new Random();
            int score = 0;

            System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
            String start = scanner.nextLine();
            if ("s".equalsIgnoreCase(start)) {
                for (int i = 0; i < 10; i++) {
                    Question q = questions.get(random.nextInt(questions.size()));
                    System.out.println("[Question " + (i + 1) + "] " + q.question);
                    System.out.println("1. " + q.option1);
                    System.out.println("2. " + q.option2);
                    System.out.println("3. " + q.option3);
                    System.out.println("4. " + q.option4);

                    int answer = scanner.nextInt();
                    if (answer == q.answerkey) {
                        score++;
                    }

                    scanner.nextLine(); // Consume newline
                }

                if (score >= 8) {
                    System.out.println("Excellent! You have got " + score + " out of 10");
                } else if (score >= 5) {
                    System.out.println("Good. You have got " + score + " out of 10");
                } else if (score >= 2) {
                    System.out.println("Very poor! You have got " + score + " out of 10");
                } else {
                    System.out.println("Very sorry you are failed. You have got " + score + " out of 10");
                }
            } else {
                System.out.println("Quiz not started.");
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz System");
        System.out.print("Are you an admin or student? (admin/student): ");
        String role = scanner.nextLine();

        if ("admin".equalsIgnoreCase(role)) {
            adminLogin();
        } else if ("student".equalsIgnoreCase(role)) {
            studentLogin();
        } else {
            System.out.println("Invalid role.");
        }
    }
}