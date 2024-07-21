package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import fx.studentmanagementsystem.controller.Student.StudentIDGenerator;
import fx.studentmanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fx.studentmanagementsystem.Uses.changeScene;

public class ManageStudentsController {
    @FXML
    public TableView<Student> managestudenttable;
    @FXML
    public TableColumn<Student, String> studentID;
    @FXML
    public TableColumn<Student, String> username;
    @FXML
    public TableColumn<Student, String> email;
    @FXML
    public TableColumn<Student, String> gender;
    @FXML
    public TableColumn<Student, String> faculty;

    @FXML
    public void initialize() {

        if (studentID!=null){
            studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        }
        if(username!=null){
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
        }
        if(email!=null) {
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if(gender!=null) {
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        }
        if(faculty!=null){
            faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        }
        if(managestudenttable!=null){
            managestudenttable.setItems(FXCollections.observableArrayList(loadStudentsFromDirectory("Student_info")));
        }
        ObservableList<Student> studentList = FXCollections.observableArrayList(loadStudentsFromDirectory("Student_info"));
        managestudenttable.setItems(studentList);
    }


    public List<Student> loadStudentsFromDirectory(String directoryPath) {
        List<Student> students = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    String studentID = scanner.nextLine().split(": ")[1].trim();
                    String username = scanner.nextLine().split(": ")[1].trim();
                    String phoneNumber = scanner.nextLine().split(": ")[1].trim();
                    String email = scanner.nextLine().split(": ")[1].trim();
                    String faculty = scanner.nextLine().split(": ")[1].trim();
                    String gender = scanner.nextLine().split(": ")[1].trim();
                    students.add(new Student(studentID, username, phoneNumber, email, faculty, gender));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    public void deletestudent(ActionEvent event) {
        Student selectedStudent = managestudenttable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            managestudenttable.getItems().remove(selectedStudent);
            File file = new File("Student_info/" + selectedStudent.getEmail() + ".txt");
            file.delete();
        }
    }

    public void updatestudentinfo(ActionEvent event) {
    }
    @FXML
    public void addstudentadmin(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminStudentForm.fxml", "Add Student");
    }
    @FXML
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

    public void adminhome(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminDashboard.fxml", "Admin Dashboard");
    }


}




