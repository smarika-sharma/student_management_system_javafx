package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fx.studentmanagementsystem.Uses.changeScene;

public class QuestionFormController {
    @FXML
    public Label Question1Label, Question2Label, Question3Label;
    @FXML
    public RadioButton Q1O1, Q1O2, Q1O3, Q1O4, Q2O1, Q2O2, Q2O3, Q2O4, Q3O1, Q3O2, Q3O3, Q3O4;
    private final ToggleGroup Q1Group = new ToggleGroup(), Q2Group = new ToggleGroup(), Q3Group = new ToggleGroup();
    public Label ErrorSubmitting;

    private final List<String[]> questions = new ArrayList<>();
    private final String[] correctAnswers = new String[3]; // Assuming correct answers are at index 2 of each question

    public QuestionFormController() {
        // Constructor remains empty
    }

    @FXML
    public void initialize() throws IOException {
        Q1O1.setToggleGroup(Q1Group);
        Q1O2.setToggleGroup(Q1Group);
        Q1O3.setToggleGroup(Q1Group);
        Q1O4.setToggleGroup(Q1Group);

        Q2O1.setToggleGroup(Q2Group);
        Q2O2.setToggleGroup(Q2Group);
        Q2O3.setToggleGroup(Q2Group);
        Q2O4.setToggleGroup(Q2Group);

        Q3O1.setToggleGroup(Q3Group);
        Q3O2.setToggleGroup(Q3Group);
        Q3O3.setToggleGroup(Q3Group);
        Q3O4.setToggleGroup(Q3Group);

        loadQuestionsFromCSV("Question_bank.csv");
        displayQuestions();
        ErrorSubmitting.setText("");
    }

    private void loadQuestionsFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                questions.add(values);
            }
            Collections.shuffle(questions); // Randomize the list of questions
        }
    }

    private void displayQuestions() {
        // Display first three questions
        for (int i = 0; i < 3; i++) {
            String[] question = questions.get(i);
            correctAnswers[i] = question[2]; // Assuming the correct answer is the second option
            switch (i) {
                case 0:
                    Question1Label.setText(question[0]);
                    Q1O1.setText(question[1]);
                    Q1O2.setText(question[2]);
                    Q1O3.setText(question[3]);
                    Q1O4.setText(question[4]);
                    break;
                case 1:
                    Question2Label.setText(question[0]);
                    Q2O1.setText(question[1]);
                    Q2O2.setText(question[2]);
                    Q2O3.setText(question[3]);
                    Q2O4.setText(question[4]);
                    break;
                case 2:
                    Question3Label.setText(question[0]);
                    Q3O1.setText(question[1]);
                    Q3O2.setText(question[2]);
                    Q3O3.setText(question[3]);
                    Q3O4.setText(question[4]);
                    break;
            }
        }
    }

    @FXML
    protected void handleSubmitAction(ActionEvent event) {
        int score = 0;
        if (((RadioButton) Q1Group.getSelectedToggle()).getText().equals(correctAnswers[0])) score++;
        if (((RadioButton) Q2Group.getSelectedToggle()).getText().equals(correctAnswers[1])) score++;
        if (((RadioButton) Q3Group.getSelectedToggle()).getText().equals(correctAnswers[2])) score++;

        ErrorSubmitting.setText("Your score is: " + score + "/3");
    }




    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/StudentDashboard.fxml", "AcademiaFX");
    }

    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/QuestionForm.fxml", "AcademiaFX");
    }

    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/ProblemForm.fxml", "AcademiaFX");
    }

    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/SuggestionForm.fxml", "AcademiaFX");
    }

    public void studentLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
