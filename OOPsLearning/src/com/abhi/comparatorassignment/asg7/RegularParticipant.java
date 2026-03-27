package com.abhi.comparatorassignment.asg7;

public class RegularParticipant extends Participant {

    private String courseType;

    public RegularParticipant(String participantId, String name, String track, String courseType) {
        super(participantId, name, track);
        this.courseType = courseType;
    }

    @Override
    public void attend() {
        System.out.println("Regular participant attending: " + participantId);
    }
}
