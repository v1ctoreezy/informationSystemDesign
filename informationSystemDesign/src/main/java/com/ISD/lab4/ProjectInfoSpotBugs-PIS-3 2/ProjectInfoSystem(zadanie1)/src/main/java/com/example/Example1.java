package com.example;

/**
 * Пример 1: Проблемы с NullPointerException
 * 
 * Этот код содержит потенциальные проблемы:
 * 1. Переменная n может быть не инициализирована
 * 2. Явное приведение к null может вызвать NullPointerException
 * 3. Избыточные вызовы Integer.valueOf() и intValue()
 */
public class Example1 {
    
    public static void main(String[] args) {
        Example1 example = new Example1();
        
        // Тестируем различные сценарии
        System.out.println("=== Тест 1: flag1 = true ===");
        example.testMethod(true, false);
        
        System.out.println("=== Тест 2: flag1 = false, flag2 = true ===");
        example.testMethod(false, true);
        
        System.out.println("=== Тест 3: flag1 = false, flag2 = false ===");
        example.testMethod(false, false);
    }
    
    /**
     * Проблемный метод из примера
     */
    public void testMethod(boolean flag1, boolean flag2) {
        Integer n;
        if (flag1) {
            n = Integer.valueOf(1);
        } else {
            if (flag2) {
                n = Integer.valueOf(Integer.valueOf(2).intValue());
            } else {
                // Потенциальная проблема: приведение null к Integer
                n = Integer.valueOf(((Integer) null).intValue());
            }
        }
        
        System.out.println("Результат: " + n);
    }
    /**
     * Исправленная версия метода
     */
    public void testMethodFixed(boolean flag1, boolean flag2) {
        Integer n;
        if (flag1) {
            n = 1; // Автоупаковка вместо Integer.valueOf(1)
        } else {
            if (flag2) {
                n = 2; // Упрощенное присваивание
            } else {
                n = null; // Явное присваивание null
            }
        }
        
        if (n != null) {
            System.out.println("Результат: " + n);
        } else {
            System.out.println("Результат: null");
        }
    }
}
