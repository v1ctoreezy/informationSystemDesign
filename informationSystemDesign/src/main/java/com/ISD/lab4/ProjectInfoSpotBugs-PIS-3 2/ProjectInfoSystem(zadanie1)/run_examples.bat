@echo off
echo ========================================
echo Start examples Java code
echo ========================================

echo.
echo Компиляция Java файлов...
javac -d . src/main/java/com/example/*.java

if %errorlevel% neq 0 (
    echo Error compilate!
    pause
    exit /b 1
)

echo.
echo ========================================
echo Start example 1: NullPointerException
echo ========================================
java com.example.Example1

echo.
echo ========================================
echo Start example 2: Thread Safety
echo ========================================
java com.example.Example2

echo.
echo ========================================
echo Start example 3: BigDecimal
echo ========================================
java com.example.Example3

echo.
echo ========================================
echo Start all examples
echo ========================================
java com.example.Main

echo.
echo All examples succesfull!
pause
