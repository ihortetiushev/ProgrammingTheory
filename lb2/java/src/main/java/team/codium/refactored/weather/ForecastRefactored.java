package team.codium.refactored.weather;
import java.time.LocalDate;

public class ForecastRefactored implements Forecast {

    private final CoordinatesProvider coordinatesProvider;
    private final ForecastProvider forecastProvider;

    public ForecastRefactored(CoordinatesProvider coordinatesProvider, ForecastProvider forecastProvider) {
        this.coordinatesProvider = coordinatesProvider;
        this.forecastProvider = forecastProvider;
    }

    @Override
    public String predict(String city, LocalDate datetime, boolean wind) {
        if (datetime == null) {
            datetime = LocalDate.now();
        }

        if (datetime.isBefore(LocalDate.now().plusDays(7))) {
            float latitude = coordinatesProvider.getLatitude(city);
            float longitude = coordinatesProvider.getLongitude(city);

            if (wind) {
                return forecastProvider.getWindSpeed(latitude, longitude, datetime);
            } else {
                return forecastProvider.getForecast(latitude, longitude, datetime);
            }
        } else {
            return "";
        }
    }
}
