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

import java.io.*;
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

        loadStaffData();
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

    public void deletestaff(ActionEvent event) {
        Staff selectedStaff = managestafftable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            managestafftable.getItems().remove(selectedStaff);

            String fileName;
            if ("Librarian".equals(selectedStaff.getStaffFaculty())) {
                fileName = "librarian_credentials.csv";
            } else if ("Admission Officer".equals(selectedStaff.getStaffFaculty())) {
                fileName = "admission_officer_credentials.csv";
            } else {
                System.out.println("Unexpected faculty value. No action taken.");
                return;
            }

            List<Staff> allStaff = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (!fields[4].equals(selectedStaff.getStaffEmail())) {
                        allStaff.add(new Staff(fields[0], fields[1], fields[4], fields[2], fields[3]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
                for (Staff staff : allStaff) {
                    pw.println(staff.getStaffID() + "," + staff.getStaffUsername() + "," + staff.getStaffGender() + "," + staff.getStaffFaculty() + "," + staff.getStaffEmail());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void updatetable(ActionEvent event) {
//        loadStaffData();
//    }

    private void loadStaffData() {
    managestafftable.getItems().clear(); // Clear existing data
    CSVReader reader = new CSVReader();
    List<Staff> librarians = reader.readStaffFromCSV("librarian_credentials.csv");
    List<Staff> officers = reader.readStaffFromCSV("admission_officer_credentials.csv");
    List<Staff> allStaff = new ArrayList<>(librarians);
    allStaff.addAll(officers);
    ObservableList<Staff> data = FXCollections.observableArrayList(allStaff);
    managestafftable.setItems(data); // Reload data
}

    public class CSVReader {
    public List<Staff> readStaffFromCSV(String fileName) {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fileName.contains("librarian") && fields.length == 6) {
                    staffList.add(new Staff(fields[0], fields[1], fields[4], fields[2], fields[3]));
                } else if (fileName.contains("admission_officer") && fields.length == 6) {
                    staffList.add(new Staff(fields[0], fields[1], fields[4], fields[2], fields[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}
}
