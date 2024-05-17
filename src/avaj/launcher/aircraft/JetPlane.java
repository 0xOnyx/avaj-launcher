package avaj.launcher.aircraft;

import avaj.launcher.tower.WeatherProvider;
import avaj.launcher.utils.Coordinates;
import avaj.launcher.utils.Simulation;

public class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public String toString() {
        return "JetPlane#" + this.name + "(" + this.id + ")";
    }

    public void updateConditions() {
        WeatherProvider.Weather weather = WeatherProvider.Weather.valueOf(
                this.weatherTower.getWeather(this.coordinates)
        );
        switch (weather) {
            case WeatherProvider.Weather.SUN:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 10,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 2
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": I'm flying in the sun.\n");
                break;
            case WeatherProvider.Weather.RAIN:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 5,
                        this.coordinates.getHeight()
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": I'm flying in the rain.\n");
                break;
            case WeatherProvider.Weather.FOG:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 1,
                        this.coordinates.getHeight()
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": I'm flying in the fog.\n");
                break;
            case WeatherProvider.Weather.SNOW:
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 7
                );
                Simulation.getInstance().getWriter().printf(this.toString() + ": I'm flying in the snow.\n");
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
