package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StatsService {
    private List<String> strings = new ArrayList<>();
    private List<BigInteger> integers = new ArrayList<>();
    private List<BigDecimal> floats = new ArrayList<>();


    //Методы для целочисленных
    private BigInteger getMaxInteger() {
        BigInteger maxValue = integers.getFirst();
        for (BigInteger i : integers) {
            maxValue = maxValue.max(i);
        }
        return maxValue;
    }

    private BigInteger getMinInteger() {
        BigInteger minValue = integers.getFirst();
        for (BigInteger i : integers) {
            minValue = minValue.min(i);
        }
        return minValue;
    }

    private BigInteger getIntegerSum() {
        BigInteger sum = new BigInteger("0");
        for (BigInteger i : integers) {
            sum = sum.add(i);
        }
        return sum;
    }

    private BigDecimal getIntegerAverage() {
        BigDecimal sum = new BigDecimal(getIntegerSum());
        return BigDecimal.valueOf(sum.doubleValue()/integers.size());
    }

    //Методы для вещественных
    private BigDecimal getMaxFloat() {
        BigDecimal maxValue = floats.getFirst();
        for (BigDecimal f : floats) {
            maxValue = maxValue.max(f);
        }
        return maxValue;
    }

    private BigDecimal getMinFloat() {
        BigDecimal minValue = floats.getFirst();
        for (BigDecimal f : floats) {
            minValue = minValue.min(f);
        }
        return minValue;
    }

    private BigDecimal getFloatSum() {
        BigDecimal sum = new BigDecimal("0");
        for (BigDecimal f : floats) {
            sum = sum.add(f);
        }
        return sum;
    }

    private BigDecimal getFloatAverage() {
        return BigDecimal.valueOf(getFloatSum().doubleValue()/floats.size());
    }

    //Методы для строк
    private int getMaxStringLength() {
        int maxLength = strings.getFirst().length();
        for (String s : strings) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    private int getMinStringLength() {
        int minLength = strings.getFirst().length();
        for (String s : strings) {
            if (s.length() < minLength) {
                minLength = s.length();
            }
        }
        return minLength;
    }

    public void printIntegerShortStats() {
        if (!integers.isEmpty()) {
            System.out.println("---Integers:---");
            System.out.println("Count: " + integers.size());
        }
    }

    public void printIntegerFullStats() {
        if (!integers.isEmpty()) {
            printIntegerShortStats();
            System.out.println("Min: " + getMinInteger());
            System.out.println("Max: " + getMaxInteger());
            System.out.println("Sum: " + getIntegerSum());
            System.out.println("Average: " + getIntegerAverage());
        }
    }

    public void printFloatShortStats() {
        if (!floats.isEmpty()) {
            System.out.println("---Floats:---");
            System.out.println("Count: " + floats.size());
        }
    }

    public void printFloatFullStats() {
        if (!floats.isEmpty()) {
            printFloatShortStats();
            System.out.println("Min: " + getMinFloat());
            System.out.println("Max: " + getMaxFloat());
            System.out.println("Sum: " + getFloatSum());
            System.out.println("Average: " + getFloatAverage());
        }
    }

    public void printStringShortStats() {
        if (!strings.isEmpty()) {
            System.out.println("---Strings:---");
            System.out.println("Count: " + strings.size());
        }
    }

    public void printStringFullStats() {
        if (!strings.isEmpty()) {
            printStringShortStats();
            System.out.println("Min Length: " + getMinStringLength());
            System.out.println("Max Length: " + getMaxStringLength());
        }
    }

    public List<String> getStrings() {
        return strings;
    }

    public List<BigInteger> getIntegers() {
        return integers;
    }

    public List<BigDecimal> getFloats() {
        return floats;
    }
}
