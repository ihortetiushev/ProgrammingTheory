package org.example.reader;

import org.example.model.RevenueExpensesData;
import java.util.Map;

public interface DataReader {
    Map<String, RevenueExpensesData> getData();
}
