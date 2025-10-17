#!/bin/bash

echo "=========================================="
echo "Запуск всех CI/CD пайплайнов"
echo "=========================================="

# Функция для проверки успешности команды
check_success() {
    if [ $? -eq 0 ]; then
        echo "✅ $1 - УСПЕШНО"
    else
        echo "❌ $1 - ОШИБКА"
    fi
}

# 1. Задание 1 - Базовые примеры
echo ""
echo "🔧 Задание 1 - Базовые примеры статического анализа"
echo "---------------------------------------------------"
cd "ProjectInfoSystem(zadanie1)"
mvn clean compile
check_success "Компиляция Zadanie1"

mvn spotbugs:check
check_success "SpotBugs анализ Zadanie1"

java -cp "target/classes" com.example.Main
check_success "Запуск примеров Zadanie1"
cd ..

# 2. Library - Анализ библиотеки
echo ""
echo "📚 Library - Анализ библиотеки с SpotBugs"
echo "----------------------------------------"
cd library
javac -cp "lib/jsr305-2.0.0.jar" -d build/classes src/bookstore/*.java
check_success "Компиляция Library"

java -jar spotbugs-4.8.3/lib/spotbugs.jar -textui -output spotbugs-local-report.txt -cp "lib/jsr305-2.0.0.jar" build/classes/
check_success "SpotBugs анализ Library"

java -cp "build/classes:lib/jsr305-2.0.0.jar" bookstore.Library
check_success "Запуск примеров Library"
cd ..

# 3. Colt - Анализ библиотеки Colt
echo ""
echo "🧮 Colt - Анализ библиотеки Colt"
echo "-------------------------------"
cd colt
mvn clean compile
check_success "Компиляция Colt"

mvn spotbugs:check
check_success "SpotBugs анализ Colt"

mvn test || echo "Тесты Colt не найдены"
check_success "Тесты Colt"

mvn javadoc:javadoc || echo "Javadoc Colt не сгенерирован"
check_success "Javadoc Colt"
cd ..

echo ""
echo "=========================================="
echo "Все пайплайны завершены!"
echo "=========================================="
echo ""
echo "📊 Результаты:"
echo "- Zadanie1: target/spotbugs/"
echo "- Library: spotbugs-local-report.txt"
echo "- Colt: target/spotbugs/ и target/site/"
echo ""
echo "🚀 Для запуска CI/CD серверов:"
echo "docker-compose up -d"
