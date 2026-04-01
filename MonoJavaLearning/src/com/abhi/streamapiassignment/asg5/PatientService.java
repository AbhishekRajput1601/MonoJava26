package com.abhi.streamapiassignment.asg5;

import java.util.*;
import java.util.stream.*;

public class PatientService {

    // 1. admitted patients
    public static void getAdmittedPatients(List<Patient> list) {
        list.stream()
                .filter(Patient::isAdmitted)
                .forEach(System.out::println);
    }

    // 2. group by disease
    public static void groupByDisease(List<Patient> list) {
        Map<String, List<Patient>> map = list.stream()
                .collect(Collectors.groupingBy(Patient::getDisease));

        map.forEach((k, v) -> {
            System.out.println("\nDisease: " + k);
            v.forEach(System.out::println);
        });
    }

    // 3. admitted vs non-admitted (partitioningBy)
    public static void partitionByAdmission(List<Patient> list) {
        Map<Boolean, List<Patient>> map = list.stream()
                .collect(Collectors.partitioningBy(Patient::isAdmitted));

        System.out.println("\nAdmitted Patients:");
        map.get(true).forEach(System.out::println);

        System.out.println("\nNon-Admitted Patients:");
        map.get(false).forEach(System.out::println);
    }

    // 4. highest bill
    public static void getHighestBill(List<Patient> list) {
        list.stream()
                .max(Comparator.comparingDouble(Patient::getBillAmount))
                .ifPresent(System.out::println);
    }

    // 5. average bill
    public static void getAverageBill(List<Patient> list) {
        double avg = list.stream()
                .collect(Collectors.averagingDouble(Patient::getBillAmount));

        System.out.println("Average Bill: " + avg);
    }

    // 6. names above age 60
    public static void getSeniorPatientNames(List<Patient> list) {
        list.stream()
                .filter(p -> p.getAge() > 60)
                .map(Patient::getName)
                .forEach(System.out::println);
    }

    // 7. disease -> patient names map (mapping)
    public static void diseaseToNamesMap(List<Patient> list) {
        Map<String, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(
                        Patient::getDisease,
                        Collectors.mapping(Patient::getName, Collectors.toList())
                ));

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
