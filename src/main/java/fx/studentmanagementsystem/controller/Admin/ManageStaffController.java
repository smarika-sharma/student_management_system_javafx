package fx.studentmanagementsystem.controller.Admin;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import fx.studentmanagementsystem.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fx.studentmanagementsystem.Uses.changeScene;

public class ManageStaffController {


    @FXML
    private TableView<Staff> managestafftable;
    @FXML
    private TableColumn<Staff, String> staffID;
    @FXML
    private TableColumn<Staff, String> staffusername;
    @FXML
    private TableColumn<Staff, String> staffemail;
    @FXML
    private TableColumn<Staff, String> staffgender;
    @FXML
    private TableColumn<Staff, String> stafffaculty;

    public void initialize() {
        staffID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        staffusername.setCellValueFactory(new PropertyValueFactory<>("staffUsername"));
        staffemail.setCellValueFactory(new PropertyValueFactory<>("staffEmail"));
        staffgender.setCellValueFactory(new PropertyValueFactory<>("staffGender"));
        stafffaculty.setCellValueFactory(new PropertyValueFactory<>("staffFaculty"));

        CSVReader reader = new CSVReader();
        List<Staff> librarians = reader.readStaffFromCSV("librarian_credentials.csv");
        List<Staff> officers = reader.readStaffFromCSV("admission_officer_credentials.csv");
        List<Staff> allStaff = new ArrayList<>(librarians);
        allStaff.addAll(officers);

        ObservableList<Staff> data = FXCollections.observableArrayList(allStaff);
        managestafftable.setItems(data);
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

    public void adminhome(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminDashboard.fxml", "Admin Dashboard");
    }

    public void addstaffadmin(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/StaffForm.fxml", "Add Staff");
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
