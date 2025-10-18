package com.ISD.lab2;

import java.util.List;


public class NumberOfErrors {

    public float BugsCalc  (float a, float b, float e, List<Integer> errorInterval, List<Integer> errorNumber) {
        float c = 0;
        float ya = 0;
        float yb = 0;
        float yc = 0;
        float partOfFirstPartA =0;
        float firstPartA =0;

        float partOfFirstPartB =0;
        float firstPartB =0;

        float partOfFirstPartC =0;
        float firstPartC =0;

        float secondPart =0;
        float thirdPart =0;

        float x;

        System.out.println  ("|LowerPoint a |MiddlePoint c|UpperPoint b |B(a)       |B(с)         |B(b)        |LeftPart(a)  |LeftPart(b) |LeftPart(c)  |RightPartDivisible |RightPartDivider|");


        while(Math.abs(b-a)>e) {
            c = (a+b)/2;

            firstPartA = 0; //левая часть уравнения (1/(B+i-1) от переменной a
            firstPartB = 0; //левая часть уравнения (1/(B+i-1) от переменной b
            firstPartC = 0;	//левая часть уравнения (1/(B+i-1) от переменной c
            secondPart = 0;//делимое правой части уравнения
            thirdPart = 0; //делитель правой части уравнения
            for (int i = 0; i<errorInterval.size(); i++ ) {


                partOfFirstPartA = 1/(a-errorNumber.get(i)+1);
                firstPartA = firstPartA+partOfFirstPartA;

                partOfFirstPartB = 1/(b-errorNumber.get(i)+1);
                firstPartB = firstPartB+partOfFirstPartB;

                partOfFirstPartC = 1/(c-errorNumber.get(i)+1);
                firstPartC = firstPartC+partOfFirstPartC;

                secondPart =secondPart+ errorInterval.get(i);

                thirdPart = thirdPart+errorNumber.get(i)*errorInterval.get(i);
            }

            ya = firstPartA-((errorInterval.size()*secondPart)/((a+1)*secondPart-thirdPart));
            yb = firstPartB-((errorInterval.size()*secondPart)/((b+1)*secondPart-thirdPart));
            yc = firstPartC-((errorInterval.size()*secondPart)/((c+1)*secondPart-thirdPart));


            String str = String.format (
                    "|%1$+.8f |%2$+.8f |%3$+.8f |%4$+.8f|%5$+.8f  |%6$+.8f |%7$+.8f  |%8$+.8f |%9$+.8f  |%10$+.8f      |%11$+.8f  |" ,
                    a, c, b, ya, yb, yc,	firstPartA, firstPartB, firstPartC, secondPart, thirdPart);
            System.out.println(str);



            if (ya*yc<0) {
                a=a; b=c;
            }
            else if (yb*yc<0) {
                a=c; b=b;

            }
            else {
                System.out.println ("На заданном промежутке ошибка: нет корня / значения точек диапазона не в области определения функции / на промежутке несколько корней");
                c = (Float) null;
                break;
            }

        }

        System.out.printf("Величина B равна: %.2f (Ошибок)%n", c);

        return c;
    }

}

