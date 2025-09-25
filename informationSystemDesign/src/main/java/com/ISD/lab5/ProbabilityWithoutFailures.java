package com.ISD.lab5;

import java.util.ArrayList;

public class ProbabilityWithoutFailures {
    public static double probabilityCalculations (ArrayList <String> lines) {
        int numberOfExperiments = Integer.parseInt(lines.get(1));
        int numberOfFailures = Integer.parseInt(lines.get(0));
        return 1 - (double) numberOfFailures / (double) numberOfExperiments;
    }
}

