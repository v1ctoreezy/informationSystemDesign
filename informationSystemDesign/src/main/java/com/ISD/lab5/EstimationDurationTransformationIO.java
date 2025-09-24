package com.ISD.lab5;

import java.util.ArrayList;
import java.util.Arrays;

public class EstimationDurationTransformationIO { // Qп - Оценка продолжительности преобразования входного набора данных в выходной
    public static double [] actualConversionDuration (ArrayList <String> lines, double [] arr) { // Tп
        double allowedConversionTime = Double.parseDouble(lines.get(5));
        double leftSide = arr[0]; // левый предел
        double rightSide = arr[1]; // правый предел
        double [] totalDuration = new double [200]; // массив 200 значений
        for (int i = 0; i < totalDuration.length; i++) {
            totalDuration[i] = leftSide + Math.random()*(rightSide-leftSide); // заполняем массив значениями
            if (totalDuration[i] <= allowedConversionTime) {
                totalDuration[i] = 1;
            }else{
                totalDuration[i] = allowedConversionTime/totalDuration[i];
            }
        }
        return totalDuration; // возвращаем заполненный массив
    }
    public static double estimateDurationIO (double [] arr) { // вычисляем усредненную оценку mkq по нескольким значениям
        return Arrays.stream(arr).sum()/arr.length; // возвращаем усредненную оценку
    }
}
