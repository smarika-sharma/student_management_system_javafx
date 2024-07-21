package fx.studentmanagementsystem.controller.Student;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;

import static fx.studentmanagementsystem.Uses.*;

public class ProblemFormController {



    public void StudentDashboardClicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/StudentDashboard.fxml","AcademiaFX");
    }

    public void questionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/QuestionForm.fxml","AcademiaFX");
    }

    @FXML
    public void problemformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/ProblemForm.fxml","AcademiaFX");
    }

    @FXML
    public void counselingclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/CounselingForm.fxml","AcademiaFX");  //CounselingForm
    }

    @FXML
    public void otherproblemclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/OthersForm.fxml","AcademiaFX");  //OthersForm
    }

    @FXML
    public void sugessionformclicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/SuggestionForm.fxml","AcademiaFX");
    }

    public void studentLogout(ActionEvent event) throws IOException {
        if(DialogsUtil.showLogoutConfirmation()) {
            changeScene(event, "/Fxml/login.fxml", "Academiafx");
        }
    }

    @FXML
    public void infrastructureproblemClicked(ActionEvent event) throws IOException {
        changeScene(event,"/Fxml/Student/InfrastructureForm.fxml","AcademiaFX");
    }




}
