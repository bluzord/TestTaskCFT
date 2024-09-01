package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TypeChecker {

    public static String checkType(String value) {
        try {
            new BigDecimal(value);
            try {
                new BigInteger(value);
                return "integer";
            } catch (Exception e) {
                return "float";
            }
        } catch (Exception e) {
            return "string";
        }
    }
}
