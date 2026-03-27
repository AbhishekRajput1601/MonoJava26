package com.abhi.comparatorassignment.asg3;

import java.util.Comparator;

public class MarksComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getAverageMarks(), s1.getAverageMarks());
    }
}