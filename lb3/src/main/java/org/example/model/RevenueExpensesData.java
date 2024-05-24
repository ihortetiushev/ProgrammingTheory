package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RevenueExpensesData {
    private List<RevenueExpenses> revenueExpensesData = new ArrayList<>();
    private FeeType feeType;
    private BigDecimal totalFee;

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public void addRevenueExpenses(RevenueExpenses revenueExpenses) {
        if (!revenueExpensesData.stream().filter(e -> e.getCategory() == revenueExpenses.getCategory()).findFirst().isEmpty()) {
            throw new IllegalArgumentException("already contains such category");
        }
        revenueExpensesData.add(revenueExpenses);
    }

    public List<RevenueExpenses> getRevenueExpensesData() {
        return revenueExpensesData;
    }
}
