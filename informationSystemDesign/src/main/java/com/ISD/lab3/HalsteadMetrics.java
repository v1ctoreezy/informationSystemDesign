package com.ISD.lab3;

public class HalsteadMetrics {
    // Параметры для варианта 1
    private int targets = 20;
    private int measurements = 30;
    private int trackedParams = 10;
    private int calculatedParams = 3;
    private double lambda = 1.53;

    public double calculateN2Star() {
        return targets * measurements * trackedParams + targets * calculatedParams;
    }

    public double calculatePotentialVolume() {
        double n2 = calculateN2Star();
        return (n2 + 2) * Math.log(n2 + 2) / Math.log(2);
    }

    public double calculatePotentialErrors() {
        double v = calculatePotentialVolume();
        return Math.pow(v, 2) / (3000 * lambda);
    }
}

