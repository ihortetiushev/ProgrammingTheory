package team.codium.refactored.weather;

import org.json.JSONObject;

public class JsonConverter implements ResultConverter<JSONObject> {
    @Override
    public JSONObject convert(String rawResponse) {
        return new JSONObject(rawResponse);
    }
}
