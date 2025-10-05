package com.ISD.utils;

import com.ISD.lab5.EstimationDurationTransformationIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentReader extends EstimationDurationTransformationIO {

    public static ArrayList<String> fileLineReader(String filename) {
        ArrayList<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while (line != null) {
                readLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readLines;
    }
}

