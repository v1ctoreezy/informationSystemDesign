package com.ISD.utils;

import java.util.ArrayList;


public class SplitLines {
    // Берём строку по индексу и разбиваем её по пробелу
    public static String[] subStrings(ArrayList<String> lines, int index) {
        return lines.get(index).split(" ");
    }

    // Конвертируем массив строк в массив double
    public static double[] splitLines(String[] values) {
        double[] numbers = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            numbers[i] = Double.parseDouble(values[i]);
        }
        return numbers;
    }
}
