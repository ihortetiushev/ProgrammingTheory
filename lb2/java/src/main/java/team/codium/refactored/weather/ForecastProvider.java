package team.codium.refactored.weather;

import java.time.LocalDate;

public interface ForecastProvider {
    String getWindSpeed(float latitude, float longitude, LocalDate datetime);

    String getForecast(float latitude, float longitude, LocalDate datetime);
}
