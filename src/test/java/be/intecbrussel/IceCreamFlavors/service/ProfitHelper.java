package be.intecbrussel.IceCreamFlavors.service;

import java.math.BigDecimal;

public class ProfitHelper {
    public static Object ROUND_HALF_UP;

    public ProfitHelper(double profit) {

    }

    public static double roundProfit(double profit) {
        BigDecimal bigDecimalValue = new BigDecimal(profit);
        BigDecimal roundedValue = bigDecimalValue.setScale(3, BigDecimal.ROUND_HALF_UP);
        return roundedValue.doubleValue();
    }
    public ProfitHelper equals(int i, Object roundHalfUp) {

        return null;
    }
    public double doubleValue() {
        return 0;
    }
}
