package com.abhi.interfaceLearning.assignment.asg4;

public class TheoryExam implements ExamEvaluator {

    private final double marks;

    public TheoryExam(double marks) {
        this.marks = marks;
    }

    public double evaluateMarks() {
        return marks;
    }

    public String calculateGrade(double marks) {

        if (marks >= 80)
            return "A";

        if (marks >= 60)
            return "B";

        if (marks >= 40)
            return "C";

        return "Fail";
    }
}