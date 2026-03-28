package com.abhi.stringandtypecasting;

import java.io.*;

public class CharacterStreamProgram {

    public static void main(String[] args) throws Exception {

        writeData();
        readData();
    }

    private static void writeData() throws Exception {

        FileWriter fw = new FileWriter("charData.txt");
        fw.write("Learning Character Stream in Java");
        fw.close();

        System.out.println("Character Data Written");
    }

    private static void readData() throws Exception {

        FileReader fr = new FileReader("charData.txt");

        int ch;
        System.out.println("Reading File:");

        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }

        fr.close();
    }
}
