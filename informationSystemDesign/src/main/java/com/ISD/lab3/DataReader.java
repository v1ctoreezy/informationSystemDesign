package com.ISD.lab3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    public static ArrayList<String> readFile(String filename) {
        BufferedReader reader;
        ArrayList<String> readLines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                readLines.add(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Прочитано строк из файла: " + readLines.size());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла '" + filename + "': " + e.getMessage());
        }
        return readLines;
    }

    public static double[] parseDoubleArray(ArrayList<String> readLines, int lineNum) {
        if (lineNum >= readLines.size()) {
            throw new RuntimeException("Запрошена строка " + lineNum + ", но в файле только " + readLines.size() + " строк");
        }

        String line = readLines.get(lineNum);
        String[] parts = line.split(";");
        double[] arrayDouble = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arrayDouble[i] = Double.parseDouble(parts[i].trim());
        }
        return arrayDouble;
    }

    public static ArrayList<Double> parseDoubleList(ArrayList<String> readLines, int lineNum) {
        double[] array = parseDoubleArray(readLines, lineNum);
        ArrayList<Double> result = new ArrayList<>();
        for (double value : array) {
            result.add(value);
        }
        return result;
    }
}
