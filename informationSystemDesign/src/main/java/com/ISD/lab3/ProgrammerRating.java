package com.ISD.lab3;

public class ProgrammerRating extends HalsteadMetrics {
    private double initialRating = 1000;
    private double[] volumes = {5, 7, 9, 11};
    private int[] errors = {0, 2, 5, 4};
    private double newVolume = 15;

    public double calculateRating(double[] volumes, int[] errors, double lambda, double initialRating) {
        double sumV = 0;
        double sumB = 0;

        for (int i = 0; i < volumes.length; i++) {
            sumV += volumes[i];
            sumB += errors[i] / (lambda + initialRating);
        }

        return initialRating * (1 + 0.001 * (sumV - sumB));
    }

    public double calculateExpectedErrors(double rating, double lambda, double volume) {
        return volume / (lambda + rating);
    }
}

