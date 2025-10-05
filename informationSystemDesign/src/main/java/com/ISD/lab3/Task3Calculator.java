package com.ISD.lab3;

import java.util.ArrayList;

public class Task3Calculator {
    private double initialRating;
    private double lambda;
    private int programsCount; // Количество сделанных программ за оцениваемый период
    private ArrayList<Double> programVolumes;
    private ArrayList<Double> programErrors;
    private double newProgramVolume;

    public Task3Calculator(double initialRating, double lambda, int programsCount,
                           ArrayList<Double> programVolumes,
                           ArrayList<Double> programErrors,
                           double newProgramVolume) {
        this.initialRating = initialRating;
        this.lambda = lambda;
        this.programsCount = programsCount;
        this.programVolumes = programVolumes;
        this.programErrors = programErrors;
        this.newProgramVolume = newProgramVolume;

        // Проверка соответствия количества программ
        validateDataConsistency();
    }

    private void validateDataConsistency() {
        if (programVolumes.size() != programsCount) {
            System.out.println("ВНИМАНИЕ: Количество объемов программ (" + programVolumes.size() +
                    ") не соответствует заявленному количеству программ (" + programsCount + ")");
        }
        if (programErrors.size() != programsCount) {
            System.out.println("ВНИМАНИЕ: Количество ошибок (" + programErrors.size() +
                    ") не соответствует заявленному количеству программ (" + programsCount + ")");
        }
    }

    // Вариант 1 коэффициента: c = 1/(λ + R)
    public double calculateCoefficient1(double rating) {
        return 1.0 / (lambda + rating);
    }

    // Вариант 2 коэффициента: c = 1/(λ * R)
    public double calculateCoefficient2(double rating) {
        return 1.0 / (lambda * rating);
    }

    // Вариант 3 коэффициента: c = 1/λ + 1/R
    public double calculateCoefficient3(double rating) {
        return 1.0/lambda + 1.0/rating;
    }

    // Расчет рейтинга для варианта коэффициента
    public ArrayList<Double> calculateRatings(int coefficientType) {
        ArrayList<Double> ratings = new ArrayList<>();
        ratings.add(initialRating);

        double sumPrograms = 0;
        double sumErrors = 0;

        // Обрабатываем только указанное количество программ
        int programsToProcess = Math.min(programsCount, Math.min(programVolumes.size(), programErrors.size()));

        for (int i = 0; i < programsToProcess; i++) {
            double currentRating = ratings.get(i);
            double coefficient = getCoefficient(coefficientType, currentRating);

            sumPrograms += programVolumes.get(i);
            sumErrors += programErrors.get(i) / coefficient;

            double newRating = currentRating * (1 + 0.001 * (sumPrograms - sumErrors));
            ratings.add(newRating);
        }

        return ratings;
    }

    private double getCoefficient(int coefficientType, double rating) {
        switch (coefficientType) {
            case 1: return calculateCoefficient1(rating);
            case 2: return calculateCoefficient2(rating);
            case 3: return calculateCoefficient3(rating);
            default: return calculateCoefficient1(rating);
        }
    }

    // Расчет ожидаемых ошибок для нового проекта
    public double calculateExpectedErrors(int coefficientType, double currentRating) {
        double coefficient = getCoefficient(coefficientType, currentRating);
        return coefficient * newProgramVolume;
    }

    // Анализ эффективности программиста
    public void analyzeProgrammerPerformance() {
        double totalVolume = 0;
        double totalErrors = 0;
        double maxErrorsInProgram = 0;
        int errorFreePrograms = 0;

        int programsToAnalyze = Math.min(programsCount, Math.min(programVolumes.size(), programErrors.size()));

        for (int i = 0; i < programsToAnalyze; i++) {
            totalVolume += programVolumes.get(i);
            totalErrors += programErrors.get(i);
            maxErrorsInProgram = Math.max(maxErrorsInProgram, programErrors.get(i));
            if (programErrors.get(i) == 0) {
                errorFreePrograms++;
            }
        }

        double errorRate = totalVolume > 0 ? totalErrors / totalVolume : 0;
        double errorFreePercentage = programsToAnalyze > 0 ? (double) errorFreePrograms / programsToAnalyze * 100 : 0;

        System.out.println("\nАнализ эффективности программиста:");
        System.out.println("Обработано программ: " + programsToAnalyze + " из " + programsCount);
        System.out.println("Общий объем написанного кода: " + totalVolume + " Кбайт");
        System.out.println("Общее количество ошибок: " + totalErrors);
        System.out.println("Плотность ошибок: " + String.format("%.3f", errorRate) + " ошибок/Кбайт");
        System.out.println("Максимальное количество ошибок в одной программе: " + maxErrorsInProgram);
        System.out.println("Безошибочных программ: " + errorFreePrograms + " (" + String.format("%.1f", errorFreePercentage) + "%)");
    }

    public void calculateAndPrint() {
        System.out.println("\n=== ЗАДАНИЕ №3 ===");
        System.out.println("Начальный рейтинг (R0): " + initialRating);
        System.out.println("Уровень языка (λ): " + lambda);
        System.out.println("Количество сделанных программ: " + programsCount);
        System.out.println("Объемы написанных программ: " + programVolumes);
        System.out.println("Ошибки в программах: " + programErrors);
        System.out.println("Объем новой программы: " + newProgramVolume + " Кбайт");

        // Анализ эффективности
        analyzeProgrammerPerformance();

        // Расчет по трем вариантам коэффициентов
        for (int i = 1; i <= 3; i++) {
            ArrayList<Double> ratings = calculateRatings(i);
            double currentRating = ratings.get(ratings.size() - 1);
            double expectedErrors = calculateExpectedErrors(i, currentRating);

            System.out.println("\n--- Вариант коэффициента " + i + " ---");
            System.out.println("Формула коэффициента: " + getCoefficientFormula(i));
            System.out.println("Текущий рейтинг: " + Math.round(currentRating));
            System.out.println("Изменение рейтинга: " + String.format("%+.0f", currentRating - initialRating));
            System.out.println("Ожидаемое число ошибок в новой программе: " + Math.round(expectedErrors));

            // Детализация по программам
            System.out.println("Динамика рейтинга по программам:");
            for (int j = 0; j < ratings.size() && j <= programsCount; j++) {
                if (j == 0) {
                    System.out.println("  Программа 0 (начало): " + Math.round(ratings.get(j)));
                } else if (j <= programVolumes.size()) {
                    System.out.println("  После программы " + j + " (" + programVolumes.get(j-1) +
                            " Кбайт, ошибок: " + programErrors.get(j-1) + "): " +
                            Math.round(ratings.get(j)));
                }
            }
        }
    }

    private String getCoefficientFormula(int coefficientType) {
        switch (coefficientType) {
            case 1: return "c(λ,R) = 1/(λ + R)";
            case 2: return "c(λ,R) = 1/(λ × R)";
            case 3: return "c(λ,R) = 1/λ + 1/R";
            default: return "неизвестная формула";
        }
    }
}


