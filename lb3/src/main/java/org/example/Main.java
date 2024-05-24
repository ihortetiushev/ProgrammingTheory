package org.example;

import org.example.model.RevenueExpensesData;
import org.example.reader.DataReader;
import org.example.reader.FileDataReader;
import org.example.service.FeeCalculator;
import org.example.service.FeeCalculatorFactory;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DataReader reader = new FileDataReader();
        Map<String, RevenueExpensesData> data = reader.getData();
        FeeCalculatorFactory feeFactory = new FeeCalculatorFactory();
        for (Map.Entry<String, RevenueExpensesData> entry: data.entrySet()){
            FeeCalculator feeCalculator = feeFactory.createFeeCalculator(entry.getValue().getFeeType());
            feeCalculator.calculateFee(entry.getValue());
            System.out.println(entry.getKey() + ": " + entry.getValue().getFeeType());
            System.out.println(entry.getValue().getTotalFee());
        }
    }
}
