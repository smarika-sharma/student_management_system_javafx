package fx.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.io.IOException;

public class Uses {
    public static Stage getCurrentStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }

    public static void changeScene(ActionEvent event, String sceneName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = getCurrentStage(event);
        stage.setTitle(title);
        stage.setScene(scene);
    }

    //for mouse event
    public static void changeSceneMouse(MouseEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
