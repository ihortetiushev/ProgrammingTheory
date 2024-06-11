package org.example.service;

import org.example.model.RevenueExpenses;
import org.example.model.RevenueExpensesData;
import java.math.BigDecimal;

public abstract class FeeCalculator {

    public final void calculateFee(RevenueExpensesData data) {
        BigDecimal fee = BigDecimal.ZERO;
        for (RevenueExpenses revenueExpenses : data.getRevenueExpensesData()) {
            fee = fee.add(getFeeByCategory(revenueExpenses));
        }
        data.setTotalFee(fee);
    }

    abstract BigDecimal getFeeByCategory(RevenueExpenses revenueExpenses);
}
