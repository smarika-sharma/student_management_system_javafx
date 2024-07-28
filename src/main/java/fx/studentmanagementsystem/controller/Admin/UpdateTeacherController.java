package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeSceneMouse;
import static fx.studentmanagementsystem.Uses.updateTeacherInCSV;


public class UpdateTeacherController {
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
    @FXML
    public TextField email;
    @FXML
    public ChoiceBox<String> gender;
    @FXML
    public TextField phoneNumber;
    @FXML
    public Label Error_Label;
    @FXML
    public Button submitButton;
    @FXML
    public ImageView backButton;

    private Teacher selectedTeacher;

    public void backToManageTeacher(MouseEvent event) throws IOException {
        changeSceneMouse(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
    }

    public void loadSelectedTeacherData(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
        id.setText(selectedTeacher.getId());
        username.setText(selectedTeacher.getUsername());
        email.setText(selectedTeacher.getEmail());
        faculty.setValue(selectedTeacher.getFaculty());
        gender.setValue(selectedTeacher.getGender());
        //phoneNumber.setText(selectedTeacher.getPhoneNumber());
        // Add other fields as necessary
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
        //selectedTeacher.setUsername(newUsername);
        selectedTeacher.setEmail(newEmail);
        selectedTeacher.setFaculty(newFaculty);
        selectedTeacher.setGender(newGender);
        //selectedTeacher.setPhoneNumber(newPhoneNumber);
        // Add other fields as necessary

        // Close the update form

        //((Stage) submitButton.getScene().getWindow()).close();
    }
}