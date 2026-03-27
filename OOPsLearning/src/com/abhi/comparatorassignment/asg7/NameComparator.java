package com.abhi.comparatorassignment.asg7;

import java.util.Comparator;

public class NameComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant p1, Participant p2) {
        return p1.name.compareTo(p2.name);
    }
}