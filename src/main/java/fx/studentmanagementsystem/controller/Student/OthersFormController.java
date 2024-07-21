package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.saveDataCSV;

public class OthersFormController {
    public void studentLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()) {
            changeScene(event, "/Fxml/login.fxml", "Academiafx");
        }
    }

    @FXML
    public TextField probelmId;
    public TextField scid;
    public TextField othersproblemtitle;
    public TextField creatornameothersform;
    public TextField otheraproblemtxtfield;


    private String generateRandomProblemId() {
        // Current time in milliseconds ensures uniqueness
        long currentTimeMillis = System.currentTimeMillis();

        // Generate a random number to append
        Random random = new Random();
        int randomNumber = random.nextInt(9999); // Random number between 0 and 9999

        // Combine current time and random number for the ID
        return "PID-" + currentTimeMillis + "-" + randomNumber;
    }
    private int generateRandomScid(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min; // Generate a random number within the specified range
    }

    public void initialize() {
        probelmId.setText(generateRandomProblemId());
        scid.setText(String.valueOf(generateRandomScid(1000, 9999)));
    }



    public void othersproblemsubmit(ActionEvent event) throws IOException {
        String ProblemId = probelmId.getText();
        String Scid = scid.getText();
        String Othersproblemtitle = othersproblemtitle.getText();
        String Creatornameothersform = creatornameothersform.getText();
        String Othersformproblemtxtfield = otheraproblemtxtfield.getText();

        saveDataCSV("src/main/resources/csv/othersproblem.csv", ProblemId, Scid, Othersproblemtitle, Creatornameothersform, Othersformproblemtxtfield);
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","Academiafx");
    }


    @FXML
    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
    }

    @FXML
    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/QuestionForm.fxml","AcademiaFX");
    }

    @FXML
    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","AcademiaFX");
    }

    @FXML
    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/SuggestionForm.fxml","AcademiaFX");
    }
}
