package fx.studentmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class signupcontoller implements Initializable {

    @FXML
    private Label gender;

    @FXML
    private ChoiceBox<String> chooseGender;
    private String[] genders = {"Male", "Female", "Others"};

    @FXML
    private Label faculty;

    @FXML
    private ChoiceBox<String> chooseFaculty;
    private String[] faculties = {"BIHM", "BBA", "BCS"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseGender.getItems().addAll(genders);
        chooseFaculty.getItems().addAll(faculties);
    }
}
