package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.Utils.HashEncryption;
import fx.studentmanagementsystem.controller.Student.StudentIDGenerator;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.util.Duration;


import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static fx.studentmanagementsystem.Uses.*;
import static java.lang.StringTemplate.STR;

// controller for handling student signup
public class Signupcontoller implements Initializable {

    // TextFields for student information input
    @FXML
    public TextField student_firstname;
    @FXML
    public TextField student_lastname;
    @FXML
    public TextField student_phonenumber;
    @FXML
    public TextField student_email_field;
    @FXML
    public PasswordField student_pass_field;
    @FXML
    public PasswordField student_confirmpass_field;

    // ChoiceBox for selecting gender
    @FXML
    private ChoiceBox<String> chooseGender;
    private final String[] genders = {"Male", "Female", "Others"};

    // ChoiceBox for selecting faculty
    @FXML
    private ChoiceBox<String> chooseFaculty;
    private final String[] faculties = {"BIHM", "BBA", "BCS"};


    //Labels for errors
    @FXML
    public Label Error_label;
    @FXML
    public Label firstNameError;
    @FXML
    public Label lastNameError;
    @FXML
    public Label genderError;
    @FXML
    public Label facultyError;
    @FXML
    public Label emailError;
    @FXML
    public Label phoneNumberError;
    @FXML
    public Label passwordFieldError;
    @FXML
    public ImageView backto_chooseuser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseGender.getItems().addAll(genders);
        chooseFaculty.getItems().addAll(faculties);
        Error_label.setText("");

        addInputListeners();

    }

    private void addInputListeners() {
        student_firstname.textProperty().addListener((_) -> clearAllErrors());
        student_lastname.textProperty().addListener((_) -> clearAllErrors());
        student_phonenumber.textProperty().addListener((_) -> clearAllErrors());
        student_email_field.textProperty().addListener((_) -> clearAllErrors());
        student_pass_field.textProperty().addListener((_) -> clearAllErrors());
        student_confirmpass_field.textProperty().addListener((_) -> clearAllErrors());
        chooseGender.valueProperty().addListener((_) -> clearAllErrors());
        chooseFaculty.valueProperty().addListener((_) -> clearAllErrors());
    }



    @FXML
    public void signup_btn_clicked(ActionEvent event) throws NoSuchAlgorithmException {

        String firstName = student_firstname.getText();
        String lastName = student_lastname.getText();
        String phoneNumber = student_phonenumber.getText();
        String email = student_email_field.getText();
        String faculty = chooseFaculty.getValue();
        String gender = chooseGender.getValue();
        String password = student_pass_field.getText();
        String confirmPassword = student_confirmpass_field.getText();



        if (!isInputValid()) {
            return;
        }
        String Email = email;
        if (emailExists(new File("Student_credentials.csv"), Email)) {
            Error_label.setText("Email already exists. Please use another email.");
            return;
        }

        if (!isPasswordMatching()) {
            Error_label.setText("Password and confirm password do not match");
            return;
        } else {
            Error_label.setText("Signup successful");
            String hashedPassword = HashEncryption.hashPassword(password);
            // String Email = student_email_field.getText();
            //String Password = student_pass_field.getText();

            writeCredentialsToCSV(Email, hashedPassword);
            try {
                writestudentinfoTotxt(firstName, lastName, phoneNumber, Email, faculty, gender);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Create a pause transition
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(stop -> {
            // Load login page after successful signup
            try {
                changeScene(event, "/Fxml/login.fxml", "AcademiaFX");
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

        // Validate first name
        if (student_firstname.getText().trim().isEmpty()) {
            error(firstNameError, "Required.", true);
            valid = false;
        } else if (student_firstname.getText().trim().length()<3) {
            error(firstNameError,"Too short", true);
            valid = false;
        }

        // Validate last name
        if (student_lastname.getText().trim().isEmpty()) {
            error(lastNameError, "Required.", true);
            valid = false;
        }

        // Validate phone number
        if (student_phonenumber.getText().trim().isEmpty()) {
            error(phoneNumberError, "Phone number is required.", true);
            valid = false;
        } else if (!Pattern.matches("^98\\d{8}$", student_phonenumber.getText().trim())) {
            error(phoneNumberError, "Must be 10 digits starting with 98.", true);
            valid = false;
        }

        // Validate email
        if (student_email_field.getText().trim().isEmpty()) {
            error(emailError, "Email is required.", true);
            valid = false;
        } else if (!(Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$", student_email_field.getText().trim()))){
            error(emailError, "Invalid email format.", true);
            valid = false;
        }

        // Validate gender
        if (chooseGender.getValue() == null) {
            error(genderError, "Invalid", true);
            valid = false;
        }

        // Validate faculty
        if (chooseFaculty.getValue() == null) {
            error(facultyError, "Invalid", true);
            valid = false;
        }

        // Validate password
        if (student_pass_field.getText().trim().isEmpty()) {
            error(passwordFieldError, "Password is required.", true);
            valid = false;
        }

        // Validate confirm password
        if (student_confirmpass_field.getText().trim().isEmpty()) {
            error(Error_label, "Please confirm your password.", true);
            valid = false;
        }
        return valid;
    }

    private void clearAllErrors() {
        error(firstNameError, "", false);
        error(lastNameError, "", false);
        error(genderError, "", false);
        error(facultyError, "", false);
        error(emailError, "", false);
        error(phoneNumberError, "", false);
        error(passwordFieldError, "", false);
        error(Error_label, "", false);
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

    private void writestudentinfoTotxt(String firstname, String lastname, String phonenumber, String email, String faculty, String gender) throws IOException {
        //make a student info directory
        File directory = new File("Student_info");
        if (!directory.exists()) {
            boolean isDirCreated = directory.mkdir();
            if (!isDirCreated) {
                throw new IOException("Failed to create directory");
            }
        }
        String studentID = StudentIDGenerator.generateID();
        String filename = email + ".txt";
        File studentfile = new File(directory, filename);

        try (PrintWriter pw = new PrintWriter(studentfile)) {
            pw.println("Student ID: " + studentID);
            pw.println("Username: " + firstname + " " + lastname);
            //pw.println("Last Name: " + lastname);
            pw.println("Phone Number: " + phonenumber);
            pw.println("Email: " + email);
            pw.println("Faculty: " + faculty);
            pw.println("Gender: " + gender);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void backToChooseUser(javafx.scene.input.MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signuppageloginbtn(ActionEvent event) {
        try {
            changeScene(event, "/Fxml/login.fxml", "AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

