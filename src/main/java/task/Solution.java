package task;

import helpers.StatsService;
import helpers.TypeChecker;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solution implements Callable<Integer> {

    private final StatsService statsService = new StatsService();
    private String integerOutput = "integers.txt";
    private String stringOutput = "strings.txt";
    private String floatsOutput = "floats.txt";
    private List<BufferedReader> readers = new ArrayList<>();

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Список опций.")
    Boolean help;

    @Option(names = {"-o", "--output"}, description = "Путь для выходных файлов.")
    String path = "";

    @Option(names = {"-p", "--prefix"}, description = "Префикс для выходных файлов.")
    String prefix = "";

    @Option(names = {"-s", "--short"}, description = "Отображение краткой статистики.")
    Boolean isShortStats = false;

    @Option(names = {"-f", "--full"}, description = "Отображение полной статистики.")
    Boolean isFullStats = false;

    @Option(names = {"-a", "--append"}, description = "Режим добавления в существующие файлы.")
    Boolean isCurrentFiles = false;

    @Parameters(description = "Входные файлы.")
    List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Solution()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (!path.isEmpty()) {
            path += "/";
        }
        integerOutput = path + prefix + integerOutput;
        stringOutput = path  + prefix + stringOutput;
        floatsOutput = path  + prefix + floatsOutput;

        try {
            //TODO: создание файла, проверка txt
        } catch (Exception e) {

        }

        for (String fileName : fileNames) {
            readers.add(new BufferedReader(new FileReader(fileName)));
        }

        boolean hasLines;
        do {
            hasLines = false;
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line != null) {
                    String type = TypeChecker.checkType(line);
                    switch (type) {
                        case "integer" -> statsService.getIntegers().add(new BigInteger(line));
                        case "float" -> statsService.getFloats().add(new BigDecimal(line));
                        case "string" -> statsService.getStrings().add(line);
                    }
                    hasLines = true;
                }
            }
        } while (hasLines);

        for (BufferedReader reader : readers) {
            reader.close();
        }


        if (isShortStats) {
            //TODO: печать о нуле элементов
            statsService.printIntegerShortStats();
            statsService.printFloatShortStats();
            statsService.printStringShortStats();
        }

        if (isFullStats) {
            //TODO: печать о нуле элементов
            statsService.printIntegerFullStats();
            statsService.printFloatFullStats();
            statsService.printStringFullStats();
        }

        FileWriter writer;
        if (!statsService.getIntegers().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(integerOutput, isCurrentFiles);
            for (BigInteger i : statsService.getIntegers()) {
                writer.write(i + "\n");
            }
            writer.close();
        }

        if (!statsService.getFloats().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(floatsOutput, isCurrentFiles);
            for (BigDecimal f : statsService.getFloats()) {
                writer.write(f + "\n");
            }
            writer.close();
        }

        if (!statsService.getStrings().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(stringOutput, isCurrentFiles);
            for (String s : statsService.getStrings()) {
                writer.write(s + "\n");
            }
            writer.close();
        }



        return 0;
    }
}