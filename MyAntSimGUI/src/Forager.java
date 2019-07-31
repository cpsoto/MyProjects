import java.util.*;

public class Forager extends Ant {
	Stack<ColonyNode> returnPath;
	boolean foodFound;
	int x, y;
	ColonyNode colNode;

	public Forager(ColonyNode node) {
		location = node;
		returnPath = new Stack<ColonyNode>();
		foodFound = false;
		x=node.getXcoord();
		y=node.getYcoord();
		returnPath.push(location);
	}

	public Forager() {
	}

	public void nextDay(int currDay) 
	{
		if ((currDay - hatchDate) > 3650) {
			setDead();
		}
		if (!foodFound) {
			findPherm();
		}
		if (foodFound && location.isQnNode()) {
			dropFood();
			foodFound = false;
			returnPath.clear();
			returnPath.push(this.location);
		} 
		else if (foodFound && !this.location.isQnNode())
		{
			dropPherm();
			ColonyNode gotoQn = returnPath.pop();
			location.removeAnt(this);
			location = gotoQn;
			location.placeAnt(this);
		}
	}

	private void dropFood() 
	{
		int i = location.getXcoord();
		int j = location.getYcoord();
		int gfd = location.colony.colGrid[i][j].getFood();
		this.location.colony.colGrid[i][j].setFood(gfd + 1);
	}

	private void dropPherm() 
	{
		int m = location.getXcoord();
		int n = location.getYcoord();
		int gph = location.colony.colGrid[m][n].getPheromone();
		this.location.colony.colGrid[m][n].setPheromone(gph + 10);
	}

	public void findPherm() {
		ArrayList<ColonyNode> surroundingNodes = new ArrayList<ColonyNode>();
		surroundingNodes = location.surveyNodes();
		ColonyNode pherNode = surroundingNodes.get(0);
		ArrayList<ColonyNode> pherList = new ArrayList<ColonyNode>();
		ArrayList<ColonyNode> visNodes = new ArrayList<ColonyNode>();
		for (ColonyNode visN : surroundingNodes) {
			if (visN.isVisible == true) {
				visNodes.add(visN);
			}
		}
		for (ColonyNode n : visNodes) 
		{
			if (n.getPheromone() > pherNode.getPheromone()) {
				pherNode = n;
			} 
			else if (n.getPheromone() == pherNode.getPheromone()) {
				pherList.add(n);
			}
		}

		if (!pherList.isEmpty()) 
		{
			for (ColonyNode no : pherList) 
			{
				if (no.getPheromone() > pherNode.getPheromone()) 
				{
					pherNode = no;
				}
			}
		}	
				if(returnPath.peek() != null)
				{
					for(int i = returnPath.peek().getXcoord() - 1; i <= returnPath.peek().getXcoord() + 1; i++)
					{
						for(int j = returnPath.peek().getYcoord() - 1; j <= returnPath.peek().getYcoord() + 1; j++)
						{
							if(pherNode.getXcoord() == i || pherNode.getYcoord() == j)
							{
								Random rando = new Random();
								pherNode = visNodes.get(rando.nextInt(visNodes.size()));
							}
						}
					}
					
				}
		location.removeAnt(this);
		location = pherNode;
		location.placeAnt(this);
		returnPath.push(location);
		if (location.getFood() > 0 && !location.isQnNode())
		{
			pickupFood();
			foodFound = true;
		}
	}

	private void pickupFood() {
		int a = location.getXcoord();
		int b = location.getYcoord();
		int gfd = location.colony.colGrid[a][b].getFood();
		this.location.colony.colGrid[a][b].setFood(gfd-1);
	}
}