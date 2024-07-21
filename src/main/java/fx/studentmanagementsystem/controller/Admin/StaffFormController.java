package fx.studentmanagementsystem.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffFormController implements Initializable {

    public TextField staffid;
    public TextField staffusername;
    public ChoiceBox<String> staffrole;
    private final String[] role = {"Librarian", "Admission Officer"};
    public TextField staffemail;
    public ChoiceBox<String> staffgender;
    private final String[] genders = {"male", "female", "others"};

    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffgender.getItems().addAll(genders);
        staffrole.getItems().addAll(role);
    }

    public void saveStaffCredentials() {
        String staffId = staffid.getText();
        String username = staffusername.getText();
        String gender = staffgender.getValue();
        String role = staffrole.getValue();
        String email = staffemail.getText();

        String credentialsFile = role.equals("Librarian") ? "librarian_credentials.csv" : "admission_officer_credentials.csv";

        try {
            fx.studentmanagementsystem.Uses.saveStaffDataCSV(credentialsFile, staffId, username, gender, role, email);
        } catch (IOException e) {
            e.printStackTrace();
            // Consider showing an error message to the user
        }
    }

    public void submitbutton(ActionEvent event) {
        saveStaffCredentials();
    }
}