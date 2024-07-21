package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.saveDataCSV;

public class SuggestionFormController {

    private static final HashSet<String> submittedEmails = new HashSet<>();
    @FXML
    public TextField suggestionTextField;
    public Label suggestionId;

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
        if (DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/login.fxml", "Academiafx");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSubmitClicked(ActionEvent actionEvent) {
        String userEmail = SessionManager.getStudentEmail();
        try {
            saveDataCSV("Suggestions.csv",userEmail,generateRandomSuggestionID(),suggestionTextField.getText());
            submittedEmails.add(userEmail);
            suggestionTextField.clear();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String generateRandomSuggestionID() {
        // Current time in milliseconds ensures uniqueness
        long currentTimeMillis = System.currentTimeMillis();

        // Generate a random number to append
        Random random = new Random();
        int randomNumber = random.nextInt(9999); // Random number between 0 and 9999

        // Combine current time and random number for the ID
        return "SuggestionID:"+ currentTimeMillis  + "-" + randomNumber;
    }


    public void initialize() {
        suggestionId.setText(generateRandomSuggestionID());
    }
}
