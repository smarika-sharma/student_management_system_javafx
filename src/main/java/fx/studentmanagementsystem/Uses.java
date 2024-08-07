package fx.studentmanagementsystem;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import fx.studentmanagementsystem.model.Student;
import fx.studentmanagementsystem.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Uses {

    //current stage
    public static Stage getCurrentStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }

    //changing scene
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

    //saving data into scv file
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

    //reading data from csv
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


    public static void saveStaffDataCSV(String credentialsFile, String staffId, String username, String gender, String role, String email, String password) throws IOException {
        try (FileWriter fw = new FileWriter(credentialsFile, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(staffId + "," + username + "," + gender + "," + role + "," + email+ "," + password);
        }
    }

    public static void saveTeacherDataCSV(String credentialsFile,String teacherID,String userName, String teacherFirstname, String teacherLastname,String teacherEmail,String teacherPhoneNumber,String Password,String Faculty,String Gender) throws IOException {
        try (FileWriter fw = new FileWriter(credentialsFile, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(teacherID + "," + userName + "," + teacherFirstname + "," + teacherLastname + "," + teacherEmail+ "," + teacherPhoneNumber+ "," + Password+ "," + Faculty+ "," + Gender );
        }
    }

    public static ObservableList<Teacher> readTeacherFromCSV(String fileName) {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

            try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {

                    if (nextLine.length >= 8) {
                        teacherList.add(new Teacher(
                                nextLine[0], // ID
                                nextLine[1], // username
                                nextLine[4], // email
                                nextLine[8], // gender
                                nextLine[7] // faculty
                        ));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return teacherList;
    }

    //deleting user in admin controls
    public static void deleteUser(String fileName, String teacherId) throws IOException {
        List<String[]> csvBody = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (!nextLine[0].equals(teacherId)) {
                    csvBody.add(nextLine);
                }
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeAll(csvBody);
        }
    }
    public static void updateTeacherInCSV(String teacherId, String newUsername, String newEmail, String newFaculty, String newGender, String newPhoneNumber) {
        List<String[]> csvBody = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("teacher_credentials.csv"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(teacherId)) {
                    nextLine[1] = newUsername;
                    nextLine[4] = newEmail;
                    nextLine[7] = newFaculty;
                    nextLine[8] = newGender;
                    nextLine[5] = newPhoneNumber;
                }
                csvBody.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter("teacher_credentials.csv"))) {
            writer.writeAll(csvBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Student> loadStudentsFromDirectory() {
        List<Student> students = new ArrayList<>();
        File directory = new File("Student_info");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    String studentID = scanner.nextLine().split(": ")[1];
                    String username = scanner.nextLine().split(": ")[1];
                    String phoneNumber = scanner.nextLine().split(": ")[1];
                    String email = scanner.nextLine().split(": ")[1];
                    String faculty = scanner.nextLine().split(": ")[1];
                    String gender = scanner.nextLine().split(": ")[1];
                    students.add(new Student(studentID, username, phoneNumber, email, faculty, gender));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    //method to check if the email entered already exists
    public static boolean emailExists(File filename, String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(email)) {
                    return true; // Email exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Email does not exist
    }
    public static void error(Label error, String errorName, boolean visibility){
        error.setText(errorName);
        error.setVisible(visibility);
        error.setTextFill(Color.RED);
    }

}




