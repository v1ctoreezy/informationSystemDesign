package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;


public class Lab2Alt {

    public static void main (String[] args) throws IOException  {

        // Прочитать из файла параметры функции: q - количество ошибок, m - интервал времени до ошибки
        System.out.println ("Данные файла lab2Alt.txt");
        System.out.println ("|q  |"+ " |m  |");

        // Получение данных из файла
        Path filePath = Paths.get("lab2Alt.txt");
        Scanner scanner = new Scanner(filePath);

        // Запись в список и вывод в консоль
        List<Integer> errorInterval = new ArrayList<>();
        List<Integer> errorNumber = new ArrayList<>();
        int q = 1;
        int i = -1;
        while (scanner.hasNext()) {
            if(scanner.hasNext()) {
                errorInterval.add(scanner.nextInt());
                errorNumber.add(q++);
                i++;

                String formattedErrorNumber = new DecimalFormat("00").format(errorNumber.get(i));
                String formattedErrorInterval = new DecimalFormat("00").format(errorInterval.get(i));

                System.out.println ("|" +formattedErrorNumber+" | |"+formattedErrorInterval+" |");

            }
            else {
                scanner.next();
            }
        }


        // Считать из консоли интервал [a, b]и погрешность e
        Scanner in = new Scanner(System.in);
        System.out.println ("Укажите нижнюю границу интервала");
        float a = in.nextFloat();
        System.out.println ("Укажите верхнюю границу интервала");
        float b = in.nextFloat();
        System.out.println ("Укажите желаемую погрешность");
        float e = in.nextFloat();

        // Вычислить общее количество ошибок в программе
        NumberOfErrors numberOfErrors = new NumberOfErrors();
        float c = numberOfErrors.BugsCalc(a, b, e, errorInterval, errorNumber);


        // Вычислить коэффициент пропорциональности
        ScaleFactor scaleFactor = new ScaleFactor();
        float k1= scaleFactor.ScaleFactorCalc(c, errorInterval, errorNumber );


        // Вычислить среднее время до появления ошибки
        AverageErrorInterval averageErrorInterval = new AverageErrorInterval();
        float t = averageErrorInterval.AverageErrorIntervalCalc(c, k1, errorInterval);


        // Вычислить время до начала тестирования
        TestingStartTime testingStartTime  = new TestingStartTime();
        float tt =  testingStartTime.TestingStartTimeCalc (c, k1, errorInterval, errorNumber);
    }
}
