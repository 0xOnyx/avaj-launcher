package avaj.launcher.tower;

import avaj.launcher.utils.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinate){
        return WeatherProvider.getInstance().getCurrentWeather(coordinate);
    }

    public void changeWeather(){
        this.conditionChanged();
    }
}
