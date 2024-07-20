package fx.studentmanagementsystem.controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

import static fx.studentmanagementsystem.Uses.*;

public class ProblemFormController {

    @FXML
    public void studentLogout(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/login.fxml", "Academiafx");
    }

    @FXML
public void navigateToInfrastructureForm(MouseEvent event) throws IOException {
      changeSceneMouse(event, "/Fxml/Student/InfrastructureForm.fxml", "Academiafx");
}
    @FXML
    public void navigateToProblemForm(MouseEvent event) throws IOException {
        changeSceneMouse(event, "/Fxml/Student/Problem.fxml", "Academiafx");
    }


}
