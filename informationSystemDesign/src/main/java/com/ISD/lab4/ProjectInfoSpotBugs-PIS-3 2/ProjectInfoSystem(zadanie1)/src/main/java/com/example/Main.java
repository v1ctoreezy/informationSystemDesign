package com.example;
/**
 * Главный класс для запуска всех примеров
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Демонстрация проблем в Java коде ===\n");
        
        // Запуск примера 1
        System.out.println("ПРИМЕР 1: Проблемы с NullPointerException");
        System.out.println("==========================================");
        Example1 example1 = new Example1();
        example1.testMethod(true, false);
        example1.testMethod(false, true);
        example1.testMethod(false, false);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Запуск примера 2
        System.out.println("ПРИМЕР 2: Проблемы с Thread Safety");
        System.out.println("===================================");
        Example2 example2 = new Example2();
        example2.getDate();
        example2.getDateFixed();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Запуск примера 3
        System.out.println("ПРИМЕР 3: Проблемы с BigDecimal и форматированием");
        System.out.println("=================================================");
        Example3 example3 = new Example3();
        example3.testBigDecimalEquals();
        example3.testStringFormatting();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        System.out.println("Все примеры выполнены. Проверьте результаты статического анализа!");
    }
}
