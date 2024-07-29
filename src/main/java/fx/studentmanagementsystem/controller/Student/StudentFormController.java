package fx.studentmanagementsystem.controller.Student;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static fx.studentmanagementsystem.Uses.*;

public class StudentFormController implements Initializable {

    @FXML
    public TextField studentID;

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

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

    @FXML
    public Label Error_label;
    @FXML
    public Label studentIdError;
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
    public void backToAdmissionOfficerDashboard(javafx.scene.input.MouseEvent event) {
        try {
            changeSceneMouse(event,"/Fxml/Staff/AdmissionOfficerDashboard.fxml","AdmissionOfficer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genders);
        faculty.getItems().addAll(faculties);
        backButton.setOnMouseClicked(event -> {
            try {
                backButton(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void backButton(MouseEvent event) throws IOException {
        changeSceneMouse(event,"/Fxml/Staff/AdmissionOfficerDashboard.fxml","AcademiaFX");
    }

    public void onSubmitButtonClicked(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }
        String Email = email.getText();
        if (emailExists(new File("Student_credentials.csv"), Email)) {
            error(Error_label,"Email already exists. Please use another email.", true);
            return;
        }
        else {
            Error_label.setTextFill(Color.GREEN);
            Error_label.setText("Successfully added a new student.");
            //String Email = student_email_field.getText();
            String StudentID = studentID.getText();
            String Password = password.getText();
            String Firstname = firstName.getText();
            String Lastname = lastName.getText();
            String Phonenumber = phoneNumber.getText();
            String Faculty = faculty.getValue();
            String Gender = gender.getValue();
            writeCredentialsToCSV(Email, Password);
            try {
                writestudentinfoTotxt(StudentID, Firstname, Lastname, Phonenumber, Email, Faculty, Gender);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //pausing scene before showing login screen after successful signup
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(stop -> {
            // Load login page after successful signup
            try {
                changeScene(event,"/Fxml/Staff/AdmissionOfficerDashboard.fxml","AcademiaFX");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }
    private boolean isInputValid() {
        boolean valid = true;

        // Clear all previous error messages
        clearAllErrors();

        //Validate student ID
        if(studentID.getText().trim().isEmpty()){
            error(studentIdError, "Required", true);
            valid = false;
        }else if (!Pattern.matches("^03\\d{4,7}$", studentID.getText().trim())){
            error(studentIdError,"Invalid format.", true);
            valid = false;
        }

        // Validate first name
        if (username.getText().trim().isEmpty()) {
            error(usernameError, "Required.", true);
            valid = false;
        }

        // Validate first name
        if (firstName.getText().trim().isEmpty()) {
            error(firstNameError, "Required.", true);
            valid = false;
        }

        // Validate last name
        if (lastName.getText().trim().isEmpty()) {
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
        error(studentIdError,"",false);
        error(usernameError,"",false);
        error(firstNameError, "", false);
        error(lastNameError, "", false);
        error(genderError, "", false);
        error(facultyError, "", false);
        error(emailError, "", false);
        error(phoneNumberError, "", false);
        error(passwordError, "", false);
    }


    private void writeCredentialsToCSV(String Email, String Password) {
        try (FileWriter fw = new FileWriter("Student_credentials.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(STR."\{Email},\{Password}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writestudentinfoTotxt(String StudentID, String firstname,String lastname,String phonenumber,String email,String faculty,String gender) throws IOException {
        //make a student info directory
        File directory = new File("Student_info");

        String filename = email + ".txt";
        File studentfile = new File(directory,filename);

        try(PrintWriter pw = new PrintWriter(studentfile)) {
            pw.println("Student ID: " + StudentID);
            pw.println("Username: " + firstname +" "+ lastname);
            //pw.println("Last Name: " + lastname);
            pw.println("Phone Number: " + phonenumber);
            pw.println("Email: " + email);
            pw.println("Faculty: " + faculty);
            pw.println("Gender: " + gender);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
