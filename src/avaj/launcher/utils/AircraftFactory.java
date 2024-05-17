package avaj.launcher.utils;

import avaj.launcher.aircraft.*;
import avaj.launcher.exceptions.IncorrectParamsAircraftException;

public class AircraftFactory {

    public enum AircraftType {
        Helicopter,
        JetPlane,
        Baloon
    }
    static public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates)
            throws IncorrectParamsAircraftException {
        if (p_type == null || p_name == null || p_coordinates == null)
            throw new IncorrectParamsAircraftException("Invalid argument");
        if (p_coordinates.getHeight() < 0 || p_coordinates.getLongitude() < 0 || p_coordinates.getLatitude() < 0)
            throw new IncorrectParamsAircraftException("Invalid coordinates are negative");
        if (p_coordinates.getHeight() > 100)
            throw new IncorrectParamsAircraftException("Invalid height is more than 100");

        switch (AircraftType.valueOf(p_type)){
            case Helicopter:
                return new Helicopter(Aircraft.nextId(), p_name, p_coordinates);
            case JetPlane:
                return new JetPlane(Aircraft.nextId(), p_name, p_coordinates);
            case Baloon:
                return new Baloon(Aircraft.nextId(), p_name, p_coordinates);
            default:
                throw new IncorrectParamsAircraftException("Invalid aircraft type");
        }
    }
}
