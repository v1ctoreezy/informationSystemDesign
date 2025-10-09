package com.ISD.lab3;

import java.util.ArrayList;

public class Task3Calculator {
    private double initialRating;
    private double lambda;
    private int programsCount;
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
    }

    public void calculateAndPrint() {
        System.out.println("\n=== ЗАДАНИЕ №3 - ДЕТАЛЬНЫЙ РАСЧЕТ ===");
        System.out.println("ИСХОДНЫЕ ДАННЫЕ:");
        System.out.println("Начальный рейтинг (R₀): " + initialRating);
        System.out.println("Уровень языка (λ): " + lambda);
        System.out.println("Количество программ: " + programsCount);
        System.out.println("Объемы программ: " + programVolumes + " Кбайт");
        System.out.println("Ошибки в программах: " + programErrors);
        System.out.println("Объем новой программы: " + newProgramVolume + " Кбайт");

        printInitialCoefficients();

        for (int coeffType = 1; coeffType <= 3; coeffType++) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("РАСЧЕТ ДЛЯ КОЭФФИЦИЕНТА " + coeffType + ":");
            System.out.println("Формула: " + getCoefficientFormula(coeffType));

            ArrayList<Double> ratings = calculateRatingsWithDetails(coeffType);
            double finalRating = ratings.get(ratings.size() - 1);
            double coefficient = getCoefficient(coeffType, finalRating);
            double expectedErrors = calculateExpectedErrors(coeffType, finalRating);

            System.out.println("\nИТОГ для коэффициента " + coeffType + ":");
            System.out.println("Финальный рейтинг: R = " + finalRating);
            System.out.println("Коэффициент: c = " + coefficient);
            System.out.println("Ожидаемые ошибки: B = c × V = " +
                    coefficient + " × " + newProgramVolume + " = " + expectedErrors);
        }
    }

    public ArrayList<Double> calculateRatingsWithDetails(int coefficientType) {
        ArrayList<Double> ratings = new ArrayList<>();
        ratings.add(initialRating);

        double sumV = 0;
        double sumBc = 0;

        int programsToProcess = Math.min(programsCount, Math.min(programVolumes.size(), programErrors.size()));

        System.out.println("\nШАГИ РАСЧЕТА РЕЙТИНГА:");
        System.out.println("R₀ = " + initialRating);

        for (int i = 0; i < programsToProcess; i++) {
            double currentRating = ratings.get(i);
            double coefficient = getCoefficient(coefficientType, currentRating);
            double Vj = programVolumes.get(i);
            double Bk = programErrors.get(i);

            sumV += Vj;
            sumBc += Bk / coefficient;

            double delta = 0.001 * (sumV - sumBc);
            double newRating = currentRating * (1 + delta);

            System.out.println(String.format("\nПрограмма %d:", i+1));
            System.out.println(String.format("  V%d = %.0f, B%d = %.0f", i+1, Vj, i+1, Bk));
            System.out.println(String.format("  c = %s = %.10f",
                    getCoefficientCalculation(coefficientType, currentRating), coefficient));
            System.out.println(String.format("  ΣV = %.0f, Σ(B/c) = %.6f", sumV, sumBc));
            System.out.println(String.format("  Δ = 0.001 × (%.0f - %.6f) = %.10f", sumV, sumBc, delta));
            System.out.println(String.format("  R%d = %.6f × (1 + %.10f) = %.6f",
                    i+1, currentRating, delta, newRating));

            ratings.add(newRating);
        }

        return ratings;
    }

    private double getCoefficient(int coefficientType, double rating) {
        switch (coefficientType) {
            case 1: return 1.0 / (lambda + rating);
            case 2: return 1.0 / (lambda * rating);
            case 3: return 1.0/lambda + 1.0/rating;
            default: return 0;
        }
    }

    private String getCoefficientFormula(int coefficientType) {
        switch (coefficientType) {
            case 1: return "c = 1/(λ + R)";
            case 2: return "c = 1/(λ × R)";
            case 3: return "c = 1/λ + 1/R";
            default: return "";
        }
    }

    private String getCoefficientCalculation(int coefficientType, double rating) {
        switch (coefficientType) {
            case 1: return String.format("1/(%.2f + %.6f)", lambda, rating);
            case 2: return String.format("1/(%.2f × %.6f)", lambda, rating);
            case 3: return String.format("1/%.2f + 1/%.6f", lambda, rating);
            default: return "";
        }
    }

    public double calculateExpectedErrors(int coefficientType, double currentRating) {
        double coefficient = getCoefficient(coefficientType, currentRating);
        return coefficient * newProgramVolume;
    }

    public void printInitialCoefficients() {
        System.out.println("\nПРОВЕРКА НАЧАЛЬНЫХ КОЭФФИЦИЕНТОВ:");

        double c1 = getCoefficient(1, initialRating);
        double c2 = getCoefficient(2, initialRating);
        double c3 = getCoefficient(3, initialRating);

        System.out.println(String.format("c₁ = 1/(%.2f + %.0f) = 1/%.6f = %.15f",
                lambda, initialRating, lambda + initialRating, c1));
        System.out.println(String.format("c₂ = 1/(%.2f × %.0f) = 1/%.6f = %.15f",
                lambda, initialRating, lambda * initialRating, c2));
        System.out.println(String.format("c₃ = 1/%.2f + 1/%.0f = %.15f + %.15f = %.15f",
                lambda, initialRating, 1.0/lambda, 1.0/initialRating, c3));

        System.out.println("\nОЖИДАЕМЫЕ ОШИБКИ ДЛЯ НАЧАЛЬНОГО РЕЙТИНГА:");
        System.out.println(String.format("B₁ = %.15f × %.0f = %.15f", c1, newProgramVolume, c1 * newProgramVolume));
        System.out.println(String.format("B₂ = %.15f × %.0f = %.15f", c2, newProgramVolume, c2 * newProgramVolume));
        System.out.println(String.format("B₃ = %.15f × %.0f = %.15f", c3, newProgramVolume, c3 * newProgramVolume));
    }
}

