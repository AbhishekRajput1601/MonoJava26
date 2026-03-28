package com.abhi.interfaceLearning.assignment.asg4;

public class OnlineQuiz implements ExamEvaluator {

    private final double marks;

    public OnlineQuiz(double marks) {
        this.marks = marks;
    }

    public double evaluateMarks() {
        return marks;
    }

    public String calculateGrade(double marks) {

        if (marks >= 90)
            return "A";

        if (marks >= 75)
            return "B";

        if (marks >= 50)
            return "C";

        return "Fail";
    }
}