package fx.studentmanagementsystem.controller.Admin;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.*;

/**
 * Controller for the Teacher Form in the Admin module.
 * Handles the creation and validation of teacher entries.
 */
public class TeacherFormController implements Initializable {
    // FXML injected fields for form inputs and controls
    @FXML
    public TextField id;

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
    public Label Error_Label;
    //Handles the back button click to navigate to the Manage Teacher view.
    @FXML
    public void backToManageTeacher(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            changeSceneMouse(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     * Sets up the choice boxes and the back button action.
     */
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
    /**
     * Validates the input fields to ensure they are not empty.
     * @return true if all fields are valid, false otherwise.
     */
    private boolean isInputValid() {
        if (isFieldEmpty(id.getText(), "ID cannot be empty") ||
                isFieldEmpty(username.getText(), "Username cannot be empty") ||
                isFieldEmpty(password.getText(), "Password cannot be empty") ||
                isFieldEmpty(teacherFirstName.getText(), "First name cannot be empty") ||
                isFieldEmpty(teacherLastName.getText(), "Last name cannot be empty") ||
                isFieldEmpty(email.getText(), "Email cannot be empty") ||
                isFieldEmpty(phoneNumber.getText(), "Phone number cannot be empty")) {
            return false;
        }
        return true;
    }
    /**
     * Checks if a given field is empty and sets an error message if so.
     * @param fieldText The text of the field to check.
     * @param errorMessage The error message to display if the field is empty.
     * @return true if the field is empty, false otherwise.
     */
    private boolean isFieldEmpty(String fieldText, String errorMessage) {
        if (fieldText.isEmpty()) {
            Error_Label.setText(errorMessage);
            return true;
        }
        return false;
    }
    /**
     * Checks if the provided email already exists in the system.
     * @param email The email to check.
     * @return true if the email exists, false otherwise.
     */
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
        if (!isInputValid()) {
            return;
        }
        String Email = email.getText();
        if (emailExists(Email)) {
            Error_Label.setText("Email already exists. Please use another email.");
            return;
        }

        String teacherID = id.getText();
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


    public void onSubmitClicked(ActionEvent event) throws IOException {
        if(!isInputValid()){
            return;
        }
        String Email = email.getText();
        if (emailExists(Email)) {
            Error_Label.setText("Email already exists. Please use another email.");
            return;
        }
        else {
            Error_Label.setText("Successfully added a new teacher.");
            saveTeacher();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
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
}