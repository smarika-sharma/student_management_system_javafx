package fx.studentmanagementsystem.controller.Student;


import javafx.fxml.FXML;
import static fx.studentmanagementsystem.Uses.changeScene;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import java.io.IOException;

public class StudentMenuController {
    @FXML
    private Button StudentDashboard;
    @FXML
    private Button QuestionForm;
    @FXML
    private Button ProblemForm;
    @FXML
    private Button SugessionForm;

    @FXML
    protected void StudentDashboardClicked(ActionEvent event) {
        try {
            changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void questionformclicked(ActionEvent event) {

    }
    @FXML
    public void problemformclicked(ActionEvent event) {

    }
    @FXML
    public void sugessionformclicked(ActionEvent event) {

    }
}
