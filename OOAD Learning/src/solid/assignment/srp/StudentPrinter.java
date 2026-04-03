package solid.assignment.srp;

public class StudentPrinter {

    public void printReport(Student student, String grade) {
        System.out.println("\n========= STUDENT REPORT =========");
        System.out.println("Name  : " + student.getName());
        System.out.println("Marks : " + student.getMarks());
        System.out.println("Grade : " + grade);
        System.out.println("==================================");
    }
}
