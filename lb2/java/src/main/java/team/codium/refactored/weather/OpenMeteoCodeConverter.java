package team.codium.refactored.weather;

import java.util.Map;

import static java.util.Map.entry;

public class OpenMeteoCodeConverter {

    private static final Map<Integer, String> CODE_MAP = Map.ofEntries(
            entry(0, "lear sky"),
            entry(1, "Mainly clear, partly cloudy, and overcast"),
            entry(2, "Mainly clear, partly cloudy, and overcast"),
            entry(3, "Mainly clear, partly cloudy, and overcast"),
            entry(45, "Fog and depositing rime fog"),
            entry(48, "Fog and depositing rime fog"),
            entry(51, "Drizzle: Light, moderate, and dense intensity"),
            entry(53, "Drizzle: Light, moderate, and dense intensity"),
            entry(55, "Drizzle: Light, moderate, and dense intensity"),
            entry(56, "Freezing Drizzle: Light and dense intensity"),
            entry(57, "Freezing Drizzle: Light and dense intensity"),
            entry(61, "Rain: Slight, moderate and heavy intensity"),
            entry(63, "Rain: Slight, moderate and heavy intensity"),
            entry(65, "Rain: Slight, moderate and heavy intensity"),
            entry(66, "Freezing Rain: Light and heavy intensity"),
            entry(67, "Freezing Rain: Light and heavy intensity"),
            entry(71, "Snow fall: Slight, moderate, and heavy intensity"),
            entry(73, "Snow fall: Slight, moderate, and heavy intensity"),
            entry(75, "Snow fall: Slight, moderate, and heavy intensity"),
            entry(77, "Snow grains"),
            entry(80, "Rain showers: Slight, moderate, and violent"),
            entry(81, "Rain showers: Slight, moderate, and violent"),
            entry(82, "Rain showers: Slight, moderate, and violent"),
            entry(85, "Snow showers slight and heavy"),
            entry(86, "Snow showers slight and heavy"),
            entry(95, "Thunderstorm: Slight or moderate"),
            entry(96, "Thunderstorm with slight and heavy hail"),
            entry(99, "Thunderstorm with slight and heavy hail")
    );

    static String getDescriptionByCode(int weatherCode) {
        return CODE_MAP.get(weatherCode);
    }
}
