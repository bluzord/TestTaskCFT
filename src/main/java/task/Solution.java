package task;

import helpers.StatsService;
import helpers.TypeChecker;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solution implements Callable<Integer> {

    private final StatsService statsService = new StatsService();

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

    @Option(names = "-a", description = "Режим добавления в существующие файлы.")
    Boolean isCurrentFiles = false;

    @Parameters(description = "Входные файлы.")
    List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Solution()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}