package com.abhi.comparatorassignment.asg3;

import java.util.*;

public class StudentManager {

    private List<Student> studentList = new ArrayList<>();
    private Set<String> studentIdSet = new HashSet<>();
    private Map<String, List<Student>> departmentMap = new HashMap<>();

    public void addStudent(Student student) {
        if (studentIdSet.contains(student.getStudentId())) {
            System.out.println("Duplicate Student ID");
            return;
        }

        studentList.add(student);
        studentIdSet.add(student.getStudentId());

        departmentMap
                .computeIfAbsent(student.getDepartment(), k -> new ArrayList<>())
                .add(student);
    }

    public void addMarks(String id, String subject, int marks) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(id)) {
                s.addMarks(subject, marks);
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void displayAll() {
        for (Student s : studentList) {
            System.out.println(s.getStudentId() + " " + s.getStudentName() + " " + s.getAverageMarks());
        }
    }

    public void sortByMarks() {
        Collections.sort(studentList, new MarksComparator());
        displayAll();
    }

    public void sortByName() {
        Collections.sort(studentList, new NameComparator());
        displayAll();
    }

    public void removeLowPerformers(double minMarks) {
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getAverageMarks() < minMarks) {
                it.remove();
                studentIdSet.remove(s.getStudentId());
            }
        }
    }

    public void displayByDepartment(String dept) {
        List<Student> list = departmentMap.get(dept);
        if (list == null) {
            System.out.println("No students");
            return;
        }
        for (Student s : list) {
            System.out.println(s.getStudentId() + " " + s.getStudentName());
        }
    }
}