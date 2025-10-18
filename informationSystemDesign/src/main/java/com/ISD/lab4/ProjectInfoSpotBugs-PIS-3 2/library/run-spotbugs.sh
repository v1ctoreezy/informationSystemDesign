#!/bin/bash
cd /home/beloecoleno/IdeaProjects/ProjectInfoSystem/library

# Компилируем проект, если необходимо
if [ ! -d "build/classes/bookstore" ] || [ "src/bookstore/Book.java" -nt "build/classes/bookstore/Book.class" ]; then
    echo "Компилирую проект..."
    mkdir -p build/classes
    javac -cp lib/jsr305-2.0.0.jar -d build/classes src/bookstore/*.java
fi

# Запускаем SpotBugs
echo "Запускаю SpotBugs анализ..."
./spotbugs-4.8.3/bin/spotbugs -textui -low -effort:max -auxclasspath lib/jsr305-2.0.0.jar build/classes/

echo "Анализ завершен!"
