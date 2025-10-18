package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Пример 2: Проблемы с Thread Safety
 * 
 * Этот код содержит проблемы:
 * 1. SimpleDateFormat не является thread-safe
 * 2. Статическое поле format может вызывать проблемы в многопоточной среде
 */
public class Example2 {
    
    // ПРОБЛЕМА: SimpleDateFormat не thread-safe
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args) {
        Example2 example = new Example2();
        
        System.out.println("=== Тест проблемного метода ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("Дата " + (i + 1) + ": " + example.getDate());
        }
        
        System.out.println("\n=== Тест исправленного метода ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("Дата " + (i + 1) + ": " + example.getDateFixed());
        }
    }
    
    /**
     * Проблемный метод из примера
     */
    public String getDate() {
        return format.format(new Date());
    }
    
    /**
     * Исправленная версия - создаем новый экземпляр каждый раз
     */
    public String getDateFixed() {
        DateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return localFormat.format(new Date());
    }
    
    /**
     * Альтернативное решение - использование ThreadLocal
     */
    private static final ThreadLocal<DateFormat> threadLocalFormat = 
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    
    public String getDateThreadSafe() {
        return threadLocalFormat.get().format(new Date());
    }
    
    /**
     * Современное решение - использование java.time API
     */
    public String getDateModern() {
        return java.time.LocalDateTime.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
