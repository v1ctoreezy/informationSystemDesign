package com.ISD;

import com.ISD.lab5.*;
import com.ISD.lab3.*;
import com.ISD.utils.DocumentReader;
import com.ISD.utils.SplitLines;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

     static void main(String[] args) {
     // Кодировка для консоли
     try {
         System.setOut(new PrintStream(System.out, true, "UTF-8"));
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

    System.out.println("Выберите номер лабороторной работы");
         System.out.print("Введите номер (2-5): ");
         int numberLab = scanner.nextInt();
     switch (numberLab) {
         case 2: System.out.println("лабороторная работа не завершена");
             break;
         case 3: inputResultLab3();
             break;
         case 4: System.out.println("лабороторная работа не завершена");
             break;
         case 5: runReliabilityCalculations();;
             break;
     }
    }
    public static void runReliabilityCalculations() {
        ArrayList<String> list = DocumentReader.fileLineReader("/Users/victorcerkasov/Documents/informationSystemDesign-main/informationSystemDesign/src/main/java/com/ISD/lab5/file1");
        double [] averageRecoveryTime = SplitLines.splitLines(SplitLines.subStrings(list,2));
        double [] estDurTransIO = SplitLines.splitLines(SplitLines.subStrings(list,4));
        double probability = ProbabilityWithoutFailures.probabilityCalculations(list);
        double averageRecTimeEstimate = AverageRecoveryTime.averageRecoveryTimeEstimate(list,AverageRecoveryTime.recoveryTime(averageRecoveryTime));
        double estDurTransformIO = EstimationDurationTransformationIO.estimateDurationIO(EstimationDurationTransformationIO.actualConversionDuration(list,estDurTransIO));

        System.out.println("Вероятность безотказной работы (P): "+probability);
        System.out.println("Оценка по среднему времени восстановления (Qв): "+averageRecTimeEstimate);
        System.out.println("Оценка по продолжительности преобразования входного набора данных в выходной (Qп): "+estDurTransformIO);
        System.out.println("Итоговая оценка вероятности безотказной работы : "+probability);
        System.out.println("Итоговая оценка по среднему времени восстановления и продолжительности преобразования : "+ MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO));
        System.out.println("Абсолютный показатель критериев : "+ MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)));
        System.out.println("Относительный показатель критериев : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(list.get(6))));
        System.out.println("Фактор надежности : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(list.get(6))));
    }

    public static void inputResultLab3() {
        final String FILE_PATH = "D:\\workspace\\informationSystemDesign\\src\\main\\java\\com\\ISD\\filesRead\\metriksHolsteda.txt";
        try {
            // Чтение данных из файла
            ArrayList<String> fileData = DataReader.readFile(FILE_PATH);

            // Параметры системы (Задание 1 и 2)
            double[] systemParams = DataReader.parseDoubleArray(fileData, 0);

            // Параметры программиста (Задание 3)
            double[] programmerParams = DataReader.parseDoubleArray(fileData, 1);
            int programsCount = (int) programmerParams[2]; // Количество сделанных программ
            ArrayList<Double> programVolumes = DataReader.parseDoubleList(fileData, 2);
            ArrayList<Double> programErrors = DataReader.parseDoubleList(fileData, 3);
            double newProgramVolume = DataReader.parseDoubleArray(fileData, 4)[0];

            // Параметры для Задания 2
            int programmersCount = 5;    // количество программистов
            int productivity = 20;       // производительность (команд/день)
            int workingHours = 8;        // рабочих часов в день
            double reliabilityParam = 2.0; // параметр надежности

            // Выполнение расчетов

            // Задание 1
            Task1Calculator task1 = new Task1Calculator(systemParams, programmerParams[1]);
            task1.calculateAndPrint();

            // Задание 2
            Task2Calculator task2 = new Task2Calculator(systemParams, programmersCount,
                    productivity, workingHours, reliabilityParam);
            task2.calculateAndPrint();

            // Задание 3
            Task3Calculator task3 = new Task3Calculator(programmerParams[0], programmerParams[1],
                    programsCount, programVolumes, programErrors,
                    newProgramVolume);
            task3.calculateAndPrint();

        } catch (Exception e) {
            System.out.println("Ошибка выполнения программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}