import simulations.Simulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import inputfiles.Input;
import outputfiles.Output;

import java.io.File;

public final class Main {

    private Main() { }

    /**
     * The start of the program itself
     * @param args contains the input and output file paths
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        // the data from the json file is copied to the Input variabile
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);

        // the data needed is sorted in the simulation variabile
        Simulation simulation = Simulation.getInstance(input);
        // the simulation is run
        Output output = simulation.start();

        // the output data is put in the json out file
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);
    }
}
