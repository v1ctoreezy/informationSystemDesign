package ru.petrov.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class OtherProblems {

    /**
     * Этот класс демонстрирует различные другие проблемы,
     * которые может обнаружить SpotBugs.
     */
    public static void demonstrateOtherIssues() {
        // Проблема 1: Утечки ресурсов
        // SpotBugs найдет здесь ошибку OS_OPEN_STREAM или OS_OPEN_STREAM_EXCEPTION_PATH
        demonstrateResourceLeak();

        // Проблема 2: Использование неэффективного Random
        // SpotBugs найдет здесь ошибку DMI_RANDOM_USED_ONLY_ONCE
        demonstrateRandomIssues();

        // Проблема 3: Возможная потеря точности при преобразовании типов
        // SpotBugs найдет здесь ошибку FL_PRECISION_LOSS или NP_UNWRITTEN_FIELD
        demonstratePrecisionLossInCasts();

        // Проблема 4: Пустой блок catch
        // SpotBugs найдет здесь ошибку DE_MIGHT_IGNORE
        demonstrateEmptyCatch();

        // Проблема 5: Сложные условия сравнения
        // SpotBugs найдет здесь ошибку SF_SWITCH_NO_DEFAULT или SF_DEAD_STORE_DUE_TO_SWITCH_FALLTHROUGH
        demonstrateComplexConditions();
    }

    /**
     * Демонстрирует утечку ресурсов (незакрытый поток)
     */
    private static void demonstrateResourceLeak() {
        try {
            // Создаем тестовый файл
            File tempFile = File.createTempFile("spotbugs_test", ".tmp");
            tempFile.deleteOnExit();

            // Открываем поток, но не закрываем его - утечка ресурса!
            InputStream is = new FileInputStream(tempFile);

            // Используем поток
            int data = is.read();

            // Правильный подход - использовать try-with-resources
            // try (InputStream is2 = new FileInputStream(tempFile)) { ... }

            System.out.println("Прочитан первый байт: " + data);

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует неправильное использование класса Random
     */
    private static void demonstrateRandomIssues() {
        // Создание нового объекта Random для каждого случайного числа - неэффективно
        int random1 = new Random().nextInt(100);
        System.out.println("Случайное число 1: " + random1);

        // Повторное создание Random с тем же зерном - приведет к одинаковым последовательностям
        Random r1 = new Random(42);
        Random r2 = new Random(42);

        System.out.println("r1: " + r1.nextInt() + ", r2: " + r2.nextInt());
        System.out.println("r1: " + r1.nextInt() + ", r2: " + r2.nextInt());
    }

    /**
     * Демонстрирует потерю точности при приведении типов
     */
    private static void demonstratePrecisionLossInCasts() {
        long bigNumber = 1234567890123L;

        // Потеря точности при приведении long к int
        int truncated = (int) bigNumber;
        System.out.println("Исходное число: " + bigNumber);
        System.out.println("После приведения к int: " + truncated);

        // Потеря точности при приведении double к float
        double largeDouble = 1.23456789012345;
        float smallerFloat = (float) largeDouble;
        System.out.println("Double: " + largeDouble);
        System.out.println("Float: " + smallerFloat);
    }

    /**
     * Демонстрирует пустой блок catch
     */
    private static void demonstrateEmptyCatch() {
        try {
            // Попытка выполнить рискованную операцию
            Object obj = null;
            obj.toString();  // Вызовет NullPointerException
        } catch (Exception e) {
            // Пустой блок catch - опасная практика!
            // SpotBugs обнаружит, что исключение игнорируется
        }

        // Также демонстрирует проблему с возвратом значения в блоке finally
        System.out.println("Результат вычисления: " + methodWithFinallyReturn());
    }

    /**
     * Метод с возвратом значения в блоке finally - антипаттерн
     */
    private static int methodWithFinallyReturn() {
        try {
            System.out.println("Выполняем что-то в блоке try");
            return 1;  // Это значение будет перезаписано блоком finally!
        } catch (Exception e) {
            return -1;  // Это значение тоже будет перезаписано
        } finally {
            // Возврат значения в finally переопределяет любой другой return
            return 0;  // SpotBugs должен обнаружить эту проблему
        }
    }

    /**
     * Демонстрирует сложные условия сравнения
     */
    private static void demonstrateComplexConditions() {
        // Оператор switch без default - плохая практика
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                // Отсутствие break - неявное падение в следующий case
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            // Отсутствует default
        }

        // Сложное условие с побочными эффектами
        int a = 5;
        int b = 10;

        // Побочный эффект в условии - плохая практика
        if (a > 0 && (b = a + 5) > 10) {
            System.out.println("Условие истинно, b = " + b);
        } else {
            System.out.println("Условие ложно, b = " + b);
        }
    }
}
