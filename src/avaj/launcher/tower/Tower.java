package avaj.launcher.tower;

import avaj.launcher.aircraft.Flyable;
import avaj.launcher.utils.Simulation;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    private final List<Flyable> landing = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        flyable.registerTower((WeatherTower)this);
        Simulation.getInstance().getWriter()
            .printf("Tower says: %s registered to weather tower.\n", flyable.toString());
    }

    public void unregister(Flyable flyable) {
        landing.add(flyable);
        Simulation.getInstance().getWriter()
            .printf("Tower says: %s unregistered from weather tower.\n", flyable.toString());
    }

    protected void conditionChanged(){
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
        observers.remove(landing);

    }


}
