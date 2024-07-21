package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class ManageTeacherController {
    public void addTeacher(ActionEvent actionEvent) {
    }

    public void updateTeacherInfo(ActionEvent actionEvent) {
    }

    public void deleteTeacher(ActionEvent actionEvent) {
    }
    @FXML
    public void adminmanagestudent(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ManageStudents.fxml", "Manage Students");
    }

    public void adminmanagestaff(ActionEvent event) {
    }

    public void adminmanageteacher(ActionEvent event) {
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
