package fx.studentmanagementsystem.controller.Teacher;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import fx.studentmanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class TeacherDashboardController {

    @FXML
    private TableView<Student> studentTableView; //student detail tableview
    @FXML
    private TableColumn<Student, String> studentNameColumn; //student name column
    @FXML
    private TableColumn<Student, String> facultyColumn; //student faculty column
    @FXML
    private TableColumn<Student, String> emailColumn; // student email column

    @FXML
    public void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        //loading student details from the Student_info directory
        ObservableList<Student> studentList = FXCollections.observableArrayList(loadStudentsFromDirectory());
        studentTableView.setItems(studentList); //setting student details to the table
    }

    // for logout
    public void teacherLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX"); //directing back to choose user interface when logges out
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //directing to dashboard on clicking student details in teacher menu
    public void studentdetailsteacherdash(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherDashboard.fxml", "Student Details");
    }

    //directing to student report on clicking question report in teacher menu
    public void quesitonreportsclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/QuestionReports.fxml", "Question Reports");
    }
}