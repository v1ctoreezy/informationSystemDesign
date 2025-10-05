package com.ISD.lab3;

public class Task2Calculator {
    private double[] systemParams;
    private int programmersCount; // количество программистов
    private int productivity; // производительность (команд в день)
    private int workingHours; // рабочих часов в день
    private double reliabilityParam; // параметр надежности n

    public Task2Calculator(double[] systemParams, int programmersCount, int productivity,
                           int workingHours, double reliabilityParam) {
        this.systemParams = systemParams;
        this.programmersCount = programmersCount;
        this.productivity = productivity;
        this.workingHours = workingHours;
        this.reliabilityParam = reliabilityParam;
    }

    // Расчет числа модулей ПО
    public double calculateModulesCount() {
        double n2 = calculateMinOperands();
        return n2 / 8;
    }

    // Расчет числа уровней иерархии
    public double calculateHierarchyLevels() {
        double n2 = calculateMinOperands();
        return (Math.log(n2) / Math.log(2)) / 3 + 1;
    }

    // Расчет общего числа модулей
    public double calculateTotalModules() {
        double k = calculateModulesCount();
        if (k > 8 * 3) {
            return k + k / 8;
        } else {
            return k;
        }
    }

    // Расчет длины программы
    public double calculateProgramLength() {
        double K = calculateTotalModules();
        return K * (220 + Math.log(K) / Math.log(2));
    }

    // Расчет объема ПО
    public double calculateProgramVolume() {
        double K = calculateTotalModules();
        return K * 220 * Math.log(48) / Math.log(2);
    }

    // Расчет количества команд ассемблера
    public double calculateAssemblyCommands() {
        double N = calculateProgramLength();
        return 3 * N / 8;
    }

    // Расчет календарного времени программирования (в днях)
    public double calculateDevelopmentTime() {
        double N = calculateProgramLength();
        return 3 * N / (8 * programmersCount * productivity);
    }

    // Расчет потенциального количества ошибок
    public double calculatePotentialErrors() {
        double V = calculateProgramVolume();
        return V / 3000;
    }

    // Расчет начальной надежности (в часах)
    public double calculateInitialReliability() {
        double Tk = calculateDevelopmentTime();
        double B = calculatePotentialErrors();
        double TkHours = Tk * workingHours; // перевод в часы
        return TkHours * reliabilityParam / (2 * Math.log(B));
    }

    private double calculateMinOperands() {
        double targets = systemParams[0];
        double measurements = systemParams[1];
        double trackedParams = systemParams[2];
        double calculatedParams = systemParams[3];
        return targets * measurements * trackedParams + targets * calculatedParams;
    }

    public void calculateAndPrint() {
        System.out.println("\n=== ЗАДАНИЕ №2 ===");
        System.out.println("Число модулей ПО (k): " + Math.round(calculateModulesCount()));
        System.out.println("Число уровней иерархии (i): " + Math.round(calculateHierarchyLevels()));
        System.out.println("Общее число модулей (K): " + Math.round(calculateTotalModules()));
        System.out.println("Длина программы (N): " + Math.round(calculateProgramLength()));
        System.out.println("Объем ПО (V): " + Math.round(calculateProgramVolume()));
        System.out.println("Количество команд ассемблера (P): " + Math.round(calculateAssemblyCommands()));
        System.out.println("Время разработки (Tk, дней): " + Math.round(calculateDevelopmentTime()));
        System.out.println("Потенциальное количество ошибок (B): " + Math.round(calculatePotentialErrors()));
        System.out.println("Начальная надежность (tн, часов): " + Math.round(calculateInitialReliability()));

        // Параметры расчета
        System.out.println("\nПараметры расчета:");
        System.out.println("Количество программистов (m): " + programmersCount);
        System.out.println("Производительность (v): " + productivity + " команд/день");
        System.out.println("Рабочих часов в день: " + workingHours);
        System.out.println("Параметр надежности (n): " + reliabilityParam);
    }
}

