package org.example.service;

import org.example.model.RevenueExpenses;

import java.math.BigDecimal;

public class FixedFeeCalculator extends FeeCalculator {
    private static final BigDecimal FEE = BigDecimal.valueOf(0.05);

    @Override
    BigDecimal getFeeByCategory(RevenueExpenses revenueExpenses) {
        BigDecimal diff = revenueExpenses.getRevenue().subtract(revenueExpenses.getExpenses()).abs();
        return diff.multiply(FEE);
    }
}
