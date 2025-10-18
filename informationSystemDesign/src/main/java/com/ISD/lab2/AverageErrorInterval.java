package com.ISD.lab2;

import java.util.List;

public class AverageErrorInterval {
    public float AverageErrorIntervalCalc (float c, float k1, List<Integer> errorInterval) {
        float t1 = 0;
        t1=1/(k1*(c-errorInterval.size()));
        System.out.printf("Среднее время до появления ошибки: %.1f (Часов)%n", t1);
        return t1;
    }
}
