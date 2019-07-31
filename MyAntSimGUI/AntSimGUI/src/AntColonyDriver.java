import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JOptionPane;
public class AntColonyDriver implements SimulationEventListener {

    AntColony antColSim;
    AntSimGUI gui;
    int currDay;
    boolean queenLives;
    boolean goTime;

    public AntColonyDriver(AntSimGUI gui)
    {
        queenLives = true;
        this.gui = gui;
        goTime = false;
        antColSim = new AntColony(new ColonyView(27, 27), this);
        gui.initGUI(antColSim.getView());
    }

    public void simulationEventOccurred(SimulationEvent simEvent) 
    {
        if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) 
        {
            antColSim.iColony();
        }
        else if (simEvent.getEventType() == SimulationEvent.RUN_EVENT) 
        {
        	Timer t = new Timer(40,this::timerMeth);
            goTime = true;
            t.start();
            
        }
        else if (simEvent.getEventType() == SimulationEvent.STEP_EVENT) 
        {
            goTime = false;
            nextDay();
        }
        else
        	System.out.println("Dont click that!");
    }
    
    public void nextDay() 
    {
    	if(!goTime && queenLives)
        {
        	antColSim.nextDay(currDay);
        	currDay++;
        }
    	else if (goTime && queenLives)
    	{
        while(goTime && queenLives)
        {	
            antColSim.nextDay(currDay);
            currDay++;
        }
    	}
    }
    
    private void timerMeth(ActionEvent e)
    {
    	if(goTime && queenLives)
        {	
            antColSim.nextDay(currDay);
            currDay++;
        }
    }

    public void endSim()
    {
        queenLives = false;
        JOptionPane.showMessageDialog(gui, "Simulation has ended. The Queen is Dead!");
        System.exit(0);
    }


}
