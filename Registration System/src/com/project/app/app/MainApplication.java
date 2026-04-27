package com.project.app.app;

import com.project.app.model.StudentModel;
import com.project.app.model.CourseModel;
import com.project.app.service.StudentServiceLayer;
import com.project.app.util.InputValidationUtil;

import java.util.Scanner;
import java.util.Optional;

public class MainApplication {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentServiceLayer service = new StudentServiceLayer();

        while (true) {
            System.out.println("\n===== Student Course Registration & Fee Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. Register for Course");
            System.out.println("3. View All Students with Courses");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Update Student Record");
            System.out.println("6. Update Course Fee");
            System.out.println("7. Cancel Registration");
            System.out.println("8. Delete Student");
            System.out.println("9. High Paying Students Report");
            System.out.println("10. Course-wise Student Count");
            System.out.println("11. Add Course");
            System.out.println("12. Update Course Name");
            System.out.println("13. Remove Course");
            System.out.println("14. Add Branch");
            System.out.println("15. Update Branch Name");
            System.out.println("16. Remove Branch");
            System.out.println("17. Show All Courses");
            System.out.println("18. Show All Branches");
            System.out.println("19. Exit");

            int choice = readMenuChoice(scanner);

            try {
            switch (choice) {
                case 1:
                    int id1 = getUniqueStudentIdForAdd(scanner, service);
                    String name1 = InputValidationUtil.readValidName(scanner, "Student Name");
                    int age = InputValidationUtil.readPositiveInt(scanner, "Student Age");
                    String branch1 = InputValidationUtil.readNonBlank(scanner, "Student Branch");
                    service.addNewStudent(new StudentModel(id1, name1, age, branch1));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    int id2 = getExistingStudentId(scanner, service);
                    int courseId2 = selectCourseId(scanner, service);
                    if (courseId2 <= 0) break;
                    double fee2 = InputValidationUtil.readPositiveDouble(scanner, "Fees Paid");
                    service.registerStudentForCourse(id2, courseId2, fee2);
                    System.out.println("Course registration successful.");
                    break;

                case 3:
                    var rows = service.viewAllStudentsWithRegistrations();
                    if (rows.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        rows.forEach(System.out::println);
                    }
                    break;

                case 4:
                    int id4 = InputValidationUtil.readPositiveInt(scanner, "Student ID");
                    var rep = service.searchStudentRegistrationById(id4);
                    rep.forEach(System.out::println);
                    break;

                case 5:
                    int id5 = getExistingStudentId(scanner, service);
                    Optional<StudentModel> opt = service.getStudentById(id5);

                    String currentName = opt.map(StudentModel::getStudentName).orElse("");
                    String currentBranch = opt.map(StudentModel::getStudentBranch).orElse("");

                    System.out.println("1. Name\n2. Branch\n3. Both\n4. Cancel");
                    int upd = InputValidationUtil.readIntInRange(scanner, 1, 4, "Choose option: ");

                    if (upd == 1) {
                        boolean ok = service.updateStudentDetails(id5, InputValidationUtil.readValidName(scanner, "New Name"), currentBranch);
                        System.out.println(ok ? "Student updated successfully." : "Student not found.");
                    } else if (upd == 2) {
                        boolean ok = service.updateStudentDetails(id5, currentName, InputValidationUtil.readNonBlank(scanner, "New Branch"));
                        System.out.println(ok ? "Student updated successfully." : "Student not found.");
                    } else if (upd == 3) {
                        boolean ok = service.updateStudentDetails(id5,
                                InputValidationUtil.readValidName(scanner, "New Name"),
                                InputValidationUtil.readNonBlank(scanner, "New Branch"));
                        System.out.println(ok ? "Student updated successfully." : "Student not found.");
                    }
                    break;

                case 6:
                    int id6 = getExistingStudentId(scanner, service);
                    int courseId6 = selectCourseId(scanner, service);
                    if (courseId6 <= 0) break;
                    double fee6 = InputValidationUtil.readPositiveDouble(scanner, "New Fee");
                    boolean updated = service.updateCourseFee(id6, courseId6, fee6);
                    System.out.println(updated ? "Course fee updated." : "Registration not found.");
                    break;

                case 7:
                    int id7 = getExistingStudentId(scanner, service);
                    int courseId7 = selectCourseId(scanner, service);
                    if (courseId7 <= 0) break;
                    boolean cancelled = service.cancelCourseRegistration(id7, courseId7);
                    System.out.println(cancelled ? "Registration cancelled." : "Registration not found.");
                    break;

                case 8:
                    int id8 = InputValidationUtil.readPositiveInt(scanner, "Student ID");
                    service.deleteStudentCompletely(id8);
                    System.out.println("Student deleted successfully.");
                    break;

                case 9:
                    double minFee = InputValidationUtil.readNonNegativeDouble(scanner, "Minimum Fee");
                    var list = service.generateHighPayingStudentsReport(minFee);
                    if (list.isEmpty()) System.out.println("No high-paying students found."); else list.forEach(System.out::println);
                    break;

                case 10:
                    var cw = service.generateCourseWiseCountReport();
                    if (cw.isEmpty()) System.out.println("No course registrations found."); else cw.forEach(System.out::println);
                    break;

                case 11:
                    service.addCourse(InputValidationUtil.readNonBlank(scanner, "Course Name"));
                    System.out.println("Course added.");
                    break;

                case 12:
                    updateCourse(scanner, service);
                    break;

                case 13:
                    deleteCourse(scanner, service);
                    break;

                case 14:
                    service.addBranch(InputValidationUtil.readNonBlank(scanner, "Branch Name"));
                    System.out.println("Branch added.");
                    break;

                case 15:
                    updateBranch(scanner, service);
                    break;

                case 16:
                    deleteBranch(scanner, service);
                    break;

                case 17:
                    java.util.List<CourseModel> allCourses = service.getAllCourses();
                    if (allCourses.isEmpty()) {
                        System.out.println("No courses available.");
                        break;
                    }
                    System.out.println("Courses:");
                    for (CourseModel c : allCourses) {
                        System.out.printf("ID=%d, Name=%s%n", c.getCourseId(), c.getCourseName());
                    }
                    break;

                case 18:
                    java.util.List<com.project.app.model.BranchModel> allBranches = service.getAllBranches();
                    if (allBranches.isEmpty()) {
                        System.out.println("No branches available.");
                        break;
                    }
                    System.out.println("Branches:");
                    for (com.project.app.model.BranchModel b : allBranches) {
                        System.out.printf("ID=%d, Name=%s%n", b.getBranchId(), b.getBranchName());
                    }
                    break;

                case 19:
                    System.out.println("Exiting...");
                    return;
            }
            } catch (com.project.app.exceptions.DuplicateEntityException | com.project.app.exceptions.EntityNotFoundException |
                    IllegalArgumentException | com.project.app.exceptions.TransactionFailureException |
                    com.project.app.exceptions.DataAccessException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }



    private static int readMenuChoice(Scanner scanner) {
        return InputValidationUtil.readIntInRange(scanner, 1, 19, "Enter menu choice: ");
    }


    private static int getUniqueStudentIdForAdd(Scanner sc, StudentServiceLayer service) {
        while (true) {
            int id = InputValidationUtil.readPositiveInt(sc, "Student ID");
            if (!service.isStudentExists(id)) return id;
            System.out.println("ID already exists");
        }
    }

    private static int getExistingStudentId(Scanner sc, StudentServiceLayer service) {
        while (true) {
            int id = InputValidationUtil.readPositiveInt(sc, "Student ID");
            if (service.isStudentExists(id)) return id;
            System.out.println("Student not found");
        }
    }

    private static int selectCourseId(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllCourses();
        if (list.isEmpty()) {
            System.out.println("No courses available");
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getCourseName());
        }
        int choice = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose course: ");
        return list.get(choice - 1).getCourseId();
    }

    private static void updateCourse(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllCourses();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getCourseName());
        }
        int c = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        boolean ok = service.updateCourseName(list.get(c - 1).getCourseId(),
                InputValidationUtil.readNonBlank(sc, "New Course Name"));
        System.out.println(ok ? "Course updated." : "Course not found.");
    }

    private static void deleteCourse(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllCourses();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getCourseName());
        }
        int c = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        int courseId = list.get(c - 1).getCourseId();
        try {
            boolean ok = service.deleteCourse(courseId);
            System.out.println(ok ? "Course deleted." : "Course not found.");
        } catch (com.project.app.exceptions.ForeignKeyConstraintException fk) {
            System.out.println("Cannot delete course: there are registrations referencing it.");
            System.out.print("Delete all registrations for this course and then delete the course? (y/n): ");
            String ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                service.deleteCourseCompletely(courseId);
                System.out.println("Course and its registrations deleted.");
            } else {
                System.out.println("Delete aborted.");
            }
        }
    }

    private static void updateBranch(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllBranches();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getBranchName());
        }
        int b = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        boolean ok = service.updateBranchName(list.get(b - 1).getBranchId(),
                InputValidationUtil.readNonBlank(sc, "New Branch Name"));
        System.out.println(ok ? "Branch updated." : "Branch not found.");
    }

    private static void deleteBranch(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllBranches();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getBranchName());
        }
        int b = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        boolean ok = service.deleteBranch(list.get(b - 1).getBranchId());
        System.out.println(ok ? "Branch deleted." : "Branch not found or cannot delete (foreign key constraint). ");
    }
}