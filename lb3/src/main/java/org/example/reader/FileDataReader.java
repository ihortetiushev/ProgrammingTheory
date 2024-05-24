package org.example.reader;

import org.apache.commons.io.FilenameUtils;
import org.example.model.Category;
import org.example.model.FeeType;
import org.example.model.RevenueExpenses;
import org.example.model.RevenueExpensesData;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataReader implements DataReader {
    private static final String DIR = "src/main/resources";
    public static final String REVENUE = "Revenue:";
    public static final String EXPENSES = "Expenses:";

    @Override
    public Map<String, RevenueExpensesData> getData() {
        Map<String, RevenueExpensesData> salesData = new HashMap<>();
        Set<String> files = listFiles(DIR);
        for (String fileName : files) {
            List<String> strings = readFile(DIR + "/" + fileName);
            RevenueExpensesData data = new RevenueExpensesData();
            data.setFeeType(FeeType.valueOf(strings.get(0)));
            for (int i = 1; i < strings.size(); i++) {
                String line = strings.get(i);
                String[] revStr = line.split(" ");
                RevenueExpenses.RevenueExpensesBuilder builder = new RevenueExpenses.RevenueExpensesBuilder();
                builder.withCategory(Category.valueOf(revStr[0].trim()));
                String[] revAmountLine = line.substring(line.indexOf(REVENUE) + REVENUE.length() + 1).split(" ");
                builder.withRevenue(new BigDecimal(revAmountLine[1]));
                String[] expensesAmountLine = line.substring(line.indexOf(EXPENSES) + EXPENSES.length() + 1).split(" ");
                builder.withExpenses(new BigDecimal(expensesAmountLine[1]));
                data.addRevenueExpenses(builder.build());
            }
            salesData.put(FilenameUtils.removeExtension(fileName), data);
        }
        return salesData;
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<String> listFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
