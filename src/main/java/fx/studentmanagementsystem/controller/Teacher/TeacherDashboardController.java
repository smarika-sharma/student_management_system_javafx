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

public class TeacherDashboardController {

    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, String> studentNameColumn;
    @FXML
    private TableColumn<Student, String> facultyColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    public void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<Student> studentList = FXCollections.observableArrayList(loadStudentsFromDirectory());
        studentTableView.setItems(studentList);
    }

    public List<Student> loadStudentsFromDirectory() {
        List<Student> students = new ArrayList<>();
        File directory = new File("Student_info");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    String studentID = scanner.nextLine().split(": ")[1];
                    String username = scanner.nextLine().split(": ")[1];
                    String phoneNumber = scanner.nextLine().split(": ")[1];
                    String email = scanner.nextLine().split(": ")[1];
                    String faculty = scanner.nextLine().split(": ")[1];
                    String gender = scanner.nextLine().split(": ")[1];
                    students.add(new Student(studentID, username, phoneNumber, email, faculty, gender));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    // for logout
    public void teacherLogout(ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}