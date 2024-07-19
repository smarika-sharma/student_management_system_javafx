package fx.studentmanagementsystem;

import com.opencsv.CSVWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;

public class Uses {
    public static Stage getCurrentStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }

    public static FXMLLoader changeScene(ActionEvent event, String sceneName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = getCurrentStage(event);
        stage.setTitle(title);
        stage.setScene(scene);
        return fxmlLoader;
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
    public static void saveDataCSV(String... args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("saveDataCSV requires at least two arguments: filename and one data item.");
        }
        String filename = args[0];
        String[] data = new String[args.length - 1];
        System.arraycopy(args, 1, data, 0, args.length - 1);

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filename, true))) {
            csvWriter.writeNext(data);
            csvWriter.flush();
            //csvWriter.close();
        }

    }
    public static List<String[]> readCSV(String filename) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineItems = line.split(",");
                data.add(lineItems);
            }
        }
        return data;
    }


}
