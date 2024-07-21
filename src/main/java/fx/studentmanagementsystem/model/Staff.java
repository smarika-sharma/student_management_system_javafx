package fx.studentmanagementsystem.model;

public class Staff {
    private String staffID;
    private String staffUsername;
    private String staffEmail;
    private String staffGender;
    private String staffFaculty;

    public Staff(String staffID, String staffUsername, String staffEmail, String staffGender, String staffFaculty) {
        this.staffID = staffID;
        this.staffUsername = staffUsername;
        this.staffEmail = staffEmail;
        this.staffGender = staffGender;
        this.staffFaculty = staffFaculty;
    }

    // Getters
    public String getStaffID() { return staffID; }
    public String getStaffUsername() { return staffUsername; }
    public String getStaffEmail() { return staffEmail; }
    public String getStaffGender() { return staffGender; }
    public String getStaffFaculty() { return staffFaculty; }
}
