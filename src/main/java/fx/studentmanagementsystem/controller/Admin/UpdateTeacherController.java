package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import static fx.studentmanagementsystem.Uses.updateTeacherInCSV;

public class UpdateTeacherController {
    @FXML
    public Label Error_Label;
    public TextField teacherFirstName;
    public PasswordField password;
    public TextField teacherLastName;
    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> faculty;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Button submitButton;
    private Teacher selectedTeacher;

    public void initialize() {
        Error_Label.setText("");
        faculty.getItems().addAll("BCS", "BBA", "BIHM");
        gender.getItems().addAll("Male", "Female", "Others");
    }

    public void loadSelectedTeacherData(Teacher teacher) {
        this.selectedTeacher = teacher;
        id.setText(teacher.getId());
        username.setText(teacher.getUsername());
        email.setText(teacher.getEmail());
        faculty.setValue(teacher.getFaculty());
        gender.setValue(teacher.getGender());
        phoneNumber.setText(teacher.getPhoneNumber());
    }

    @FXML
    public void onSubmitClicked(ActionEvent event) {
        String teacherId = id.getText();
        String newUsername = username.getText();
        String newEmail = email.getText();
        String newFaculty = faculty.getValue();
        String newGender = gender.getValue();
        String newPhoneNumber = phoneNumber.getText();
        // Add other fields as necessary

        // Update CSV
        updateTeacherInCSV(teacherId, newUsername, newEmail, newFaculty, newGender, newPhoneNumber);

        // Update TableView
        selectedTeacher.setUsername(newUsername);
        selectedTeacher.setEmail(newEmail);
        selectedTeacher.setFaculty(newFaculty);
        selectedTeacher.setGender(newGender);
        selectedTeacher.setPhoneNumber(newPhoneNumber);
        // Add other fields as necessary

        // Close the update form
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

}