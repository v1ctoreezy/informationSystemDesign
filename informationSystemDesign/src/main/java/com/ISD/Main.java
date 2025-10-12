package com.ISD;

import com.ISD.lab5.*;
import com.ISD.lab3.*;
import com.ISD.lab2.*;
import com.ISD.utils.DataReader;
import com.ISD.utils.SplitLines;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

public static void main(String[] args) throws IOException {
        // Кодировка для консоли
    System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

    System.out.println("Выберите номер лабороторной работы");
        System.out.print("Введите номер (2-5): ");
        int numberLab = scanner.nextInt();
        switch (numberLab) {
            case 2: inputResultLab2();
                break;
            case 3: inputResultLab3();
                break;
            case 4: System.out.println("лабороторная работа не завершена");
                break;
            case 5: inputResultLab5();;
                break;
        }
    }
    public static void inputResultLab2() throws IOException {
        // Путь к файлу относительно проекта
        String filePath = "filesRead/lab2Alt.txt";

        // Получаем данные из файла
        ArrayList<String> fileLines = documentReader(filePath);

        if (fileLines == null) {
            System.err.println("Не удалось прочитать файл. Завершаем выполнение.");
            return;
        }

        System.out.println("Данные файла lab2Alt.txt");
        System.out.println("|q  | |m  |");

        List<Integer> errorInterval = new ArrayList<>();
        List<Integer> errorNumber = new ArrayList<>();
        int q = 1;

        // Парсим данные из файла
        for (String line : fileLines) {
            try {
                int interval = Integer.parseInt(line.trim());
                errorInterval.add(interval);
                errorNumber.add(q++);

                // Форматируем вывод
                DecimalFormat formatter = new DecimalFormat("00");
                String formattedErrorNumber = formatter.format(errorNumber.get(errorNumber.size() - 1));
                String formattedErrorInterval = formatter.format(errorInterval.get(errorInterval.size() - 1));

                System.out.println("|" + formattedErrorNumber + " | |" + formattedErrorInterval + " |");
            } catch (NumberFormatException e) {
                System.err.println("Ошибка при парсинге строки: " + line);
            }
        }

        // Считываем данные с консоли
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Укажите нижнюю границу интервала");
            float a = in.nextFloat();
            System.out.println("Укажите верхнюю границу интервала");
            float b = in.nextFloat();
            System.out.println("Укажите желаемую погрешность");
            float e = in.nextFloat();

            // Вычисляем общее количество ошибок
            NumberOfErrors numberOfErrors = new NumberOfErrors();
            float c = numberOfErrors.BugsCalc(a, b, e, errorInterval, errorNumber);

            // Вычисляем коэффициент пропорциональности
            ScaleFactor scaleFactor = new ScaleFactor();
            float k1 = scaleFactor.ScaleFactorCalc(c, errorInterval, errorNumber);

            // Вычисляем среднее время до появления ошибки
            AverageErrorInterval averageErrorInterval = new AverageErrorInterval();
            float t = averageErrorInterval.AverageErrorIntervalCalc(c, k1, errorInterval);

            // Вычисляем время до начала тестирования
            TestingStartTime testingStartTime = new TestingStartTime();
            float tt = testingStartTime.TestingStartTimeCalc(c, k1, errorInterval, errorNumber);
        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода данных: " + e.getMessage());
        }
    }

    public static void inputResultLab5() {
        final String RELATIVE_FILE_PATH = "filesRead/file_lab5.txt";
        try{
            double [] averageRecoveryTime = SplitLines.splitLines(SplitLines.subStrings(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)),2));
            double [] estDurTransIO = SplitLines.splitLines(SplitLines.subStrings(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)),4));
            double probability = ProbabilityWithoutFailures.probabilityCalculations(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)));
            double averageRecTimeEstimate = AverageRecoveryTime.averageRecoveryTimeEstimate(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)),AverageRecoveryTime.recoveryTime(averageRecoveryTime));
            double estDurTransformIO = EstimationDurationTransformationIO.estimateDurationIO(EstimationDurationTransformationIO.actualConversionDuration(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)),estDurTransIO));

            System.out.println("Вероятность безотказной работы (P): "+probability);
            System.out.println("Оценка по среднему времени восстановления (Qв): "+averageRecTimeEstimate);
            System.out.println("Оценка по продолжительности преобразования входного набора данных в выходной (Qп): "+estDurTransformIO);
            System.out.println("Итоговая оценка вероятности безотказной работы : "+probability);
            System.out.println("Итоговая оценка по среднему времени восстановления и продолжительности преобразования : "+ MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO));
            System.out.println("Абсолютный показатель критериев : "+ MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)));
            System.out.println("Относительный показатель критериев : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)).get(6))));
            System.out.println("Фактор надежности : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)).get(6))));
        }catch (Exception e){
            System.out.println("Ошибка выполнения программы: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void inputResultLab3() {
        final String RELATIVE_FILE_PATH = "filesRead/metriksHolsteda.txt";
        try {

            double[] systemParams = DataReader.parseDoubleArray(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)), 0);
            double[] programmerParams = DataReader.parseDoubleArray(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)), 1);
            int programsCount = (int) programmerParams[2];
            ArrayList<Double> programVolumes = DataReader.parseDoubleList(documentReader(RELATIVE_FILE_PATH), 2);
            ArrayList<Double> programErrors = DataReader.parseDoubleList(documentReader(RELATIVE_FILE_PATH), 3);
            double newProgramVolume = DataReader.parseDoubleArray(Objects.requireNonNull(documentReader(RELATIVE_FILE_PATH)), 4)[0];

            int programmersCount = 5;
            int productivity = 20;
            int workingHours = 8;
            double reliabilityParam = 2.0;

            System.out.println("==========================================");

            Task1Calculator task1 = new Task1Calculator(systemParams, programmerParams[1]);
            task1.calculateAndPrint();

            Task2Calculator task2 = new Task2Calculator(systemParams, programmersCount,
                    productivity, workingHours, reliabilityParam);
            task2.calculateAndPrint();

            Task3Calculator task3 = new Task3Calculator(programmerParams[0], programmerParams[1],
                    programsCount, programVolumes, programErrors,
                    newProgramVolume);
            task3.calculateAndPrint();

        } catch (Exception e) {
            System.out.println("Ошибка выполнения программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static ArrayList<String> documentReader(String filePath) {
        // Пробуем разные возможные пути
        String[] possiblePaths = {
                Paths.get("informationSystemDesign","src", "main", "java", "com", "ISD", filePath).toString(),
        };

        String absoluteFilePath = null;
        for (String path : possiblePaths) {
            File file = new File(path);
//            System.out.println("Проверяем путь: " + file.getAbsolutePath());
            if (file.exists()) {
                absoluteFilePath = file.getAbsolutePath();
//                System.out.println("Файл найден: " + absoluteFilePath);
                break;
            }
        }

        if (absoluteFilePath == null) {
            System.out.println("Файл не найден. Проверьте расположение файла.");
            System.out.println("Текущая рабочая директория: " + System.getProperty("user.dir"));
            return null;
        }

        // Чтение данных из файла
        return  DataReader.readFile(absoluteFilePath);
    }
}