package fx.studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Image icon = new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/images/icon.png")));
        stage.getIcons().add(icon);

        stage.setTitle("AcademiaFX");
        stage.setScene(scene);
        stage.setMinHeight(750);
        stage.setMinWidth(1200);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();


    }

}