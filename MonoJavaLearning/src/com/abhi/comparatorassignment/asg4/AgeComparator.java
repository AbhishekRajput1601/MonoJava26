package com.abhi.comparatorassignment.asg4;

import java.util.Comparator;

public class AgeComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient p1, Patient p2) {
        return p1.age - p2.age;
    }
}