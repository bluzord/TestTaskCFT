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
    public BigInteger getMaxInteger() {
        BigInteger maxValue = integers.getFirst();
        for (BigInteger i : integers) {
            maxValue = maxValue.max(i);
        }
        return maxValue;
    }

    public BigInteger getMinInteger() {
        BigInteger minValue = integers.getFirst();
        for (BigInteger i : integers) {
            minValue = minValue.min(i);
        }
        return minValue;
    }

    public BigInteger getIntegerSum() {
        BigInteger sum = new BigInteger("0");
        for (BigInteger i : integers) {
            sum = sum.add(i);
        }
        return sum;
    }

    public BigDecimal getIntegerAverage() {
        BigDecimal sum = new BigDecimal(getIntegerSum());
        return BigDecimal.valueOf(sum.doubleValue()/integers.size());
    }

    //Методы для вещественных
    public BigDecimal getMaxFloat() {
        BigDecimal maxValue = floats.getFirst();
        for (BigDecimal f : floats) {
            maxValue = maxValue.max(f);
        }
        return maxValue;
    }

    public BigDecimal getMinFloat() {
        BigDecimal minValue = floats.getFirst();
        for (BigDecimal f : floats) {
            minValue = minValue.min(f);
        }
        return minValue;
    }

    public BigDecimal getFloatSum() {
        BigDecimal sum = new BigDecimal("0");
        for (BigDecimal f : floats) {
            sum = sum.add(f);
        }
        return sum;
    }

    public BigDecimal getFloatAverage() {
        return BigDecimal.valueOf(getFloatSum().doubleValue()/floats.size());
    }

    //Методы для строк
    public int getMaxStringLength() {
        int maxLength = strings.getFirst().length();
        for (String s : strings) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    public int getMinStringLength() {
        int minLength = strings.getFirst().length();
        for (String s : strings) {
            if (s.length() < minLength) {
                minLength = s.length();
            }
        }
        return minLength;
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
