package fx.studentmanagementsystem.controller.Teacher;

import fx.studentmanagementsystem.Utils.DialogsUtil;
import fx.studentmanagementsystem.model.QuestionReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static fx.studentmanagementsystem.Uses.changeScene;

public class QuestionReportsController {
    @FXML
    private TableView<QuestionReport> questionreportstable;
    @FXML
    private TableColumn<QuestionReport, String> studentemailcolumn;
    @FXML
    private TableColumn<QuestionReport, String> questionsanswer;
    @FXML
    private TableColumn<QuestionReport, Integer> score;

    @FXML
    public void initialize() {
        studentemailcolumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        questionsanswer.setCellValueFactory(new PropertyValueFactory<>("questionsAnswers"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        questionreportstable.setItems(loadQuestionReports());
    }

    private ObservableList<QuestionReport> loadQuestionReports() {
        ObservableList<QuestionReport> questionReports = FXCollections.observableArrayList();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("QuestionFormAnswer.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the format is: email,QA1,QA2,...,Score:X
                String email = data[0];
                String score = data[data.length - 1].split(":")[1].trim();
                String questionsAnswers = String.join(",", Arrays.copyOfRange(data, 1, data.length - 1));
                questionReports.add(new QuestionReport(email, questionsAnswers, Integer.parseInt(score)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionReports;
    }

    public void teacherLogout(javafx.event.ActionEvent event) {
        if(DialogsUtil.showLogoutConfirmation()) {
            try {
                changeScene(event, "/Fxml/chooseUser-Signup.fxml", "AcademiaFX");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void studentdetailsteacherdash(javafx.event.ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/teacher/TeacherDashboard.fxml", "AcademiaFX");
    }

    public void quesitonreportsclicked(javafx.event.ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/teacher/QuestionReports.fxml", "AcademiaFX");

    }
}
