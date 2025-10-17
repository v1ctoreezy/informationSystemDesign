package ru.petrov.edu;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестирование проблем с NullPointerException ===");
        NullPointerProblems.demonstrateNullPointerIssues();

        System.out.println("\n=== Тестирование проблем с потокобезопасностью ===");
        ThreadSafetyProblems.demonstrateThreadSafetyIssues();

        System.out.println("\n=== Тестирование проблем с точностью и производительностью ===");
        PrecisionAndPerformanceProblems.demonstratePrecisionIssues();
        PrecisionAndPerformanceProblems.demonstratePerformanceIssues();

        System.out.println("\n=== Тестирование других проблем ===");
        OtherProblems.demonstrateOtherIssues();
    }
}