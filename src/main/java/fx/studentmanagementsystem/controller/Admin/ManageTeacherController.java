package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import fx.studentmanagementsystem.model.Staff;
import fx.studentmanagementsystem.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.*;


public class ManageTeacherController implements Initializable {
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, String> TeacherId;
    @FXML
    private TableColumn<Teacher, String> username;
    @FXML
    private TableColumn<Teacher, String> email;
    @FXML
    private TableColumn<Teacher, String> faculty;
    @FXML
    private TableColumn<Teacher, String> gender;
    @FXML
    private TableColumn<Teacher, Button> deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeacherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        deleteButton.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        loadTeacherData();

        for (Teacher teacher : teacherTable.getItems()) {
            teacher.getDeleteButton().setOnAction(event -> {
                try {
                    deleteTeacher("teacher_credentials.csv", teacher.getId());
                    teacherTable.setItems(readTeacherFromCSV("teacher_credentials.csv"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public void adminmanagestudent(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ManageStudents.fxml", "Manage Students");
    }

    public void adminmanagestaff(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ManageStaff.fxml", "Manage Staff");
    }

    public void adminmanageteacher(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ManageTeacher.fxml", "Manage Teacher");
    }


    public void adminLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/adminLogin.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTeacher(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/TeacherForm.fxml", "Add Teacher");
    }
    public void adminhome(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminDashboard.fxml", "Admin Dashboard");
    }

    private void loadTeacherData(){
        teacherTable.getItems().clear(); // Clear existing data
        teacherTable.setItems(readTeacherFromCSV("teacher_credentials.csv"));
    }

}


