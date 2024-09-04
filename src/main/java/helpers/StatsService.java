package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
        return sum.divide(new BigDecimal(integers.size(), MathContext.DECIMAL128));
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
        return getFloatSum().divide(new BigDecimal(floats.size()), MathContext.DECIMAL128);
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
            System.out.println("Количество: " + integers.size());
        }
    }

    public void printIntegerFullStats() {
        if (!integers.isEmpty()) {
            printIntegerShortStats();
            System.out.println("Минимум: " + getMinInteger());
            System.out.println("Максимум: " + getMaxInteger());
            System.out.println("Сумма: " + getIntegerSum());
            System.out.println("Среднее: " + getIntegerAverage());
        }
    }

    public void printFloatShortStats() {
        if (!floats.isEmpty()) {
            System.out.println("---Floats:---");
            System.out.println("Количество: " + floats.size());
        }
    }

    public void printFloatFullStats() {
        if (!floats.isEmpty()) {
            printFloatShortStats();
            System.out.println("Минимум: " + getMinFloat());
            System.out.println("Максимум: " + getMaxFloat());
            System.out.println("Сумма: " + getFloatSum());
            System.out.println("Среднее: " + getFloatAverage());
        }
    }

    public void printStringShortStats() {
        if (!strings.isEmpty()) {
            System.out.println("---Strings:---");
            System.out.println("Количество: " + strings.size());
        }
    }

    public void printStringFullStats() {
        if (!strings.isEmpty()) {
            printStringShortStats();
            System.out.println("Минимальная длина: " + getMinStringLength());
            System.out.println("Максимальная длина: " + getMaxStringLength());
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
