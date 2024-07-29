package fx.studentmanagementsystem.controller.Admin;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static fx.studentmanagementsystem.Uses.*;

public class StaffFormController implements Initializable {

    //fields for inputs and controls from the form
    @FXML
    public TextField staffid;
    @FXML
    public TextField staffusername;
    @FXML
    public ChoiceBox<String> staffrole;
    @FXML
    private final String[] role = {"Librarian", "Admission Officer"};
    @FXML
    public TextField staffemail;
    @FXML
    public ChoiceBox<String> staffgender;
    @FXML
    private final String[] genders = {"male", "female", "others"};
    @FXML
    public TextField staffFirstName;
    @FXML
    public TextField staffLastName;
    @FXML
    public PasswordField staffpass;
    @FXML
    public TextField staffPhoneNumber;

    @FXML
    public ImageView backbtnstaffform;
    @FXML
    public Label staffcreatedlabel;

    //Error labels
    @FXML
    public Label usernameError;
    @FXML
    public Label passwordError;
    @FXML
    public Label firstNameError;
    @FXML
    public Label lastNameError;
    @FXML
    public Label facultyError;
    @FXML
    public Label emailError;
    @FXML
    public Label genderError;
    @FXML
    public Label phoneNumberError;
    @FXML
    public Label staffIdError;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffgender.getItems().addAll(genders);
        staffrole.getItems().addAll(role);
        staffcreatedlabel.setText("");
        backbtnstaffform.setOnMouseClicked(event -> {
            try {
                backbtnstaffform(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void backbtnstaffform(MouseEvent event) throws IOException {
        changeSceneMouse(event, "/Fxml/Admin/ManageStaff.fxml", "Admin Dashboard");
    }

    public void saveStaffCredentials() {
        String staffId = staffid.getText();
        String username = staffusername.getText();
        String gender = staffgender.getValue();
        String role = staffrole.getValue();
        String email = staffemail.getText();
        String password = staffpass.getText();

        String credentialsFile = role.equals("Librarian") ? "librarian_credentials.csv" : "admission_officer_credentials.csv";

        try {
            fx.studentmanagementsystem.Uses.saveStaffDataCSV(credentialsFile, staffId, username, gender, role, email, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitbutton(ActionEvent event) throws IOException {
        if(!isInputValid()){
            return;
        }
        String Email = staffemail.getText();
        if (emailExists(new File("librarian_credentials.csv"),Email) || emailExists(new File("admission_officer_credentials.csv"),Email)) {
            emailError.setText("Email already exists. Please use another email.");
        }
        else {
            staffcreatedlabel.setTextFill(Color.GREEN);
            staffcreatedlabel.setText("Successfully added a new staff.");
            saveStaffCredentials();

            //pausing after submit button clicked
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished(e -> {
                try {
                    changeScene(event, "/Fxml/Admin/ManageStaff.fxml", "Manage Teacher");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            pauseTransition.play();
        }
    }

    //method to validate the inputs
    private boolean isInputValid() {
        boolean valid = true;

        // Clear all previous error messages
        clearAllErrors();

        //Validate teacher ID
        if(staffid.getText().trim().isEmpty()){
            error(staffIdError, "Required", true);
            valid = false;
        }else if(!Pattern.matches("^\\d+$", staffid.getText().trim())){
            error(staffIdError, "Invalid", true);
            valid = false;
        }

        // Validate username
        if (staffusername.getText().trim().isEmpty()) {
            error(usernameError, "Required.", true);
            valid = false;
        }

        // Validate first name
        if (staffFirstName.getText().trim().isEmpty()) {
            error(firstNameError, "Required.", true);
            valid = false;
        }

        // Validate last name
        if (staffLastName.getText().trim().isEmpty()) {
            error(lastNameError, "Required.", true);
            valid = false;
        }

        // Validate phone number
        if (staffPhoneNumber.getText().trim().isEmpty()) {
            error(phoneNumberError, "Required.", true);
            valid = false;
        } else if (!Pattern.matches("^98\\d{8}$", staffPhoneNumber.getText().trim())) {
            error(phoneNumberError, "Invalid", true);
            valid = false;
        }

        // Validate email
        if (staffemail.getText().trim().isEmpty()) {
            error(emailError, "Email is required.", true);
            valid = false;
        } else if (!(Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$", staffemail.getText().trim()))){
            error(emailError, "Invalid email format.", true);
            valid = false;
        }

        // Validate gender
        if (staffgender.getValue() == null) {
            error(genderError, "Invalid", true);
            valid = false;
        }

        // Validate faculty
        if (staffrole.getValue() == null) {
            error(facultyError, "Invalid", true);
            valid = false;
        }

        // Validate password
        if (staffpass.getText().trim().isEmpty()) {
            error(passwordError, "Password is required.", true);
            valid = false;
        }
        return valid;
    }

    //method to clear all the errors before checking again.
    private void clearAllErrors() {
        error(staffIdError,"",false);
        error(usernameError,"",false);
        error(firstNameError, "", false);
        error(lastNameError, "", false);
        error(genderError, "", false);
        error(facultyError, "", false);
        error(emailError, "", false);
        error(phoneNumberError, "", false);
        error(passwordError, "", false);
    }

}