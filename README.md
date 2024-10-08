# ШИФТ. Тестовое задание. Курс Java. Утилита фильтрации содержимого файлов. 2024

## Технические средства
- **Версия Java:** [openjdk 22.0.2](https://openjdk.org/)
- **Система сборки:** [Apache Maven 3.9.9](https://maven.apache.org/index.html)
- **Используемая библиотека:** [picocli 4.7.6](https://picocli.info/)
- **Добавить зависимость Maven:**
  ```
  <dependency>
            <groupId>info.picocli</groupId>
            <artifactId>picocli</artifactId>
            <version>4.7.6</version>
  </dependency>
  ```
## Инструкция по запуску
### Сборка jar-файла
1. [Скачать](https://github.com/bluzord/TestTaskCFT/archive/refs/tags/release.zip) исходный код проекта.
3. Разархивировать в любую удобную папку.
4. [Скачать](https://jdk.java.net/22/) и установить OpenJDK.
5. [Скачать](https://maven.apache.org/download.cgi) и установить Apache Maven.
6. Открыть папку проекта в консоли.
7. Выполнить команду ```mvn compile assembly:single```.
8. Будет создана папка target. Файл ```util.jar``` из этой папки можно перекинуть в любое удобное место и перейти к запуску.

### Запуск программы
1. [Скачать](https://github.com/bluzord/TestTaskCFT/releases/download/release/util.jar) готовый jar-файл или собрать его самостоятельно.
2. [Скачать](https://jdk.java.net/22/) и установить OpenJDK.
4. Открыть в консоли папку с jar-файлом.
5. Для запуска в консоли ввести команду `java -jar util.jar file1.txt file2.txt ...`
6. Доступны различные опции. Их описание приведено в таблице ниже:
   |Опция|Альтернативное написание|Связанная переменная|Описание|
   |-|-|-|-|
   |-h|--help|-|Показать доступные опции|
   |-a|--append|-|Режим добавления в файлы|
   |-f|--full|-|Отобразить полную статистику|
   |-s|--short|-|Отобразить краткую статистику|
   |-o|--output|path|Задать путь для выходных файлов|
   |-p|--prefix|prefix|Задать префикс для выходных файлов|
7. Пример запуска программы с опциями:
   ```
   java -jar util.jar -a -f -o C:/Program Files -p prefix- file1.txt file2.txt file3.txt
   ```

## Особенности реализации
### Библиотека picocli
Picocli - это библиотека для создания приложений с интерфейсом командной строки. Она была выбрана для лёгкого создания опций, приведённых в тестовом задании. Библиотека позволяет удобно реализовывать опции при помощи встроенных аннотаций без "ручного" перебора массива args.
### BigInteger и BigDecimal
При анализе требований и примеров, предоставленных в тестовом задании, было выявлено, что не стоит использовать стандартные типы *int* и *float*.  Например, число $1234567890123456789$ нельзя записать в переменную типа *int*, но можно записать в переменную типа *long*. Однако среди требований представлен подсчёт статистики (сумма чисел), и сумма нескольких чисел такого размера приведёт к переполнению даже у *long*.

В качестве решения был выбран класс **BigInteger** из пакета *java.math*, позволяющий представлять очень большие числа. По такой же логике был выбран класс **BigDecimal** для представления вещественных чисел.

Выбор этих классов также позволил написать лёгкую проверку типа данных входного значения, благодаря исключениям, выбрасываемым конструкторами этих классов.
