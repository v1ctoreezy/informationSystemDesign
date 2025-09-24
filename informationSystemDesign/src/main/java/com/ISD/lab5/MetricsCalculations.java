package com.ISD.lab5;

public class MetricsCalculations { // расчет метрик
    public static double finalGradeCalculation(double firstMetric, double secondMetric) { // Итоговая оценка метрики
        return (firstMetric + secondMetric)/2;
    }
    public static double absoluteIndicatorsCalculation(double firstMetric, double secondMetric) { // абсолютные показатели критериев
        return firstMetric*0.5 + secondMetric*0.5;
    }
    public static double relativeIndicatorsCalculation(double absoluteMetric, double baseMetric) {
        return absoluteMetric / baseMetric;
    }
}

