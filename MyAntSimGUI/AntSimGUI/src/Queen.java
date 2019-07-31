import java.util.Random;

public class Queen extends Ant
{
	int currDay;
	int ID;
	int prevID;
	boolean qnAlive;
	
	public Queen(ColonyNode node)
	{
		this.location = node;
		ID = 0;
		prevID = 0;
		qnAlive = true;
	}
	
	public Queen()
	{
		
	}
	
	public void nextDay(int currDay)
	{
		int gfd = location.colony.colGrid[13][13].getFood();
		this.location.colony.colGrid[13][13].setFood(gfd-1);
		if ((currDay == (73000)) || (location.getFood()) < 1)
		{
			Dead();
		}
			
		if (currDay % 10 == 0)
		{
			hatchAnt(null);	
		}		
	}
	
	private void Dead() 
	{
		try
		{
		qnAlive = false;
		System.out.println("Queen is dead!");
		this.location.nodeView.hideQueenIcon();
		this.location.colony.colDrive.endSim();
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}

	public void hatchAnt(Ant type)
	{
		Random random = new Random();
		int choice = random.nextInt(3);
		Ant nAnt;
		
		if (type != null)
		{
			nAnt = type;
		}
		else
		{
			if(choice == 0)
			{
				nAnt = new Scout(location);
			}
			else if(choice == 1)
			{
				nAnt = new Soldier(location);
			}
			else
			{
				nAnt = new Forager(location);
			}
		}
		prevID++;
		nAnt.setID(prevID);
		nAnt.setHatch(currDay);
		location.placeAnt(nAnt);
	}	
	
}