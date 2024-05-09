package team.codium.legacytraining.weather;

import org.junit.jupiter.api.Test;
import team.codium.refactored.weather.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherKataTest {
    // https://positionstack.com/geo_api.php?query=Barcelona
    // https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=weathercode,windspeed_10m_max&timezone=Europe%2FBerlin

    @Test
    public void getWind_oldAndNewAreSame() throws IOException {
        Forecast oldForecast = new Forecast();
        String city = "Kharkiv";
        LocalDate requestTime = LocalDate.now();
        String oldWind = oldForecast.predict(city, requestTime, true);

        RequestExecutor requestExecutor = new RequestExecutor(new JsonConverter());
        CoordinatesProvider coordinatesProvider = new PositionStackCoordinates(requestExecutor);
        ForecastProvider forecastProvider = new OpenMeteoForecast(requestExecutor);

        ForecastRefactored newForecast = new ForecastRefactored(coordinatesProvider, forecastProvider);
        String newWind = newForecast.predict(city, requestTime, true);
        assertEquals(oldWind, newWind);
    }


    @Test
    public void getWeatherForecast_oldAndNewAreSame() throws IOException {
        Forecast oldForecast = new Forecast();
        String city = "Kharkiv";
        LocalDate requestTime = LocalDate.now();
        String oldValue = oldForecast.predict(city, requestTime, false);

        RequestExecutor requestExecutor = new RequestExecutor(new JsonConverter());
        CoordinatesProvider coordinatesProvider = new PositionStackCoordinates(requestExecutor);
        ForecastProvider forecastProvider = new OpenMeteoForecast(requestExecutor);

        ForecastRefactored newForecast = new ForecastRefactored(coordinatesProvider, forecastProvider);
        String newValue = newForecast.predict(city, requestTime, false);
        assertEquals(oldValue, newValue);
    }

}
