package fx.studentmanagementsystem.controller.Staff;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import fx.studentmanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fx.studentmanagementsystem.Uses.changeScene;
import static fx.studentmanagementsystem.Uses.loadStudentsFromDirectory;

public class AdmissionofficerDashboardController {

    @FXML
    public Button studentForm;
    @FXML
    public TableView <Student> studentTableView;
    @FXML
    public TableColumn <Student, String> studentNameColumn;
    @FXML
    public TableColumn<Student, String> facultyColumn;
    @FXML
    public TableColumn<Student, String> emailColumn;

    // Student Form

    @FXML
    public void studentFormclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/StudentForm.fxml", "StudentForm");
    }


    @FXML
    public void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<Student> studentList = FXCollections.observableArrayList(loadStudentsFromDirectory());
        studentTableView.setItems(studentList);
    }

    // for logout
    public void admissionofficerLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
