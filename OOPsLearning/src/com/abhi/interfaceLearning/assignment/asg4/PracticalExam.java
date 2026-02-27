package com.abhi.interfaceLearning.assignment.asg4;

public class PracticalExam implements ExamEvaluator {

    private final double marks;

    public PracticalExam(double marks) {
        this.marks = marks;
    }

    public double evaluateMarks() {
        return marks;
    }

    public String calculateGrade(double marks) {

        if (marks >= 85)
            return "A";

        if (marks >= 70)
            return "B";

        if (marks >= 50)
            return "C";

        return "Fail";
    }
}