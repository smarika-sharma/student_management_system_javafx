package fx.studentmanagementsystem.controller.Admin;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static fx.studentmanagementsystem.Uses.*;

/**
 * Controller for the Teacher Form in the Admin module.
 * Handles the creation and validation of teacher entries.
 */
public class TeacherFormController implements Initializable {
    // Fields for form inputs and controls
    @FXML
    public TextField teacherId;

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public TextField teacherFirstName;

    @FXML
    public TextField teacherLastName;

    @FXML
    public ChoiceBox<String> faculty;
    private final String[] faculties = {"BIHM", "BBA", "BCS"};

    @FXML
    public TextField email;

    @FXML
    public ChoiceBox<String> gender;
    private final String[] genders = {"Male", "Female", "Others"};

    @FXML
    public TextField phoneNumber;

    @FXML
    public Button submitButton;

    @FXML
    public ImageView backButton;

    //Error labels for all fields
    @FXML
    public Label Error_Label;
    @FXML
    public Label teacherIdError;
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

    //navigate to the Manage Teacher view when back button is clicked.
    @FXML
    public void backToManageTeacher(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            changeSceneMouse(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genders);
        faculty.getItems().addAll(faculties);
        Error_Label.setText("");
        backButton.setOnMouseClicked(event -> {
            try {
                backToManageTeacher(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    //checking if email already exists
    private boolean emailExists(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader("teacher_credentials.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(email)) {
                    return true; // Email exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Email does not exist
    }

    //SAVES the teacher data to a CSV file if input is valid
    public void saveTeacher() throws IOException{


        String teacherID = teacherId.getText();
        String userName = username.getText();
        String teacherFirstname = teacherFirstName.getText();
        String teacherLastname = teacherLastName.getText();
        String teacherEmail = email.getText();
        String teacherPhoneNumber = phoneNumber.getText();
        String Password = password.getText();
        String Faculty = faculty.getValue();
        String Gender = gender.getValue();

        try {
            saveTeacherDataCSV("teacher_credentials.csv", teacherID, userName, teacherFirstname, teacherLastname, teacherEmail, teacherPhoneNumber, Password, Faculty, Gender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Saving data when submit button is clicked.
    public void onSubmitClicked(ActionEvent event) throws IOException {
        if(!isInputValid()){
            return;
        }
        String Email = email.getText();
        if (emailExists(Email)) {
            Error_Label.setText("Email already exists. Please use another email.");
        }
        else {
            Error_Label.setTextFill(Color.GREEN);
            Error_Label.setText("Successfully added a new teacher.");
            saveTeacher();

            //pausing after submit button clicked
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished(e -> {
                try {
                    changeScene(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            pauseTransition.play();
        }
//        saveTeacher();
//        changeScene(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");

    }

    //method to validate the inputs
    private boolean isInputValid() {
        boolean valid = true;

        // Clear all previous error messages
        clearAllErrors();

        //Validate teacher ID
        if(teacherId.getText().trim().isEmpty()){
            error(teacherIdError, "Required", true);
            valid = false;
        }else if(!Pattern.matches("^\\d+$", teacherId.getText().trim())){
            error(teacherIdError, "Invalid", true);
            valid = false;
        }

        // Validate username
        if (username.getText().trim().isEmpty()) {
            error(usernameError, "Required.", true);
            valid = false;
        }

        // Validate first name
        if (teacherFirstName.getText().trim().isEmpty()) {
            error(firstNameError, "Required.", true);
            valid = false;
        }

        // Validate last name
        if (teacherLastName.getText().trim().isEmpty()) {
            error(lastNameError, "Required.", true);
            valid = false;
        }

        // Validate phone number
        if (phoneNumber.getText().trim().isEmpty()) {
            error(phoneNumberError, "Required.", true);
            valid = false;
        } else if (!Pattern.matches("^98\\d{8}$", phoneNumber.getText().trim())) {
            error(phoneNumberError, "Invalid", true);
            valid = false;
        }

        // Validate email
        if (email.getText().trim().isEmpty()) {
            error(emailError, "Email is required.", true);
            valid = false;
        } else if (!(Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$", email.getText().trim()))){
            error(emailError, "Invalid email format.", true);
            valid = false;
        }

        // Validate gender
        if (gender.getValue() == null) {
            error(genderError, "Invalid", true);
            valid = false;
        }

        // Validate faculty
        if (faculty.getValue() == null) {
            error(facultyError, "Invalid", true);
            valid = false;
        }

        // Validate password
        if (password.getText().trim().isEmpty()) {
            error(passwordError, "Password is required.", true);
            valid = false;
        }

        return valid;
    }

    //method to clear all the errors before checking again.
    private void clearAllErrors() {
        error(teacherIdError,"",false);
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