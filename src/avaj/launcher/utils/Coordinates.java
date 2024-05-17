package avaj.launcher.utils;

import avaj.launcher.exceptions.CoordinatesException;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int p_longitude, int p_latitude, int p_height){
        longitude = p_longitude;
        latitude = p_latitude;
        height = p_height;
    }

    public void checkCoordinates() throws CoordinatesException {
        if (longitude < 0)
            throw new CoordinatesException("Invalid longitude is negative");
        if (latitude < 0)
            throw new CoordinatesException("Invalid latitude is negative");
        if (height < 0)
            throw new CoordinatesException("Invalid height is negative");
        if (height > 100)
            throw new CoordinatesException("Invalid height is more than 100");
    }

    public void normalizeCoordinates() {
        if (height > 100)
            height = 100;
        else if (height < 0)
            height = 0;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
