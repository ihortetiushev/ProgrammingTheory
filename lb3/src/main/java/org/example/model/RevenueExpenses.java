package org.example.model;
import java.math.BigDecimal;

public class RevenueExpenses {
    private final BigDecimal revenue;
    private final BigDecimal expenses;
    private final Category category;

    public RevenueExpenses(Category category, BigDecimal revenue, BigDecimal expenses) {
        this.category = category;
        this.revenue = revenue;
        this.expenses = expenses;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public Category getCategory() {
        return category;
    }

    public static class RevenueExpensesBuilder {
        private BigDecimal revenue = BigDecimal.ZERO;
        private BigDecimal expenses = BigDecimal.ZERO;
        private Category category;

        public RevenueExpensesBuilder withRevenue(BigDecimal amount) {
            this.revenue = amount;
            return this;
        }

        public RevenueExpensesBuilder withExpenses(BigDecimal amount) {
            this.expenses = amount;
            return this;
        }

        public RevenueExpensesBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public RevenueExpenses build() {
            if (category == null) {
                throw new IllegalStateException("category must not be null");
            }
            return new RevenueExpenses(category, revenue, expenses);
        }
    }
}


