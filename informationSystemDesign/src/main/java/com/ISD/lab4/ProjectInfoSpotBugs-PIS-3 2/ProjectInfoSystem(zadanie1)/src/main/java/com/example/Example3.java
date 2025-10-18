package com.example;

import java.math.BigDecimal;

/**
 * Пример 3: Проблемы с BigDecimal и форматированием
 * 
 * Этот код содержит проблемы:
 * 1. BigDecimal.equals() сравнивает и значение, и масштаб
 * 2. Неэффективное использование System.out.printf для простых строк
 */
public class Example3 {
    
    public static void main(String[] args) {
        Example3 example = new Example3();
        
        System.out.println("=== Тест BigDecimal.equals() ===");
        example.testBigDecimalEquals();
        
        System.out.println("\n=== Тест форматирования строк ===");
        example.testStringFormatting();
    }
    
    /**
     * Проблемный метод из примера
     */
    public void testBigDecimalEquals() {
        BigDecimal d1 = new BigDecimal("1.1");
        BigDecimal d2 = new BigDecimal("1.10");
        
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d1.equals(d2) = " + d1.equals(d2)); // false!
        System.out.println("d1.compareTo(d2) = " + d1.compareTo(d2)); // 0 (равны)
    }
    
    /**
     * Исправленная версия
     */
    public void testBigDecimalEqualsFixed() {
        BigDecimal d1 = new BigDecimal("1.1");
        BigDecimal d2 = new BigDecimal("1.10");
        
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d1.equals(d2) = " + d1.equals(d2)); // false
        System.out.println("d1.compareTo(d2) == 0 = " + (d1.compareTo(d2) == 0)); // true
    }
    
    /**
     * Проблемный метод форматирования из примера
     */
    public void testStringFormatting() {
        // Неэффективное использование printf для простой строки
        System.out.printf("%s\n", "str#1");
        System.out.println("str#2");
    }
    
    /**
     * Исправленная версия форматирования
     */
    public void testStringFormattingFixed() {
        // Более эффективно использовать println для простых строк
        System.out.println("str#1");
        System.out.println("str#2");
        
        // printf следует использовать только когда нужны спецификаторы формата
        String name = "Java";
        int version = 11;
        System.out.printf("Язык: %s, версия: %d\n", name, version);
    }
    
    /**
     * Дополнительные примеры проблем с BigDecimal
     */
    public void demonstrateBigDecimalIssues() {
        // Проблема с конструктором double
        BigDecimal bad1 = new BigDecimal(0.1); // Неточность!
        BigDecimal good1 = new BigDecimal("0.1"); // Точность
        
        System.out.println("new BigDecimal(0.1) = " + bad1);
        System.out.println("new BigDecimal(\"0.1\") = " + good1);
        
        // Проблема с масштабом
        BigDecimal a = new BigDecimal("1.00");
        BigDecimal b = new BigDecimal("1.0");
        BigDecimal c = new BigDecimal("1");
        
        System.out.println("1.00.equals(1.0) = " + a.equals(b)); // false
        System.out.println("1.0.equals(1) = " + b.equals(c)); // false
        System.out.println("1.00.equals(1) = " + a.equals(c)); // false
        
        // Правильное сравнение
        System.out.println("1.00.compareTo(1.0) == 0 = " + (a.compareTo(b) == 0)); // true
        System.out.println("1.0.compareTo(1) == 0 = " + (b.compareTo(c) == 0)); // true
    }
}
