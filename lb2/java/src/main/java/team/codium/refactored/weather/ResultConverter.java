package team.codium.refactored.weather;

public interface ResultConverter<T> {
    T convert(String result);
}
