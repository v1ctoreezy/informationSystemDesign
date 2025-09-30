package com.ISD.lab3;

public class StructuralMetrics extends HalsteadMetrics {
    public int calculateModules() {
        return (int) (calculateN2Star() / 8);
    }

    public int calculateLevels() {
        return (int) (Math.log(calculateN2Star()) / 3 + 1);
    }

    public int calculateRefinedModules() {
        return calculateModules() + (int) (calculateN2Star() / 64);
    }

    public double calculateProgramLength() {
        int k = calculateRefinedModules();
        return 220 * k + k * Math.log(k) / Math.log(2);
    }

    public double calculateVolume() {
        int k = calculateRefinedModules();
        return k * 220 * Math.log(48) / Math.log(2);
    }

    public double calculateAssemblerCommands(int b) {
        return 3 * calculateProgramLength() / b;
    }

    public double calculateDevelopmentTime(int m, int v) {
        return 3 * calculateProgramLength() / (m * v);
    }

    public double calculateReliability(int m, int v) {
        double tk = calculateDevelopmentTime(m, v) * 8; // переводим в часы
        return tk / (2 * Math.log(calculatePotentialErrors()));
    }
}

