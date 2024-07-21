package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.saveDataCSV;

public class CounselingController {
    @FXML
    public TextField probelmId;
    public TextField scid;
    public TextField counselingproblemtitle;
    public TextField creatornamecounseling;
    public TextField counselingproblemtxtfield;
    public DatePicker dateforcounseling;
    public TextField time;


    public void studentLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()) {
            changeScene(event, "/Fxml/login.fxml", "Academiafx");
        }
    }

    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
    }

    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/QuestionForm.fxml","AcademiaFX");
    }
    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","AcademiaFX");
    }

    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/SuggestionForm.fxml","AcademiaFX");
    }

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

    public void counselingproblemsubmit(ActionEvent event) throws IOException {
        String ProblemId = probelmId.getText();
        String Scid = scid.getText();
        String Counselingproblemtitle = counselingproblemtitle.getText();
        String Creatornamecounseling = creatornamecounseling.getText();
        String Counselingproblemtxtfield = counselingproblemtxtfield.getText();
        LocalDate dateForCounseling = dateforcounseling.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateForCounselingString = dateForCounseling.format(formatter);

        String Time = time.getText();

        saveDataCSV("src/main/resources/csv/counselingproblem.csv", ProblemId, Scid, Counselingproblemtitle, Creatornamecounseling, Counselingproblemtxtfield,dateForCounselingString, Time);
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","Academiafx");
    }

}
