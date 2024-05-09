package team.codium.refactored.weather;

import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PositionStackCoordinates implements CoordinatesProvider {

    private static final String request = "https://positionstack.com/geo_api.php?query=%s";
    private final RequestExecutor<JSONObject> requestExecutor;

    private Map<String, JSONObject> result = new HashMap<>();

    public PositionStackCoordinates(RequestExecutor<JSONObject> requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    @Override
    public float getLatitude(String city) {
        return getResult(city).getJSONArray("data").getJSONObject(0).getFloat("latitude");
    }

    @Override
    public float getLongitude(String city) {
        return getResult(city).getJSONArray("data").getJSONObject(0).getFloat("longitude");
    }

    private JSONObject getResult(String city) {
        JSONObject jsonObject = result.get(city);
        if (jsonObject == null) {
            jsonObject = getCoordinates(city);
            result.put(city, jsonObject);
        }
        return jsonObject;
    }

    private JSONObject getCoordinates(String city) {
        return requestExecutor.executeGetRequest(String.format(request, city));
    }
}
