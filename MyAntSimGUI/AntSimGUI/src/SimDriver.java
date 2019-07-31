public class SimDriver
{
    public static void main(String [] args)
    {
        AntSimGUI gui = new AntSimGUI();
        AntColonyDriver antSim = new AntColonyDriver(gui);
        gui.addSimulationEventListener(antSim);
    }
}
