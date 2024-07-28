package fx.studentmanagementsystem.model;

import javafx.scene.control.Button;

public class Teacher {
        private String TeacherId;
        private String username;
        private String email;
        private String faculty;
        private String gender;
        private String phoneNumber;
        private Button deleteButton;



        public Teacher(String TeacherId, String username, String email, String faculty, String gender) {
            this.TeacherId = TeacherId;
            this.username = username;
            this.email = email;
            this.faculty = faculty;
            this.gender = gender;
            this.phoneNumber = phoneNumber;

            this.deleteButton = new Button("Delete");
        }


        public String getId() {
            return TeacherId;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getGender() {
            return gender;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }

        public void setId(String TeacherId) {
            this.TeacherId = TeacherId;
        }

        public void username(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }


    public String getPhoneNumber() {
            return phoneNumber;
    }

    public void setUsername(String newUsername) {
    }

    public void setPhoneNumber(String PhoneNumber) {
            this.phoneNumber = phoneNumber;
    }
}
