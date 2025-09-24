package com.ISD;

import com.ISD.lab5.*;
import com.ISD.utils.DocumentReader;
import com.ISD.utils.SplitLines;

import java.util.ArrayList;

public class Main {
    public static void runReliabilityCalculations() {
        ArrayList<String> list = DocumentReader.fileLineReader("/Users/victorcerkasov/Documents/informationSystemDesign-main/informationSystemDesign/src/main/java/com/ISD/lab5/file1");
        double [] averageRecoveryTime = SplitLines.splitLines(SplitLines.subStrings(list,2));
        double [] estDurTransIO = SplitLines.splitLines(SplitLines.subStrings(list,4));
        double probability = ProbabilityWithoutFailures.probabilityCalculations(list);
        double averageRecTimeEstimate = AverageRecoveryTime.averageRecoveryTimeEstimate(list,AverageRecoveryTime.recoveryTime(averageRecoveryTime));
        double estDurTransformIO = EstimationDurationTransformationIO.estimateDurationIO(EstimationDurationTransformationIO.actualConversionDuration(list,estDurTransIO));

        System.out.println("Вероятность безотказной работы (P): "+probability);
        System.out.println("Оценка по среднему времени восстановления (Qв): "+averageRecTimeEstimate);
        System.out.println("Оценка по продолжительности преобразования входного набора данных в выходной (Qп): "+estDurTransformIO);
        System.out.println("Итоговая оценка вероятности безотказной работы : "+probability);
        System.out.println("Итоговая оценка по среднему времени восстановления и продолжительности преобразования : "+ MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO));
        System.out.println("Абсолютный показатель критериев : "+ MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)));
        System.out.println("Относительный показатель критериев : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(list.get(6))));
        System.out.println("Фактор надежности : "+ MetricsCalculations.relativeIndicatorsCalculation(MetricsCalculations.absoluteIndicatorsCalculation(probability, MetricsCalculations.finalGradeCalculation(averageRecTimeEstimate,estDurTransformIO)),Double.parseDouble(list.get(6))));
    }

    public static void main(String[] args) {
        runReliabilityCalculations();
    }
}