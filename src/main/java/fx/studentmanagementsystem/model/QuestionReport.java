package fx.studentmanagementsystem.model;

public class QuestionReport {
    private String studentEmail;
    private String questionsAnswers;
    private int score;

    public QuestionReport(String studentEmail, String questionsAnswers, int score) {
        this.studentEmail = studentEmail;
        this.questionsAnswers = questionsAnswers;
        this.score = score;
    }

    // Getters and Setters
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(String questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
