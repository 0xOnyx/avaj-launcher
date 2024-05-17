package avaj.launcher.utils;

import avaj.launcher.exceptions.FileException;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Simulation {

    private PrintWriter writer;
    private static Simulation INSTANCE = null;
    public static final String FILE_NAME = "simulation.txt";

    private Simulation(){
    }

    public static Simulation getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Simulation();
        return INSTANCE;
    }

    public void init() throws FileException {
        try {
            this.writer = new PrintWriter(new FileWriter(FILE_NAME));
        } catch (Exception e) {
            throw new FileException(e.getMessage() + " : " + FILE_NAME);
        }
    }

    public void uninit() {
        this.INSTANCE = null;
        this.writer.close();
    }

    public PrintWriter getWriter() {
        return this.writer;
    }
}
