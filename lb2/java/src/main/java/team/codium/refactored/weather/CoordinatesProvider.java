package team.codium.refactored.weather;

public interface CoordinatesProvider {
    float getLatitude(String city);

    float getLongitude(String city);
}
