package com.abhi.comparatorassignment.asg7;

public class CorporateParticipant extends Participant {

    private String companyName;

    public CorporateParticipant(String participantId, String name, String track, String companyName) {
        super(participantId, name, track);
        this.companyName = companyName;
    }

    @Override
    public void attend() {
        System.out.println("Corporate participant attending: " + participantId);
    }
}
