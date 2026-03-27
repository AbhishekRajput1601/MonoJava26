package com.abhi.comparatorassignment.asg7;

import java.util.Comparator;

public class TrackComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant p1, Participant p2) {
        return p1.track.compareTo(p2.track);
    }
}