import org.example.model.FeeType;
import org.example.model.RevenueExpensesData;
import org.example.reader.DataReader;
import org.example.reader.FileDataReader;
import org.example.service.FeeCalculator;
import org.example.service.FeeCalculatorFactory;
import org.example.service.FixedFeeCalculator;
import org.example.service.StandardCalculator;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FeeCalculatorIntegrationTest {

    @Test
    public void getDataFromFilesCalculate_allDataIsCorrect_feesAreCalculated() {
        DataReader reader = new FileDataReader();
        Map<String, RevenueExpensesData> data = reader.getData();
        FeeCalculatorFactory feeFactory = new FeeCalculatorFactory();
        assertTrue(data.containsKey("Johnver"));
        assertTrue(data.containsKey("Vanston"));
        RevenueExpensesData sales1Data = data.get("Johnver");
        assertEquals(FeeType.Standard, sales1Data.getFeeType());
        RevenueExpensesData sales2Data = data.get("Vanston");
        assertEquals(FeeType.Fixed, sales2Data.getFeeType());
        FeeCalculator feeCalculator = feeFactory.createFeeCalculator(sales1Data.getFeeType());
        assertTrue(feeCalculator instanceof StandardCalculator);
        assertNull(sales1Data.getTotalFee());
        feeCalculator.calculateFee(sales1Data);
        assertEquals(0, BigDecimal.valueOf(62.248).compareTo(sales1Data.getTotalFee()));

        feeCalculator = feeFactory.createFeeCalculator(sales2Data.getFeeType());
        assertTrue(feeCalculator instanceof FixedFeeCalculator);
        assertNull(sales2Data.getTotalFee());
        feeCalculator.calculateFee(sales2Data);
        assertEquals(0, BigDecimal.valueOf(83.45).compareTo(sales2Data.getTotalFee()));
    }
}
