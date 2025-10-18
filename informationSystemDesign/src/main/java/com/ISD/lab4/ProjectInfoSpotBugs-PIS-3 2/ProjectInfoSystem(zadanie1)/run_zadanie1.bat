@echo off
echo ========================================
echo Запуск Задания 1: Базовые примеры
echo ========================================

echo.
echo Компиляция Java файлов...
javac -d . src/main/java/com/example/*.java

if %errorlevel% neq 0 (
    echo Ошибка компиляции!
    pause
    exit /b 1
)

echo.
echo ========================================
echo Запуск всех примеров
echo ========================================
java com.example.Main

echo.
echo ========================================
echo Запуск отдельных примеров
echo ========================================

echo.
echo === Пример 1: NullPointerException ===
java com.example.Example1

echo.
echo === Пример 2: Thread Safety ===
java com.example.Example2

echo.
echo === Пример 3: BigDecimal ===
java com.example.Example3

echo.
echo Все примеры выполнены!
pause
