package org.example;

import java.util.List;

public class TestingStartTime {

    public float TestingStartTimeCalc (float c, float k1, List<Integer> errorInterval,  List<Integer> errorNumber) {

        float t1 = 0;
        float partFirstG = 0;

        int newc = Math.round(c);

        for (int i = 0 ; i<(newc-errorInterval.size()); i++) {
            partFirstG = partFirstG+1/errorNumber.get(i).floatValue();

        }

        t1=(1/k1)*partFirstG;
        System.out.printf("Время до начала тестирования: %.1f (Часов)%n", t1);

        return t1;
    }
}
