package com.abhi.exceptionhandling.assignment.asg8;

public class Test {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        try {
            studentService.validateStudent();
        } catch (InvalidMarksException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks input.");
        } finally {
            System.out.println("Grading process completed.");
        }
    }
}