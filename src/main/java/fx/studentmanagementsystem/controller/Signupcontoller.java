package fx.studentmanagementsystem.controller;

import fx.studentmanagementsystem.controller.Student.StudentIDGenerator;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.util.Duration;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.changeSceneMouse;
import static java.lang.StringTemplate.STR;

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
    private boolean emailExists(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader("Student_credentials.csv"))) {
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
    @FXML
    public void signup_btn_clicked(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }
        String Email = student_email_field.getText();
        if (emailExists(Email)) {
            Error_label.setText("Email already exists. Please use another email.");
            return;
        }
        if (!isPasswordMatching()) {
            Error_label.setText("Password and confirm password do not match");
        } else {
            Error_label.setText("Signup successful");
            //String Email = student_email_field.getText();
            String Password = student_pass_field.getText();
            String Firstname = student_firstname.getText();
            String Lastname = student_lastname.getText();
            String Phonenumber = student_phonenumber.getText();
            String Faculty = chooseFaculty.getValue();
            String Gender = chooseGender.getValue();
            writeCredentialsToCSV(Email, Password);
            try {
                writestudentinfoTotxt(Firstname, Lastname, Phonenumber, Email, Faculty, Gender);
            }catch (FileNotFoundException e) {
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
                changeScene(event,"/Fxml/login.fxml","AcademiaFX");
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

    private void writestudentinfoTotxt(String firstname,String lastname,String phonenumber,String email,String faculty,String gender) throws IOException {
        //make a student info directory
        File directory = new File("Student_info");
        if (!directory.exists()){
            boolean isDirCreated=directory.mkdir();
            if (!isDirCreated){
                throw new IOException("Failed to create directory");
                }
        }
        String studentID = StudentIDGenerator.generateID();
        String filename = email + ".txt";
        File studentfile = new File(directory,filename);

        try(PrintWriter pw = new PrintWriter(studentfile)) {
            pw.println("Student ID: " + studentID);
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

    @FXML
    public void backToChooseUser(javafx.scene.input.MouseEvent event) {
        try {
            changeSceneMouse(event,"/Fxml/chooseUser-Signup.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void signuppageloginbtn(ActionEvent event) {
        try {
            changeScene(event,"/Fxml/login.fxml","AcademiaFX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

