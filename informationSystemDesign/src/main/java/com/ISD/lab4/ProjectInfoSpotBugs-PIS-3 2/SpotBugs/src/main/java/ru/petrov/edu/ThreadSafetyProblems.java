package ru.petrov.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSafetyProblems {

    /**
     * Этот класс демонстрирует различные проблемы с потокобезопасностью,
     * которые может обнаружить SpotBugs.
     */
    public static void demonstrateThreadSafetyIssues() {
        // Проблема 1: Небезопасная инициализация Lazy Singleton
        // SpotBugs найдет здесь ошибку LI_LAZY_INIT_STATIC или IS2_INCONSISTENT_SYNC
        System.out.println("Небезопасный синглтон: " + UnsafeSingleton.getInstance());

        // Проблема 2: Небезопасное использование коллекций
        // SpotBugs найдет здесь ошибку IS2_INCONSISTENT_SYNC
        demonstrateUnsafeCollectionUsage();

        // Проблема 3: Неволатильные поля, используемые несколькими потоками
        // SpotBugs найдет здесь ошибку VO_VOLATILE_INCREMENT
        demonstrateNonVolatileSharing();
    }

    /**
     * Демонстрирует небезопасное использование коллекций в многопоточной среде
     */
    private static void demonstrateUnsafeCollectionUsage() {
        // Создаем обычную коллекцию (не синхронизированную)
        UnsafeCollectionHolder holder = new UnsafeCollectionHolder();

        // Запускаем несколько потоков, которые будут писать в коллекцию
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int num = i;
            executor.submit(() -> {
                holder.addItem("Item " + num);
            });
        }

        // Пытаемся корректно завершить работу пула потоков
        try {
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Ошибка при ожидании завершения потоков: " + e.getMessage());
        }

        System.out.println("Добавлено элементов в небезопасную коллекцию: " + holder.getItemCount());
    }

    /**
     * Демонстрирует проблемы с неволатильным разделяемым состоянием
     */
    private static void demonstrateNonVolatileSharing() {
        NonVolatileCounter counter = new NonVolatileCounter();

        // Запускаем несколько потоков, которые будут увеличивать счетчик
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
        }

        // Пытаемся корректно завершить работу пула потоков
        try {
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Ошибка при ожидании завершения потоков: " + e.getMessage());
        }

        System.out.println("Итоговое значение счетчика: " + counter.getCount() +
                " (ожидаемое значение: 3000)");
    }

    /**
     * Класс с небезопасной инициализацией синглтона (Lazy initialization)
     * SpotBugs обнаружит здесь проблему с инициализацией в многопоточной среде
     */
    private static class UnsafeSingleton {
        // Статический экземпляр не является volatile
        private static UnsafeSingleton instance;

        private UnsafeSingleton() {
            // Приватный конструктор
        }

        // Метод не синхронизирован, что приводит к проблемам в многопоточной среде
        public static UnsafeSingleton getInstance() {
            if (instance == null) {
                // Возможна ситуация, когда два потока одновременно проверят условие
                // и создадут два разных экземпляра
                instance = new UnsafeSingleton();
            }
            return instance;
        }

        @Override
        public String toString() {
            return "Я небезопасный синглтон @" + Integer.toHexString(hashCode());
        }
    }

    /**
     * Класс, демонстрирующий небезопасное использование коллекции
     * SpotBugs обнаружит здесь проблему с несинхронизированным доступом
     */
    private static class UnsafeCollectionHolder {
        // Не используется синхронизированная коллекция или потокобезопасная реализация
        private final List<String> items = new ArrayList<>();

        // Метод не синхронизирован
        public void addItem(String item) {
            items.add(item);
        }

        // Метод не синхронизирован
        public int getItemCount() {
            return items.size();
        }
    }

    /**
     * Класс, демонстрирующий проблемы с неволатильным разделяемым состоянием
     * SpotBugs обнаружит здесь проблему с отсутствием volatile для поля, используемого несколькими потоками
     */
    private static class NonVolatileCounter {
        // Не объявлено как volatile
        private int count;

        // Операция инкремента не атомарна
        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
