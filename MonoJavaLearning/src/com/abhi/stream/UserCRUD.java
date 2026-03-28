package com.abhi.stream;

import java.io.*;

public class UserCRUD {

    private static final String FILE_NAME = "users.txt";


    public void createUser(String user) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
        fos.write((user + "\n").getBytes());
        fos.close();
    }

    public void readUsers() throws IOException {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No users found.");
            return;
        }

        FileInputStream fis = new FileInputStream(FILE_NAME);
        int data;

        while ((data = fis.read()) != -1) {
            System.out.print((char) data);
        }

        fis.close();
    }


    public void updateUser(String oldName, String newName) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        StringBuilder sb = new StringBuilder();
        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            if (line.equals(oldName)) {
                sb.append(newName).append("\n");
                found = true;
            } else {
                sb.append(line).append("\n");
            }
        }

        br.close();

        if (!found) {
            System.out.println("User not found!");
            return;
        }

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(sb.toString().getBytes());
        fos.close();

        System.out.println("User updated successfully.");
    }


    public void deleteUser(String name) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        StringBuilder sb = new StringBuilder();
        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            if (line.equals(name)) {
                found = true;
                continue;
            }

            sb.append(line).append("\n");
        }

        br.close();

        if (!found) {
            System.out.println("User not found!");
            return;
        }

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(sb.toString().getBytes());
        fos.close();

        System.out.println("User deleted successfully.");
    }
}