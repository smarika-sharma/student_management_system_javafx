package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.HashSet;
import java.io.IOException;


import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.saveDataCSV;

public class QuestionFormController {
    private static final HashSet<String> submittedEmails = new HashSet<>();

    @FXML
    public Label Question1Label,Question2Label,Question3Label;

    @FXML
    public RadioButton Q1O1, Q1O2, Q1O3, Q1O4, Q2O1, Q2O2, Q2O3, Q2O4, Q3O1, Q3O2, Q3O3, Q3O4;
    private final ToggleGroup Q1Group = new ToggleGroup(), Q2Group = new ToggleGroup(), Q3Group = new ToggleGroup();
    public Label ErrorSubmitting;

    public QuestionFormController() {
        // Constructor remains empty in this case
    }

    @FXML
    public void initialize(){
        ErrorSubmitting.setText("");
        // Initialize ToggleGroups and assign them to RadioButtons
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
    }

    @FXML
    protected void handleSubmitAction(ActionEvent event) {
        String userEmail = SessionManager.getStudentEmail();
        if (submittedEmails.contains(userEmail)) {
            ErrorSubmitting.setText("You have already submitted your answers.");
            // Display an error message to the user
            //System.out.println("You have already submitted your answers.");
            return;
        }
        RadioButton selectedRadioButton = (RadioButton) Q1Group.getSelectedToggle();
        String answerQ1 = selectedRadioButton != null ? selectedRadioButton.getText() : "No selection";
        RadioButton selectedRadioButtonQ2 = (RadioButton) Q2Group.getSelectedToggle();
        String answerQ2 = selectedRadioButtonQ2 != null ? selectedRadioButtonQ2.getText() : "No selection";
        RadioButton selectedRadioButtonQ3 = (RadioButton) Q3Group.getSelectedToggle();
        String answerQ3 = selectedRadioButtonQ3 != null ? selectedRadioButtonQ3.getText() : "No selection";

        try {
            saveDataCSV("Question_bank.csv",userEmail, Question1Label.getText(), answerQ1, Question2Label.getText(), answerQ2, Question3Label.getText(), answerQ3);
            submittedEmails.add(userEmail);
        }catch (IOException e){
            e.printStackTrace();
        }
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
