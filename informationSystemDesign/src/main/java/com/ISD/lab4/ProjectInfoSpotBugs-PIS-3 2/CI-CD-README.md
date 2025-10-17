# CI/CD Пайплайны для проектов статического анализа

Этот репозиторий содержит CI/CD пайплайны для всех заданий по статическому анализу кода.

## 📁 Структура проектов

- **ProjectInfoSystem(zadanie1)** - Базовые примеры статического анализа
- **library** - Анализ библиотеки с SpotBugs и JSR-305
- **colt** - Анализ библиотеки Colt

## 🚀 Доступные CI/CD системы

### 1. GitHub Actions
- **Файлы**: `.github/workflows/*.yml`
- **Запуск**: Автоматически при push/PR
- **Ручной запуск**: `workflow_dispatch`

#### Доступные workflows:
- `zadanie1.yml` - Анализ базовых примеров
- `library.yml` - Анализ библиотеки
- `colt.yml` - Анализ Colt библиотеки

### 2. Jenkins
- **Файлы**: `*/Jenkinsfile`
- **Запуск**: Через Jenkins UI или REST API

#### Доступные pipelines:
- `ProjectInfoSystem(zadanie1)/Jenkinsfile`
- `library/Jenkinsfile`
- `colt/Jenkinsfile`

### 3. GitLab CI/CD
- **Файл**: `.gitlab-ci.yml`
- **Запуск**: Автоматически при push/merge request

### 4. Azure DevOps
- **Файл**: `azure-pipelines.yml`
- **Запуск**: Через Azure DevOps UI

## 🛠️ Локальная разработка

### Запуск всех пайплайнов локально:

#### Windows:
```bash
scripts/run-all-pipelines.bat
```

#### Linux/Mac:
```bash
chmod +x scripts/run-all-pipelines.sh
./scripts/run-all-pipelines.sh
```

### Запуск CI/CD серверов:

```bash
docker-compose up -d
```

Доступные сервисы:
- **Jenkins**: http://localhost:8080
- **SonarQube**: http://localhost:9000
- **Nexus**: http://localhost:8081

## 📊 Что делают пайплайны

### Для каждого проекта:

1. **Build Stage**:
   - Компиляция кода
   - Сборка артефактов

2. **Static Analysis Stage**:
   - Запуск SpotBugs
   - Генерация отчетов
   - Проверка качества кода

3. **Test Stage**:
   - Запуск примеров
   - Выполнение тестов

4. **Deploy Stage**:
   - Архивирование артефактов
   - Генерация документации

## 🔧 Настройка

### Предварительные требования:
- Java 11+
- Maven 3.6+
- Docker (для CI/CD серверов)

### Переменные окружения:
```bash
export JAVA_HOME=/path/to/java11
export MAVEN_HOME=/path/to/maven
```

## 📈 Мониторинг

### Отчеты SpotBugs:
- **Zadanie1**: `ProjectInfoSystem(zadanie1)/target/spotbugs/`
- **Library**: `library/spotbugs-*-report.txt`
- **Colt**: `colt/target/spotbugs/`

### Артефакты:
- JAR файлы в `target/`
- Документация в `target/site/`
- Отчеты анализа в `target/spotbugs/`

## 🚨 Устранение неполадок

### Проблемы с компиляцией:
1. Проверьте версию Java (должна быть 11+)
2. Убедитесь, что Maven установлен
3. Проверьте зависимости в pom.xml

### Проблемы с SpotBugs:
1. Убедитесь, что код скомпилирован
2. Проверьте конфигурацию SpotBugs
3. Посмотрите логи в target/spotbugs/

### Проблемы с Docker:
1. Убедитесь, что Docker запущен
2. Проверьте доступность портов
3. Посмотрите логи: `docker-compose logs`

## 📞 Поддержка

При возникновении проблем:
1. Проверьте логи CI/CD
2. Убедитесь в корректности конфигурации
3. Проверьте зависимости и версии

## 🔄 Обновление

Для обновления пайплайнов:
1. Внесите изменения в соответствующие файлы
2. Запустите локальные тесты
3. Зафиксируйте изменения в Git
4. Проверьте работу в CI/CD системах
