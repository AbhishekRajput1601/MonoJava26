package solid.assignment.srp;

public class StudentService {

    public String calculateGrade(int marks) {
        if (marks >= 80) return "A";
        else if (marks >= 60) return "B";
        else if (marks >= 40) return "C";
        else return "Fail";
    }
}
