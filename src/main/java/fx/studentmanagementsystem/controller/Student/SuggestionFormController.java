package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.DialogsutilLogout.DialogsUtil;
import javafx.event.ActionEvent;

import java.io.IOException;

import static fx.studentmanagementsystem.Uses.changeScene;

public class SuggestionFormController {

    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/StudentDashboard.fxml", "AcademiaFX");
    }

    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/QuestionForm.fxml", "AcademiaFX");
    }

    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/ProblemForm.fxml", "AcademiaFX");
    }

    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/SuggestionForm.fxml", "AcademiaFX");
    }

    public void studentLogout(ActionEvent event) {
        if (DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/login.fxml", "Academiafx");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
