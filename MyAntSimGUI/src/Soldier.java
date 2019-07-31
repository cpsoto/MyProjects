import java.util.*;


public class Soldier extends Ant
{

	public Soldier(ColonyNode location) 
	{
		this.location = location;
	}

	public Soldier() {
	}
	
	public void nextDay(int currDay)
	{
		if((currDay - hatchDate) > 3650)
		{
			setDead();
			return;
		}
		
		if(location.countAntByClass(new Bala()) > 0)
		{
			attackMode();
		}
		else
		{
			scoutMode();
		}
	}
	
	public void attackMode()
	{
		ArrayList<Ant> balaAnts = location.getBalaAnts(location);
		if(balaAnts.isEmpty())
		{
			return;
		}
		else
		{
			Random hit = new Random();
			int kill = hit.nextInt(2);
			if (kill == 1)
			{
				balaAnts.get(0).setDead();
			}
		}
	}
	
	public void scoutMode()
	{
		ArrayList<ColonyNode> surroundingNodes = new ArrayList<ColonyNode>();
		surroundingNodes = location.surveyNodes();
		ColonyNode moveTo;
        Random randoMove = new Random();
        ArrayList<ColonyNode> visNodes = new ArrayList<ColonyNode>();
		for(ColonyNode visN : surroundingNodes)
		{
			if(visN.isVisible)
			{
				visNodes.add(visN);
			}
		}
        moveTo = visNodes.get(randoMove.nextInt(visNodes.size()));
        
        for(ColonyNode node : visNodes)
        {
        	if(node.countAntByClass(new Bala()) > 0)
        	{
        		moveTo = node;
        	}
        }
        
        location.removeAnt(this);
        location = moveTo;
        location.setVisible(true);
        location.placeAnt(this);
	}
}