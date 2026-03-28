package com.abhi.stringandtypecasting;

import java.io.*;

public class ByteStreamProgram {

    public static void main(String[] args) throws Exception {

        writeData();
        readData();
    }

    private static void writeData() throws Exception {

        FileOutputStream fos = new FileOutputStream("byteData.txt");

        String data = "Learning Byte Stream in Java";
        fos.write(data.getBytes());

        fos.close();
        System.out.println("Data Written Successfully");
    }

    private static void readData() throws Exception {

        FileInputStream fis = new FileInputStream("byteData.txt");

        int ch;
        System.out.println("Reading File:");

        while ((ch = fis.read()) != -1) {
            System.out.print((char) ch);
        }

        fis.close();
    }
}

