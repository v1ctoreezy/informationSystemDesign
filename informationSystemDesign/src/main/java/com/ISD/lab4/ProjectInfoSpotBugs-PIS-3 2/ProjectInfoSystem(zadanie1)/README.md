# Java Static Analysis Examples

Этот проект содержит три примера Java кода с различными проблемами, которые можно выявить с помощью статического анализа кода.

## Структура проекта

```
src/main/java/com/example/
├── Example1.java    # Проблемы с NullPointerException
├── Example2.java    # Проблемы с Thread Safety
├── Example3.java    # Проблемы с BigDecimal и форматированием
└── Main.java        # Главный класс для запуска всех примеров
```

## Примеры проблем

### Пример 1: NullPointerException
- **Проблема**: Гарантированный NullPointerException при приведении null к Integer
- **Код**: `n = Integer.valueOf(((Integer)null).intValue());`
- **Анализ**: SpotBugs обнаружит NP_NULL_ON_SOME_PATH

### Пример 2: Thread Safety
- **Проблема**: SimpleDateFormat не является thread-safe
- **Код**: Статическое поле DateFormat используется в многопоточной среде
- **Анализ**: SpotBugs обнаружит STCAL_INVOKE_ON_STATIC_DATE_FORMAT_INSTANCE

### Пример 3: BigDecimal и форматирование
- **Проблема**: Неправильное сравнение BigDecimal и неэффективное форматирование
- **Код**: `d1.equals(d2)` для чисел с разным масштабом
- **Анализ**: SpotBugs обнаружит DM_BIGDECIMAL_EQUALS

## Запуск примеров

### Windows:
```bash
run_examples.bat
```

### Linux/Mac:
```bash
# Компиляция
javac -d . src/main/java/com/example/*.java

# Запуск отдельных примеров
java com.example.Example1
java com.example.Example2
java com.example.Example3

# Запуск всех примеров
java com.example.Main
```

## Статический анализ

### С Maven и SpotBugs:
```bash
mvn clean compile
mvn spotbugs:check
```

### Результаты анализа:
Подробный отчет о найденных проблемах находится в файле `STATIC_ANALYSIS_REPORT.md`.

## Найденные проблемы

| Пример | Критические | Средние | Общая оценка |
|--------|-------------|---------|--------------|
| 1      | 1           | 2       | 🔴 Плохо     |
| 2      | 1           | 1       | 🔴 Плохо     |
| 3      | 0           | 3       | 🟡 Удовлетворительно |

## Исправления

Каждый пример содержит как проблемную, так и исправленную версию кода с подробными комментариями.

## Требования

- Java 11 или выше
- Maven (опционально, для SpotBugs)
- SpotBugs (для статического анализа)
