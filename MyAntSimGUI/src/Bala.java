import java.util.*;

public class Bala extends  Ant
{
  Bala(ColonyNode node)
  {
    location = node;
  }

  Bala()
  {

  }

  public void nextDay(int currDay)
  {
	  try {
    if((currDay - hatchDate) > 3650)
    {
      setDead();
      return;
    }
    
	ArrayList<Ant> friendAnt = this.location.getfriendAnts(this.location);
    if (!friendAnt.isEmpty()) 
    {
    	fight(friendAnt);
    }
    else
    {
        ColonyNode moveTo;
        Random randoMove = new Random();
        ArrayList<ColonyNode> surroundingNodes = new ArrayList<ColonyNode>();
        surroundingNodes = location.surveyNodes();
        moveTo = surroundingNodes.get(randoMove.nextInt(surroundingNodes.size()));
        location.removeAnt(this);
        location = moveTo;
        location.setVisible(true);
        location.placeAnt(this);
    }
	  }
	  catch (ArrayIndexOutOfBoundsException e)
	  {
		  e.printStackTrace();
	  }
  }

  public void fight(ArrayList<Ant> ant)
  {
    Random kill = new Random();
    int odds = kill.nextInt(2);
    if (odds == 1)
    {
      Ant dAnt = ant.get(kill.nextInt(ant.size()));     
      if (dAnt instanceof Queen)
      {
    	  location.colony.colDrive.endSim();
      }
      dAnt.setDead();
    }
  }
}
