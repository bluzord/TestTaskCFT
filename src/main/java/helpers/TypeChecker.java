package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TypeChecker {

    public static TypeOfString checkType(String value) {
        try {
            new BigDecimal(value);
            try {
                new BigInteger(value);
                return TypeOfString.INTEGER;
            } catch (Exception e) {
                return TypeOfString.FLOAT;
            }
        } catch (Exception e) {
            return TypeOfString.STRING;
        }
    }
}
