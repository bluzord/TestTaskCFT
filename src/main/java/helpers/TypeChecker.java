package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TypeChecker {
    public static TypeOfValue checkType(String value) {
        try {
            new BigDecimal(value);
            try {
                new BigInteger(value);
                return TypeOfValue.INTEGER;
            } catch (Exception e) {
                return TypeOfValue.FLOAT;
            }
        } catch (Exception e) {
            return TypeOfValue.STRING;
        }
    }
}
