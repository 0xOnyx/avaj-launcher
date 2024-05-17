package avaj.launcher.tower;

import avaj.launcher.utils.Coordinates;

public class WeatherProvider {
    private static final WeatherProvider INSTANCE = new WeatherProvider();
    public enum Weather {
        RAIN, FOG, SUN, SNOW
    }

    private WeatherProvider() { }

    public static WeatherProvider getInstance() {
        return INSTANCE;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int sum = p_coordinates.getLatitude() + p_coordinates.getLongitude() + p_coordinates.getHeight();
        return Weather.values()[sum % 4].toString();
    }
}
