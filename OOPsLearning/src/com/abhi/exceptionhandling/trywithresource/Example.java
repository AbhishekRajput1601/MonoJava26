package com.abhi.exceptionhandling.trywithresource;

import java.io.*;
import java.util.*;

public class Example {

    static String fileName = "users.txt";

    public static void main(String[] args) {

        createUser("Amit sir");
        readUsers();
    }

    public static void createUser(String user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(user);
            bw.newLine();
            System.out.println("User added successfully");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void readUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            System.out.println("Users List:");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}