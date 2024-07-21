package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import fx.studentmanagementsystem.model.Staff;
import fx.studentmanagementsystem.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.changeScene;



public class ManageTeacherController implements Initializable {
    public TableColumn deleteButton;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeacherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));


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

    public void deleteTeacherInfo(ActionEvent actionEvent) {
    }

    public class CSVReader {
        public List<Staff> readStaffFromCSV(String fileName) {
            List<Staff> staffList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    // Assuming librarian_credentials.csv has 5 fields and admission_officer_credentials.csv has 6 fields
                    if ((fileName.contains("librarian_credentials.csv") && fields.length == 6) ||
                            (fileName.contains("admission_officer_credentials.csv") && fields.length == 6)) {
                        Staff staff = new Staff(fields[0], fields[1], fields[4], fields[2], fields[3]);
                        staffList.add(staff);
                    } else {
                        System.out.println("Skipping line due to incorrect number of fields: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return staffList;
        }
    }
}


