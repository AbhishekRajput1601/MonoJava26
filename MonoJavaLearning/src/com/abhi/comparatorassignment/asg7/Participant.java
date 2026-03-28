package com.abhi.comparatorassignment.asg7;


public abstract class Participant implements Comparable<Participant> {

    protected String participantId;
    protected String name;
    protected String track;

    public Participant(String participantId, String name, String track) {
        this.participantId = participantId;
        this.name = name;
        this.track = track;
    }

    public String getParticipantId() {
        return participantId;
    }

    public String getTrack() {
        return track;
    }

    public abstract void attend();

    @Override
    public int compareTo(Participant other) {
        return this.participantId.compareTo(other.participantId);
    }
}