package fx.studentmanagementsystem.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static fx.studentmanagementsystem.Uses.changeSceneMouse;

public class StaffFormController implements Initializable {

    public TextField staffid;
    public TextField staffusername;
    public ChoiceBox<String> staffrole;
    private final String[] role = {"Librarian", "Admission Officer"};
    public TextField staffemail;
    public ChoiceBox<String> staffgender;
    private final String[] genders = {"male", "female", "others"};
    public ImageView backbtnstaffform;
    public PasswordField staffpass;
    public Label staffcreatedlabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffgender.getItems().addAll(genders);
        staffrole.getItems().addAll(role);
        staffcreatedlabel.setText("");
        backbtnstaffform.setOnMouseClicked(event -> {
            try {
                backbtnstaffform(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void backbtnstaffform(MouseEvent event) throws IOException {
        changeSceneMouse(event, "/Fxml/Admin/ManageStaff.fxml", "Admin Dashboard");
    }

    public void saveStaffCredentials() {
        String staffId = staffid.getText();
        String username = staffusername.getText();
        String gender = staffgender.getValue();
        String role = staffrole.getValue();
        String email = staffemail.getText();
        String password = staffpass.getText();

        String credentialsFile = role.equals("Librarian") ? "librarian_credentials.csv" : "admission_officer_credentials.csv";

        try {
            fx.studentmanagementsystem.Uses.saveStaffDataCSV(credentialsFile, staffId, username, gender, role, email, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitbutton(ActionEvent event) {
        staffcreatedlabel.setText("Staff Created Successfully");
        saveStaffCredentials();
    }
}