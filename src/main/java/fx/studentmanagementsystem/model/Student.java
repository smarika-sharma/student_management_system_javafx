package fx.studentmanagementsystem.model;

public class Student {
    private String studentID;
    private String username;
    private String phoneNumber;
    private String email;
    private String faculty;
    private String gender;

    // Constructor
    public Student(String studentID, String username, String phoneNumber, String email, String faculty, String gender) {
        this.studentID = studentID;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faculty = faculty;
        this.gender = gender;
    }

    // Getters and Setters
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
