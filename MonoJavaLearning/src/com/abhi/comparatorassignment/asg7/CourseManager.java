package com.abhi.comparatorassignment.asg7;

import java.util.*;

public class CourseManager {

    List<Participant> participantList = new ArrayList<>();
    private Set<String> participantIds = new HashSet<>();
    private Map<String, List<Participant>> trackMap = new HashMap<>();
    private Queue<Participant> waitingQueue = new LinkedList<>();

    public void addParticipant(Participant participant) {

        if (participantIds.contains(participant.getParticipantId())) {
            System.out.println("Duplicate participant not allowed");
            return;
        }

        participantList.add(participant);
        participantIds.add(participant.getParticipantId());

        trackMap.putIfAbsent(participant.getTrack(), new ArrayList<>());
        trackMap.get(participant.getTrack()).add(participant);

        System.out.println("Participant added");
    }

    public void addToWaitingList(Participant participant) {
        waitingQueue.add(participant);
        System.out.println("Added to waiting list");
    }

    public void processWaitingList() {

        if (waitingQueue.isEmpty()) {
            System.out.println("No waiting participants");
            return;
        }

        Participant p = waitingQueue.poll();
        p.attend();
    }

    public void displayAllParticipants() {

        for (Participant p : participantList) {
            System.out.println(p.getParticipantId() + " " + p.name + " " + p.track);
        }
    }

    public void displaySortedById() {

        List<Participant> list = new ArrayList<>(participantList);
        Collections.sort(list);

        for (Participant p : list) {
            System.out.println(p.getParticipantId());
        }
    }

    public void displaySortedByName() {

        List<Participant> list = new ArrayList<>(participantList);
        list.sort(new NameComparator());

        for (Participant p : list) {
            System.out.println(p.name);
        }
    }

    public void displaySortedByTrack() {

        List<Participant> list = new ArrayList<>(participantList);
        list.sort(new TrackComparator());

        for (Participant p : list) {
            System.out.println(p.getParticipantId() + " Track: " + p.track);
        }
    }

    public void displayByTrack(String track) {

        List<Participant> list = trackMap.get(track);

        if (list == null) {
            System.out.println("No participants found");
            return;
        }

        for (Participant p : list) {
            System.out.println(p.getParticipantId());
        }
    }

    public void removeParticipantsByTrack(String track) {

        Iterator<Participant> iterator = participantList.iterator();

        while (iterator.hasNext()) {
            Participant p = iterator.next();

            if (p.getTrack().equals(track)) {
                iterator.remove();
                participantIds.remove(p.getParticipantId());
                waitingQueue.remove(p);
            }
        }
    }
}
