package solid.assignment.srp;

public class StudentApp {
    public static void main(String[] args) {

        StudentService service = new StudentService();
        StudentPrinter printer = new StudentPrinter();

        System.out.println("\n==================================");
        System.out.println("      STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. Add Student & Generate Report");
            System.out.println("2. Exit");

            int choice = InputValidator.getInt("\nEnter your choice: ");

            switch (choice) {

                case 1:
                    String name = InputValidator.getString("Enter student name: ");
                    int marks = InputValidator.getInt("Enter marks: ");

                    Student student = new Student(name, marks);

                    String grade = service.calculateGrade(marks);

                    printer.printReport(student, grade);
                    break;

                case 2:
                    System.out.println("\n Exiting system...");
                    return;

                default:
                    System.out.println("⚠ Invalid choice! Try again.");
            }
        }
    }
}
