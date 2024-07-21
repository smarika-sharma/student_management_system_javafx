package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class ManageStaffController {

    public TableView managestafftable;
    public TableColumn staffID;
    public TableColumn staffusername;
    public TableColumn staffemail;
    public TableColumn staffgender;
    public TableColumn stafffaculty;

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

    public void addstaffadmin(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/StaffForm.fxml", "Add Staff");
    }

    public void adminupdatestaff(ActionEvent event) {
    }
}
