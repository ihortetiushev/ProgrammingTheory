package team.codium.refactored.weather;

import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;

public class OpenMeteoForecast implements ForecastProvider {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String REQUEST = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=weathercode,windspeed_10m_max&current_weather=true&timezone=Europe%%2FBerlin";
    private static final String WIND_FIELD = "windspeed_10m_max";
    private static final String WEATHER_CODE = "weathercode";
    private final RequestExecutor<JSONObject> requestExecutor;

    public OpenMeteoForecast(RequestExecutor<JSONObject> requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    @Override
    public String getWindSpeed(float latitude, float longitude, LocalDate datetime) {
        JSONObject data = getData(latitude, longitude);
        return String.valueOf(getField(datetime, data, WIND_FIELD, JSONArray::getFloat));
    }

    @Override
    public String getForecast(float latitude, float longitude, LocalDate datetime) {
        JSONObject data = getData(latitude, longitude);
        return OpenMeteoCodeConverter.getDescriptionByCode(getField(datetime, data, WEATHER_CODE, JSONArray::getInt));
    }

    private JSONObject getData(float latitude, float longitude) {
        return requestExecutor.executeGetRequest(String.format(REQUEST, latitude, longitude));
    }

    private <T> T getField(LocalDate datetime, JSONObject result, String field, BiFunction<JSONArray, Integer, T> method) {
        String requestDate = datetime.format(DATE_FORMAT);
        JSONObject results = result.getJSONObject("daily");
        JSONArray times = results.getJSONArray("time");
        for (int i = 0; i < times.length(); i++) {
            // When the date is the expected
            if (requestDate.equals(times.get(i))) {
                return method.apply(results.getJSONArray(field), i);
            }
        }
        return null;
    }
}
