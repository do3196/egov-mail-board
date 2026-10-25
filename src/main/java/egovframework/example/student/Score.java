package egovframework.example.student;

public class Score {
    private String studentNo;
    private String subject;
    private int score;

    public Score(String studentNo, String subject, int score) {
        this.studentNo = studentNo;
        this.subject = subject;
        this.score = score;
    }

    public String getStudentNo() { return studentNo; }
    public String getSubject() { return subject; }
    public int getScore() { return score; }
}
