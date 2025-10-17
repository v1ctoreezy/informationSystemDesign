package ru.petrov.edu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrecisionAndPerformanceProblems {

    /**
     * Этот класс демонстрирует различные проблемы с точностью и производительностью,
     * которые может обнаружить SpotBugs.
     */

    public static void demonstratePrecisionIssues() {
        System.out.println("Демонстрация проблем с точностью вычислений:");

        // Проблема 1: Использование float/double для финансовых расчетов
        // SpotBugs найдет здесь ошибку FL_FLOATS_AS_LOOP_COUNTERS или CNT_ROUGH_CONSTANT_VALUE
        demonstrateFloatingPointPrecision();

        // Проблема 2: Неправильное сравнение чисел с плавающей точкой
        // SpotBugs найдет здесь ошибку FE_FLOATING_POINT_EQUALITY
        demonstrateFloatingPointComparison();
    }

    public static void demonstratePerformanceIssues() {
        System.out.println("Демонстрация проблем с производительностью:");

        // Проблема 1: Неэффективная работа со строками
        // SpotBugs найдет здесь ошибку SBSC_USE_STRINGBUFFER_CONCATENATION или DM_STRING_CTOR
        demonstrateIneffientStringUsage();

        // Проблема 2: Неэффективное использование коллекций
        // SpotBugs найдет здесь ошибки связанные с производительностью коллекций
        demonstrateIneffientCollectionUsage();

        // Проблема 3: Боксинг/анбоксинг в циклах
        // SpotBugs найдет здесь ошибку BX_UNBOXING_IMMEDIATELY_REBOXED
        demonstrateBoxingUnboxingIssues();
    }

    /**
     * Демонстрирует проблемы с точностью чисел с плавающей точкой
     */
    private static void demonstrateFloatingPointPrecision() {
        // Использование double для финансовых расчетов может привести к ошибкам округления
        double price = 19.99;
        double taxRate = 0.06;
        double tax = price * taxRate;

        System.out.println("Цена: " + price);
        System.out.println("Налоговая ставка: " + taxRate);
        System.out.println("Налог (неточно): " + tax);

        // Правильный подход - использовать BigDecimal для финансовых расчетов
        BigDecimal correctPrice = new BigDecimal("19.99");
        BigDecimal correctTaxRate = new BigDecimal("0.06");
        BigDecimal correctTax = correctPrice.multiply(correctTaxRate);

        System.out.println("Налог (точное значение с BigDecimal): " + correctTax);

        // Использование float в качестве счетчика цикла - очень плохая практика
        for (float i = 0; i < 1; i += 0.1) {
            System.out.println("Итерация: " + i); // Может никогда не достигнуть точно 1.0
        }
    }

    /**
     * Демонстрирует проблемы со сравнением чисел с плавающей точкой
     */
    private static void demonstrateFloatingPointComparison() {
        double a = 0.7;
        double b = 0.9 - 0.2; // Должно быть 0.7, но из-за ошибок округления может отличаться

        // Некорректное сравнение - может не сработать из-за неточности представления
        if (a == b) {
            System.out.println("a и b равны (прямое сравнение)");
        } else {
            System.out.println("a и b не равны (прямое сравнение)");
            System.out.println("a = " + a);
            System.out.println("b = " + b);
        }

        // Правильный способ: использовать эпсилон при сравнении
        final double EPSILON = 1e-10;
        if (Math.abs(a - b) < EPSILON) {
            System.out.println("a и b примерно равны (с учетом погрешности)");
        }
    }

    /**
     * Демонстрирует неэффективную работу со строками
     */
    private static void demonstrateIneffientStringUsage() {
        // Неэффективное использование конкатенации строк в цикле
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result += "Число " + i + ", ";  // Создает много временных объектов String
        }
        System.out.println("Длина результата конкатенации: " + result.length());

        // Создание ненужных объектов String
        String unnecessary = new String("Это неоптимально");  // Избыточный конструктор

        // Неэффективное сравнение строк
        String str1 = "Hello";
        String str2 = new String("Hello");

        // Некорректное сравнение строк (использование == вместо equals)
        if (str1 == str2) {  // Будет false, даже если содержимое одинаковое
            System.out.println("Строки равны (по ==)");
        } else {
            System.out.println("Строки не равны (по ==)");
        }
    }

    /**
     * Демонстрирует неэффективное использование коллекций
     */
    private static void demonstrateIneffientCollectionUsage() {
        // Использование коллекции с неоптимальной начальной емкостью
        List<Integer> list = new ArrayList<>();  // Без указания начальной емкости
        for (int i = 0; i < 10000; i++) {
            list.add(i);  // Вызовет многократное увеличение размера внутреннего массива
        }

        // Неэффективное использование Map
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            Integer value = map.get("key" + i);  // Проверка на наличие ключа
            if (value == null) {
                map.put("key" + i, 1);  // Отдельная операция вставки
            } else {
                map.put("key" + i, value + 1);  // Еще одна операция вставки
            }
            // Вместо этого можно использовать map.compute() или map.merge()
        }
    }

    /**
     * Демонстрирует проблемы с автоупаковкой и распаковкой примитивных типов
     */
    private static void demonstrateBoxingUnboxingIssues() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            numbers.add(i);  // Автоупаковка int в Integer
        }

        // Неэффективное использование распаковки и упаковки в цикле
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;  // Распаковка Integer в int
        }

        // Еще более неэффективный пример
        Integer total = 0;
        for (int i = 0; i < 1000; i++) {
            total += i;  // Каждая итерация создает новый объект Integer
        }

        System.out.println("Сумма: " + sum + ", Итого: " + total);
    }
}
