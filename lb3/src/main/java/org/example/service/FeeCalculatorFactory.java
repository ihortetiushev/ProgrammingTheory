package org.example.service;

import org.example.model.FeeType;

public class FeeCalculatorFactory {
    public FeeCalculator createFeeCalculator(FeeType feeType) {
        return switch (feeType) {
            case Fixed -> new FixedFeeCalculator();
            case Standard -> new StandardCalculator();
        };
    }
}
