package ru.petrov.edu;

import java.util.HashMap;
import java.util.Map;

public class NullPointerProblems {

    /**
     * Этот класс демонстрирует различные проблемы, связанные с null-значениями,
     * которые может обнаружить SpotBugs.
     */
    public static void demonstrateNullPointerIssues() {
        // Проблема 1: Разыменование возможного null значения
        // SpotBugs найдет здесь ошибку NP_NULL_ON_SOME_PATH
        demonstrateNullDereference(null);

        // Проблема 2: Возврат null значения из метода без проверки
        // SpotBugs найдет здесь ошибку NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
        String value = returnPossiblyNull();
        System.out.println("Длина строки: " + value.length());

        // Проблема 3: Использование null в качестве ключа HashMap
        // SpotBugs найдет здесь ошибку NP_NULL_ON_SOME_PATH или VA_PRIMITIVE_ARRAY_PASSED_TO_OBJECT_VARARG
        demonstrateNullKeyInMap();
    }

    /**
     * Метод, который может вызвать NullPointerException
     * SpotBugs должен обнаружить, что параметр input может быть null
     */
    private static void demonstrateNullDereference(String input) {
        // Здесь нет проверки input на null
        int length = input.length();  // Потенциальный NullPointerException
        System.out.println("Длина входной строки: " + length);
    }

    /**
     * Метод, который может вернуть null
     */
    private static String returnPossiblyNull() {
        // Метод иногда возвращает null, но об этом нет документации
        if (Math.random() > 0.5) {
            return null;  // Потенциальная проблема для вызывающего кода
        }
        return "Not null value";
    }

    /**
     * Демонстрирует проблему с использованием null в качестве ключа карты
     */
    private static void demonstrateNullKeyInMap() {
        Map<String, Integer> map = new HashMap<>();
        String key = null;

        map.put(key, 42);  // Использование null в качестве ключа

        // Некоторые реализации Map не позволяют использовать null в качестве ключа
        // Например, ConcurrentHashMap вызовет NullPointerException
        System.out.println("Значение: " + map.get(key));
    }
}
