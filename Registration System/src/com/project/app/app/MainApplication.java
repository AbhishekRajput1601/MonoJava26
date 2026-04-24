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

            switch (choice) {
                case 1:
                    int id1 = getUniqueStudentIdForAdd(scanner, service);
                    String name1 = InputValidationUtil.readValidName(scanner, "Student Name");
                    int age = InputValidationUtil.readPositiveInt(scanner, "Student Age");
                    String branch1 = InputValidationUtil.readNonBlank(scanner, "Student Branch");
                    service.addNewStudent(new StudentModel(id1, name1, age, branch1));
                    break;

                case 2:
                    int id2 = getExistingStudentId(scanner, service);
                    int courseId2 = selectCourseId(scanner, service);
                    if (courseId2 <= 0) break;
                    double fee2 = InputValidationUtil.readPositiveDouble(scanner, "Fees Paid");
                    service.registerStudentForCourse(id2, courseId2, fee2);
                    break;

                case 3:
                    service.viewAllStudentsWithRegistrations();
                    break;

                case 4:
                    int id4 = InputValidationUtil.readPositiveInt(scanner, "Student ID");
                    service.searchStudentRegistrationById(id4);
                    break;

                case 5:
                    int id5 = getExistingStudentId(scanner, service);
                    Optional<StudentModel> opt = service.getStudentById(id5);

                    String currentName = opt.map(StudentModel::getStudentName).orElse("");
                    String currentBranch = opt.map(StudentModel::getStudentBranch).orElse("");

                    System.out.println("1. Name\n2. Branch\n3. Both\n4. Cancel");
                    int upd = InputValidationUtil.readIntInRange(scanner, 1, 4, "Choose option: ");

                    if (upd == 1) {
                        service.updateStudentDetails(id5, InputValidationUtil.readValidName(scanner, "New Name"), currentBranch);
                    } else if (upd == 2) {
                        service.updateStudentDetails(id5, currentName, InputValidationUtil.readNonBlank(scanner, "New Branch"));
                    } else if (upd == 3) {
                        service.updateStudentDetails(id5,
                                InputValidationUtil.readValidName(scanner, "New Name"),
                                InputValidationUtil.readNonBlank(scanner, "New Branch"));
                    }
                    break;

                case 6:
                    int id6 = getExistingStudentId(scanner, service);
                    int courseId6 = selectCourseId(scanner, service);
                    if (courseId6 <= 0) break;
                    double fee6 = InputValidationUtil.readPositiveDouble(scanner, "New Fee");
                    service.updateCourseFee(id6, courseId6, fee6);
                    break;

                case 7:
                    int id7 = getExistingStudentId(scanner, service);
                    int courseId7 = selectCourseId(scanner, service);
                    if (courseId7 <= 0) break;
                    service.cancelCourseRegistration(id7, courseId7);
                    break;

                case 8:
                    int id8 = InputValidationUtil.readPositiveInt(scanner, "Student ID");
                    service.deleteStudentCompletely(id8);
                    break;

                case 9:
                    double minFee = InputValidationUtil.readNonNegativeDouble(scanner, "Minimum Fee");
                    service.generateHighPayingStudentsReport(minFee);
                    break;

                case 10:
                    service.generateCourseWiseCountReport();
                    break;

                case 11:
                    service.addCourse(InputValidationUtil.readNonBlank(scanner, "Course Name"));
                    break;

                case 12:
                    updateCourse(scanner, service);
                    break;

                case 13:
                    deleteCourse(scanner, service);
                    break;

                case 14:
                    service.addBranch(InputValidationUtil.readNonBlank(scanner, "Branch Name"));
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
        service.updateCourseName(list.get(c - 1).getCourseId(),
                InputValidationUtil.readNonBlank(sc, "New Course Name"));
    }

    private static void deleteCourse(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllCourses();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getCourseName());
        }
        int c = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        service.deleteCourse(list.get(c - 1).getCourseId());
    }

    private static void updateBranch(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllBranches();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getBranchName());
        }
        int b = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        service.updateBranchName(list.get(b - 1).getBranchId(),
                InputValidationUtil.readNonBlank(sc, "New Branch Name"));
    }

    private static void deleteBranch(Scanner sc, StudentServiceLayer service) {
        var list = service.getAllBranches();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getBranchName());
        }
        int b = InputValidationUtil.readIntInRange(sc, 1, list.size(), "Choose: ");
        service.deleteBranch(list.get(b - 1).getBranchId());
    }
}