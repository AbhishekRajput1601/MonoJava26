package com.code;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User> users = new ArrayList<>();
    private static int idCounter = 1;


    public static void addUser(String name, int age, String mobileNo, String departmentName, String courseName) {
        User user = new User(idCounter++, name, age, mobileNo, departmentName, courseName);
        users.add(user);
    }

    public static int getNextUserId() {
        return idCounter;
    }


    public static User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    public static boolean updateUser(int id, String name, int age, String mobileNo, String departmentName, String courseName) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            user.setMobileNo(mobileNo);
            user.setDepartmentName(departmentName);
            user.setCourseName(courseName);
            return true;
        }
        return false;
    }



    public static boolean deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

}
