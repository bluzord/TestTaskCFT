package task;

import helpers.StatsService;
import helpers.TypeChecker;
import helpers.TypeOfString;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
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
            //TODO: создание файла, проверка txt, ends with
        } catch (Exception e) {

        }

        for (String fileName : fileNames) {
            //TODO: создавать от файла
            readers.add(new BufferedReader(new FileReader(fileName)));
        }

        boolean hasLines;
        do {
            hasLines = false;
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line != null) {
                    TypeOfString type = TypeChecker.checkType(line);
                    switch (type) {
                        case TypeOfString.INTEGER -> statsService.getIntegers().add(new BigInteger(line));
                        case TypeOfString.FLOAT -> statsService.getFloats().add(new BigDecimal(line));
                        case TypeOfString.STRING -> statsService.getStrings().add(line);
                    }
                    hasLines = true;
                }
            }
        } while (hasLines);

        for (BufferedReader reader : readers) {
            reader.close();
        }

        if (isShortStats) {
            statsService.printIntegerShortStats();
            statsService.printFloatShortStats();
            statsService.printStringShortStats();
        }

        if (isFullStats) {
            statsService.printIntegerFullStats();
            statsService.printFloatFullStats();
            statsService.printStringFullStats();
        }

        FileWriter writer;
        if (!statsService.getIntegers().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(integerOutput, isCurrentFiles);
            for (BigInteger val : statsService.getIntegers()) {
                writer.write(val + "\n");
            }
            writer.close();
        }

        if (!statsService.getFloats().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(floatsOutput, isCurrentFiles);
            for (BigDecimal val : statsService.getFloats()) {
                writer.write(val + "\n");
            }
            writer.close();
        }

        if (!statsService.getStrings().isEmpty()) {
            //TODO: проверка директории, предложение создать в текущем файле
            writer = new FileWriter(stringOutput, isCurrentFiles);
            for (String val : statsService.getStrings()) {
                writer.write(val + "\n");
            }
            writer.close();
        }



        return 0;
    }
}