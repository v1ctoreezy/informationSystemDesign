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

    public static void inputResultLab3(){
        // Создаем объект для расчетов
        StructuralMetrics metrics = new StructuralMetrics();

        // Задание №1
        System.out.println("Результаты задания №1:");
        System.out.println("Минимальное число различных операндов (n2*): " + metrics.calculateN2Star());
        System.out.println("Потенциальный объем программы (V*): " + metrics.calculatePotentialVolume());
        System.out.println("Потенциальное число ошибок (B): " + metrics.calculatePotentialErrors());
        System.out.println();

        // Задание №2
        System.out.println("Результаты задания №2:");
        System.out.println("Число модулей программного средства (k): " + metrics.calculateModules());

        if (metrics.calculateModules() > 8) {
            System.out.println("Структура ПО - иерархическая");
            System.out.println("Число уровней (i): " + metrics.calculateLevels());
        }

        System.out.println("Уточненное число модулей (K): " + metrics.calculateRefinedModules());
        System.out.println("Длина программы (N): " + metrics.calculateProgramLength());
        System.out.println("Объем программного обеспечения (V): " + metrics.calculateVolume());

        // Параметры для расчета команд ассемблера и времени разработки
        int b = 10; // количество ошибок
        int m = 5; // количество программистов
        int v = 20; // производительность

        System.out.println("Количество команд ассемблера (P): " + metrics.calculateAssemblerCommands(b));
        System.out.println("Календарное время программирования (Tk): " + metrics.calculateDevelopmentTime(m, v) + " дней");
        System.out.println("Время наработки на отказ (tH): " + metrics.calculateReliability(m, v) + " часов");
        System.out.println();

        // Задание №3
        System.out.println("Результаты задания №3:");
        ProgrammerRating rating = new ProgrammerRating();

        // Расчет рейтинга программиста
        double currentRating = rating.calculateRating(
                new double[]{5, 7, 9, 11},
                new int[]{0, 2, 5, 4},
                1.53,
                1000
        );

        System.out.println("Текущий рейтинг программиста: " + currentRating);

        // Расчет ожидаемых ошибок для новой программы
        System.out.println("Ожидаемое число ошибок для программы объемом 15 Кбайт: " +
                rating.calculateExpectedErrors(currentRating, 1.53, 15));
    }
}