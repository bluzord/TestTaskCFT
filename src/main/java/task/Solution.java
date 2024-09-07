package task;

import helpers.StatsService;
import helpers.TypeChecker;
import helpers.TypeOfValue;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solution implements Callable<Integer> {

    private final StatsService statsService = new StatsService();
    private String integerOutput = "integers.txt";
    private String stringOutput = "strings.txt";
    private String floatsOutput = "floats.txt";
    private final List<BufferedReader> readers = new ArrayList<>();

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Available options.")
    Boolean help;

    @Option(names = {"-o", "--output"}, description = "Set path for output files.")
    String path = "";

    @Option(names = {"-p", "--prefix"}, description = "Set prefix for output files.")
    String prefix = "";

    @Option(names = {"-s", "--short"}, description = "Show short statistics.")
    Boolean isShortStats = false;

    @Option(names = {"-f", "--full"}, description = "Show full statistics.")
    Boolean isFullStats = false;

    @Option(names = {"-a", "--append"}, description = "Append to files.")
    Boolean isAppend = false;

    @Parameters(description = "Input files.")
    List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Solution()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        for (String fileName : fileNames) {
            try {
                File file = new File(fileName);

                if (!file.exists()) {
                    throw new FileNotFoundException("\"" + fileName + "\"" + " not found. It will not be processed.");
                }

                if (!file.isFile()) {
                    throw new FileNotFoundException("\"" + fileName + "\"" + " is not a file. It will not be processed.");
                }

                if (!fileName.endsWith(".txt")) {
                    throw new FileNotFoundException("\"" + fileName + "\"" + " is not a txt file. It will not be processed.");
                }

                readers.add(new BufferedReader(new FileReader(file)));

            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        boolean hasLines;
        do {
            hasLines = false;
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line != null) {
                    TypeOfValue type = TypeChecker.checkType(line);
                    switch (type) {
                        case INTEGER -> statsService.getIntegers().add(new BigInteger(line));
                        case FLOAT -> statsService.getFloats().add(new BigDecimal(line));
                        case STRING -> statsService.getStrings().add(line);
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

        if (!prefix.isEmpty()) {
            try  {
                Paths.get(prefix + integerOutput);
                integerOutput = prefix + integerOutput;
                floatsOutput = prefix + floatsOutput;
                stringOutput = prefix + stringOutput;
            } catch (InvalidPathException e) {
                System.err.println("Prefix " + "\"" + prefix + "\"" + " is invalid. Files will be created without prefix.");
            }
        }

        //обработка директории
        if (!path.isEmpty()) {
            File directory = null;
            try {
                Paths.get(path);
                directory = new File(path);
                if (!directory.isDirectory()) {
                    throw new NotDirectoryException(path);
                }

                if (!directory.exists()) {
                    throw new FileNotFoundException("\"" + path + "\"" + "does not exist. The folder will be created automatically.");
                }

            } catch (InvalidPathException | NotDirectoryException e) {
                System.err.println("Path " + "\"" + path + "\"" + " is invalid. Files will be created in the current folder.");
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                if (directory.mkdir()) {
                    path += "/";
                    integerOutput = path + integerOutput;
                    floatsOutput = path + floatsOutput;
                    stringOutput = path + stringOutput;
                }
                else {
                    System.err.println("Folder not created. Files will be created in the current folder.");
                }
            }

        }

        FileWriter writer;
        if (!statsService.getIntegers().isEmpty()) {
            writer = new FileWriter(integerOutput, isAppend);
            for (BigInteger val : statsService.getIntegers()) {
                writer.write(val + "\n");
            }
            writer.close();
        }

        if (!statsService.getFloats().isEmpty()) {
            writer = new FileWriter(floatsOutput, isAppend);
            for (BigDecimal val : statsService.getFloats()) {
                writer.write(val + "\n");
            }
            writer.close();
        }

        if (!statsService.getStrings().isEmpty()) {
            writer = new FileWriter(stringOutput, isAppend);
            for (String val : statsService.getStrings()) {
                writer.write(val + "\n");
            }
            writer.close();
        }

        return 0;
    }
}