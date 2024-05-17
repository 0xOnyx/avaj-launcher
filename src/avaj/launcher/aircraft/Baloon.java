package avaj.launcher.aircraft;

import avaj.launcher.tower.WeatherProvider;
import avaj.launcher.utils.Coordinates;
import avaj.launcher.utils.Simulation;

public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public String toString() {
        return "Baloon#" + this.name + "(" + this.id + ")";
    }

    public void updateConditions() {
        WeatherProvider.Weather weather = WeatherProvider.Weather.valueOf(
                this.weatherTower.getWeather(this.coordinates)
        );
        switch (weather) {
            case WeatherProvider.Weather.SUN:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 2,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 4
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": Let's enjoy the good weather and take some pics.\n");
                break;
            case WeatherProvider.Weather.RAIN:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 5
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": Damn you rain! You messed up my baloon.\n");
                break;
            case WeatherProvider.Weather.FOG:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 3
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": I can't see anything, it's so foggy.\n");
                break;
            case WeatherProvider.Weather.SNOW:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 15
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": It's snowing. We're gonna crash.\n");
                break;
            default:
                break;
        }

        coordinates.normalizeCoordinates();
        if (this.coordinates.getHeight() <= 0) {
            Simulation.getInstance().getWriter().printf(this.toString() + " landing.\n");
            this.weatherTower.unregister(this);
        }
    }
}
