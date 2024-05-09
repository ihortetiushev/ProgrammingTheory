package team.codium.refactored.weather;

import java.time.LocalDate;

public interface Forecast {
    String predict(String city, LocalDate datetime, boolean wind);
}
