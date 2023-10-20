package com.example.splogin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileUtils {

    public static ArrayList<String> getLines(String url) throws FileNotFoundException {

        ArrayList<String> lines = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(url));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()) {
            String line = scanner.next();
            lines.add(line);
        }
        return lines;
    }


    public static HashMap<String, String> parseCredentialsFromFile(ArrayList<String> lines) {

        HashMap<String, String> credentials = new HashMap<>();

        for (String line : lines) {
            String[] values = line.split(",");
            credentials.put(values[0], values[1]);
        }
        return credentials;
    }

    public static HashMap<String, String> getCredentials() throws FileNotFoundException {
        return FileUtils.parseCredentialsFromFile(
                FileUtils.getLines(
                        "login.txt"
                )
        );
    }
    public static HashMap<String, String[]> getStudent() throws FileNotFoundException {
        return FileUtils.parseStudentFile(
                FileUtils.getLines(
                        "src/sprint2/files/employees.txt"
                )
        );
    }


    public static HashMap<String, String[]> parseStudentFile(ArrayList<String> lines){

        HashMap<String, String[]> employees = new HashMap<>();

        for (String line: lines){
            //split first into username:information
            String[] values = line.split(":");
            String username = values[0];
            String information [] = values[1].split(",");
            employees.put(username, information);
        }


        return employees;
    }


}