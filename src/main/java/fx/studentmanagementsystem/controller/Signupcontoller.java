package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.HelloApplication;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Signupcontoller implements Initializable {

    @FXML
    private ChoiceBox<String> chooseGender;
    private final String[] genders = {"Male", "Female", "Others"};


    @FXML
    private ChoiceBox<String> chooseFaculty;
    private final String[] faculties = {"BIHM", "BBA", "BCS"};
    @FXML
    public TextField student_firstname;
    @FXML
    public TextField student_lastname;
    @FXML
    public TextField student_phonenumber;
    @FXML
    public TextField student_email_field;
    @FXML
    public TextField student_pass_field;
    @FXML
    public TextField student_confirmpass_field;
    @FXML
    public Label Error_label;
    @FXML
    public ImageView backto_chooseuser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseGender.getItems().addAll(genders);
        chooseFaculty.getItems().addAll(faculties);
        Error_label.setText("");

    }
    @FXML
    public void signup_btn_clicked(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }

        if (!isPasswordMatching()) {
            Error_label.setText("Password and confirm password do not match");
        } else {
            Error_label.setText("Signup successful");
            String Email = student_email_field.getText();
            String Password = student_pass_field.getText();
            writeCredentialsToCSV(Email, Password);
        }
        // Create a pause transition
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            // Load login page after successful signup
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/login.fxml")); // replace with your login page path
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }

    private boolean isInputValid() {
        if (isFieldEmpty(student_firstname.getText(), "First name cannot be empty")) return false;
        if (isFieldEmpty(student_lastname.getText(), "Last name cannot be empty")) return false;
        if (isFieldEmpty(student_phonenumber.getText(), "Phone number cannot be empty")) return false;
        return !isFieldEmpty(student_email_field.getText(), "Email cannot be empty");
    }

    private boolean isFieldEmpty(String fieldText, String errorMessage) {
        if (fieldText.isEmpty()) {
            Error_label.setText(errorMessage);
            return true;
        }
        return false;
    }

    private boolean isPasswordMatching() {
        String pass = student_pass_field.getText();
        String confirmPass = student_confirmpass_field.getText();
        return pass.equals(confirmPass);
    }


    private void writeCredentialsToCSV(String Email, String Password) {
        try (FileWriter fw = new FileWriter("Student_credentials.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(STR."\{Email},\{Password}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Stage stage;
    Scene scene;
    @FXML
    public void backToChooseUser(javafx.scene.input.MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/chooseUser-Signup.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signuppageloginbtn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/login.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

