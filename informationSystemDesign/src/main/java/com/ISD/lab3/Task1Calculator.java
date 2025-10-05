package com.ISD.lab3;

public class Task1Calculator {
    private double[] systemParams; // параметры системы [цели, измерения, параметры, расчеты]
    private double lambda; // уровень языка программирования

    public Task1Calculator(double[] systemParams, double lambda) {
        this.systemParams = systemParams;
        this.lambda = lambda;
    }

    // Расчет минимального числа различных операндов
    public double calculateMinOperands() {
        double targets = systemParams[0]; // число целей
        double measurements = systemParams[1]; // количество измерений
        double trackedParams = systemParams[2]; // отслеживаемые параметры
        double calculatedParams = systemParams[3]; // рассчитываемые параметры

        return targets * measurements * trackedParams + targets * calculatedParams;
    }

    // Расчет потенциального объема программы
    public double calculatePotentialVolume() {
        double n2 = calculateMinOperands();
        return (n2 + 2) * (Math.log(n2 + 2) / Math.log(2));
    }

    // Расчет потенциального числа ошибок
    public double calculatePotentialErrors() {
        double V = calculatePotentialVolume();
        return Math.pow(V, 2) / (3000 * lambda);
    }

    public void calculateAndPrint() {
        double n2 = calculateMinOperands();
        double V = calculatePotentialVolume();
        double B = calculatePotentialErrors();

        System.out.println("=== ЗАДАНИЕ №1 ===");
        System.out.println("Минимальное число операндов (n2): " + Math.round(n2));
        System.out.println("Потенциальный объем программы (V*): " + Math.round(V));
        System.out.println("Потенциальное число ошибок (B): " + Math.round(B));
        System.out.println("Уровень языка программирования (λ): " + lambda);
    }
}

