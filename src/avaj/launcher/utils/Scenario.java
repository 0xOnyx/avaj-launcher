package avaj.launcher.utils;

import avaj.launcher.aircraft.Flyable;
import avaj.launcher.exceptions.CoordinatesException;
import avaj.launcher.exceptions.FileException;
import avaj.launcher.exceptions.IncorrectParamsAircraftException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scenario implements AutoCloseable {
    private Scanner scanner = null;
    public Scenario(String p_fileName) throws FileException {
        try{
            scanner = new Scanner(new File(p_fileName));
        }
        catch (Exception e) {
            throw new FileException(e.getMessage() + " : " + p_fileName);
        }
    }

    public int getSimulationCycles() {
        if (scanner.hasNextInt()) {
            int cycles = scanner.nextInt();
            if (cycles > 0)
                return cycles;
        }
        return -1;
    }

    public List<Flyable> getFlyable() throws CoordinatesException, IncorrectParamsAircraftException {
        List<Flyable> flyables = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty())
                continue;
            String[] parts = line.split(" ");
            if (parts.length != 5)
                continue;
            int longitude = Integer.parseInt(parts[2]), latitude = Integer.parseInt(parts[3]), height = Integer.parseInt(parts[4]);

            Coordinates coordinates = new Coordinates(longitude, latitude, height);
            coordinates.normalizeCoordinates();
            coordinates.checkCoordinates();
            Flyable flyable = AircraftFactory.newAircraft(parts[0], parts[1], coordinates);
            flyables.add(flyable);
        }
        return flyables;
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
