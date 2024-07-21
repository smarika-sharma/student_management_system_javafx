package fx.studentmanagementsystem.controller.Admin;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.changeSceneMouse;

public class AdminStudentFormController implements Initializable {

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
    public void backToAdmissionOfficerDashboard(MouseEvent event) {
        try {
            changeSceneMouse(event, "/Fxml/Admin/ManageStudents.fxml", "AdmissionOfficer");
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
        changeSceneMouse(event, "/Fxml/Admin/ManageStudents.fxml", "AcademiaFX");
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

    public void onSubmitButtonClicked(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }
        String Email = email.getText();
        if (emailExists(Email)) {
            Error_label.setText("Email already exists. Please use another email.");
            return;
        } else {
            Error_label.setText("Successfully added a new student.");
            //String Email = student_email_field.getText();
            String Password = password.getText();
            String Firstname = firstName.getText();
            String Lastname = lastName.getText();
            String Phonenumber = phoneNumber.getText();
            String Faculty = faculty.getValue();
            String Gender = gender.getValue();
            writeCredentialsToCSV(Email, Password);
            try {
                writestudentinfoTotxt(Firstname, Lastname, Phonenumber, Email, Faculty, Gender);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(stop -> {
            // Load login page after successful signup
            try {
                changeScene(event, "/Fxml/Admin/ManageStudents.fxml", "AcademiaFX");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }

    private boolean isInputValid() {
        if (isFieldEmpty(firstName.getText(), "First name cannot be empty")) return false;
        if (isFieldEmpty(lastName.getText(), "Last name cannot be empty")) return false;
        if (isFieldEmpty(phoneNumber.getText(), "Phone number cannot be empty")) return false;
        return !isFieldEmpty(email.getText(), "Email cannot be empty");
    }

    private boolean isFieldEmpty(String fieldText, String errorMessage) {
        if (fieldText.isEmpty()) {
            Error_label.setText(errorMessage);
            return true;
        }
        return false;
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

}
