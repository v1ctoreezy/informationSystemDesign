package com.ISD.lab2;

import java.util.List;

public class ScaleFactor {


    public float ScaleFactorCalc (float c, List<Integer> errorInterval, List<Integer> errorNumber ) {
        float k1 = 0;
        float k2 = 0;
        float partFirstB = 0;
        float partOfPartFirstB = 0;
        float partSecondB = 0;
        float partThirdB = 0;

        for (int i = 0; i<errorInterval.size(); i++) {

            partOfPartFirstB=(c-i)*errorInterval.get(i);
            partFirstB = partFirstB+partOfPartFirstB;

            partSecondB = partSecondB+errorInterval.get(i);

            partThirdB = partThirdB+errorNumber.get(i)*errorInterval.get(i);



        }


        k1=errorInterval.size()/partFirstB;
        System.out.printf("Коэффициент пропорциональности : %.3f%n", k1);



        return k1;

    }
}
