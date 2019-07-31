import java.util.*;

public class Scout extends Ant
{

	public Scout(ColonyNode location) 
	{
		this.location = location;
	}

	public Scout() 
	{
		
	}
	
	public void nextDay(int currDay)
	{
		if((currDay - hatchDate) > 3650)
		{
			setDead();
			return;
		}
		else
		{
			move();
		}
	}
	
	public void move()
	{
		ArrayList<ColonyNode> surroundingNodes = new ArrayList<ColonyNode>();
		surroundingNodes = location.surveyNodes();
		ColonyNode moveTo;
        Random randoMove = new Random();
        moveTo = surroundingNodes.get(randoMove.nextInt(surroundingNodes.size()));
        location.removeAnt(this);
        location = moveTo;
        location.setVisible(true);
        location.placeAnt(this);
	}
	
}