package avaj.launcher.aircraft;

import avaj.launcher.utils.Coordinates;

public class Aircraft extends Flyable{
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected static long idCounter = 1;

    public static long nextId() {
        return idCounter++;
    }

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    public void updateConditions() {

    }
}
