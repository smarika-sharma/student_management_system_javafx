package fx.studentmanagementsystem.controller.Student;

public class SessionManager {
    private static String studentEmail;

    public static String getStudentEmail() {
        return studentEmail;
    }

    public static void setStudentEmail(String email) {
        SessionManager.studentEmail = email;
    }
}
