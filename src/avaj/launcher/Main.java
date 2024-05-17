package avaj.launcher;

import avaj.launcher.exceptions.ArguementException;
import avaj.launcher.exceptions.CoordinatesException;
import avaj.launcher.exceptions.FileException;
import avaj.launcher.exceptions.IncorrectParamsAircraftException;
import avaj.launcher.tower.WeatherTower;
import avaj.launcher.utils.Scenario;
import avaj.launcher.utils.Simulation;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            if (args.length == 0) {
                System.out.println("Please provide a scenario file.");
                throw new ArguementException("Please provide a scenario file.");
            }

            Simulation.getInstance().init();
            try (Scenario scenario = new Scenario(args[0])) {

                WeatherTower weatherTower = new WeatherTower();

                int simulationCycle = scenario.getSimulationCycles();
                scenario.getFlyable().stream().forEach(weatherTower::register);

                for (int i = 0; i < simulationCycle; i++) {
                    weatherTower.changeWeather();
                }
            }
            Simulation.getInstance().uninit();
        } catch (ArguementException | CoordinatesException | FileException | IncorrectParamsAircraftException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
