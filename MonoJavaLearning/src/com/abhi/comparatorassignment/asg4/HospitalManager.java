package com.abhi.comparatorassignment.asg4;

import java.util.*;

public class HospitalManager {

    private Queue<Patient> patientQueue = new LinkedList<>();
    private Set<String> patientIds = new HashSet<>();
    private Map<String, List<Patient>> departmentMap = new HashMap<>();
    private List<Patient> allPatients = new ArrayList<>();

    public void addPatient(Patient patient) {
        if (patientIds.contains(patient.getPatientId())) {
            System.out.println("Duplicate patient not allowed");
            return;
        }

        patientQueue.add(patient);
        patientIds.add(patient.getPatientId());
        allPatients.add(patient);

        departmentMap.putIfAbsent(patient.getDepartment(), new ArrayList<>());
        departmentMap.get(patient.getDepartment()).add(patient);

        System.out.println("Patient added");
    }

    public void processNextPatient() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue");
            return;
        }

        Patient patient = patientQueue.poll();
        patient.process();
    }

    public void displayAllPatients() {
        for (Patient p : allPatients) {
            System.out.println(p.getPatientId() + " " + p.name + " " + p.age);
        }
    }

    public void displaySortedById() {
        List<Patient> list = new ArrayList<>(allPatients);
        Collections.sort(list);

        for (Patient p : list) {
            System.out.println(p.getPatientId());
        }
    }

    public void displaySortedByAge() {
        List<Patient> list = new ArrayList<>(allPatients);
        list.sort(new AgeComparator());

        for (Patient p : list) {
            System.out.println(p.getPatientId() + " Age: " + p.age);
        }
    }

    public void displayByDepartment(String dept) {
        List<Patient> list = departmentMap.get(dept);

        if (list == null) {
            System.out.println("No patients found");
            return;
        }

        for (Patient p : list) {
            System.out.println(p.getPatientId());
        }
    }

    public void removeOldPatients(int ageLimit) {
        Iterator<Patient> iterator = allPatients.iterator();

        while (iterator.hasNext()) {
            Patient p = iterator.next();

            if (p.age > ageLimit) {
                iterator.remove();
                patientIds.remove(p.getPatientId());
                patientQueue.remove(p);
            }
        }
    }
}
