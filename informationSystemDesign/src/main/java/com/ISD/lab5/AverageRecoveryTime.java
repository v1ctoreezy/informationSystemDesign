package com.ISD.lab5;

import java.util.ArrayList;
import java.util.Arrays;

public class AverageRecoveryTime {
    public static double recoveryTime (double [] array) { // метод, выполняющий рассчет Тв
        double leftSide = array[0]; // левый предел
        double rightSide = array[1]; // правый предел
        double [] total = new double [100]; // массив из 100 значений
        for (int i = 0; i < total.length; i++) {
            total[i] = leftSide+Math.random()*(rightSide - leftSide); // генерируем 100 случайных значений из интервала
        }
        return Arrays.stream(total).average().getAsDouble(); // возвращаем среднее значение из полученного массива
    }
    public static double averageRecoveryTimeEstimate (ArrayList <String> lines, double averageRecoveryTime){ // Метод, выполняющий расчет Qв
        double acceptableRecoveryTime = Double.parseDouble(lines.get(3)); //Допустимое время восстановления
        if (averageRecoveryTime<=acceptableRecoveryTime){ //Если Тв<=Тдоп в, тогда верни 1
            return 1;
        }
        else {
            return acceptableRecoveryTime/averageRecoveryTime; // в противном случае верни Тдопв/Тв
        }
    }
}

