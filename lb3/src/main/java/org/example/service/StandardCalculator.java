package org.example.service;

import org.example.model.RevenueExpenses;
import java.math.BigDecimal;

public class StandardCalculator extends FeeCalculator {
    private static final BigDecimal FEE = BigDecimal.valueOf(0.062);

    @Override
    BigDecimal getFeeByCategory(RevenueExpenses revenueExpenses) {
        BigDecimal diff = revenueExpenses.getRevenue().subtract(revenueExpenses.getExpenses());
        if (diff.compareTo(BigDecimal.ZERO) > 0) {
            return diff.multiply(FEE);
        } else {
            return BigDecimal.ZERO;
        }
    }
}
