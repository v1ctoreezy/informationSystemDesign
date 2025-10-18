@echo off
echo ==========================================
echo Запуск всех CI/CD пайплайнов
echo ==========================================

REM 1. Задание 1 - Базовые примеры
echo.
echo 🔧 Задание 1 - Базовые примеры статического анализа
echo ---------------------------------------------------
cd "ProjectInfoSystem(zadanie1)"
call mvn clean compile
if %errorlevel% equ 0 (
    echo ✅ Компиляция Zadanie1 - УСПЕШНО
) else (
    echo ❌ Компиляция Zadanie1 - ОШИБКА
)

call mvn spotbugs:check
if %errorlevel% equ 0 (
    echo ✅ SpotBugs анализ Zadanie1 - УСПЕШНО
) else (
    echo ❌ SpotBugs анализ Zadanie1 - ОШИБКА
)

java -cp "target/classes" com.example.Main
if %errorlevel% equ 0 (
    echo ✅ Запуск примеров Zadanie1 - УСПЕШНО
) else (
    echo ❌ Запуск примеров Zadanie1 - ОШИБКА
)
cd ..

REM 2. Library - Анализ библиотеки
echo.
echo 📚 Library - Анализ библиотеки с SpotBugs
echo ----------------------------------------
cd library
javac -cp "lib/jsr305-2.0.0.jar" -d build/classes src/bookstore/*.java
if %errorlevel% equ 0 (
    echo ✅ Компиляция Library - УСПЕШНО
) else (
    echo ❌ Компиляция Library - ОШИБКА
)

java -jar spotbugs-4.8.3/lib/spotbugs.jar -textui -output spotbugs-local-report.txt -cp "lib/jsr305-2.0.0.jar" build/classes/
if %errorlevel% equ 0 (
    echo ✅ SpotBugs анализ Library - УСПЕШНО
) else (
    echo ❌ SpotBugs анализ Library - ОШИБКА
)

java -cp "build/classes;lib/jsr305-2.0.0.jar" bookstore.Library
if %errorlevel% equ 0 (
    echo ✅ Запуск примеров Library - УСПЕШНО
) else (
    echo ❌ Запуск примеров Library - ОШИБКА
)
cd ..

REM 3. Colt - Анализ библиотеки Colt
echo.
echo 🧮 Colt - Анализ библиотеки Colt
echo -------------------------------
cd colt
call mvn clean compile
if %errorlevel% equ 0 (
    echo ✅ Компиляция Colt - УСПЕШНО
) else (
    echo ❌ Компиляция Colt - ОШИБКА
)

call mvn spotbugs:check
if %errorlevel% equ 0 (
    echo ✅ SpotBugs анализ Colt - УСПЕШНО
) else (
    echo ❌ SpotBugs анализ Colt - ОШИБКА
)

call mvn test
if %errorlevel% equ 0 (
    echo ✅ Тесты Colt - УСПЕШНО
) else (
    echo ⚠️ Тесты Colt - НЕ НАЙДЕНЫ или ОШИБКА
)

call mvn javadoc:javadoc
if %errorlevel% equ 0 (
    echo ✅ Javadoc Colt - УСПЕШНО
) else (
    echo ⚠️ Javadoc Colt - НЕ СГЕНЕРИРОВАН или ОШИБКА
)
cd ..

echo.
echo ==========================================
echo Все пайплайны завершены!
echo ==========================================
echo.
echo 📊 Результаты:
echo - Zadanie1: target/spotbugs/
echo - Library: spotbugs-local-report.txt
echo - Colt: target/spotbugs/ и target/site/
echo.
echo 🚀 Для запуска CI/CD серверов:
echo docker-compose up -d
echo.
pause
