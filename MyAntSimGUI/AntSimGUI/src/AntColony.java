import java.util.*;

public class AntColony
{
	ColonyNode[][] colGrid;
	ColonyNodeView nodeView;
	ColonyNode colNode;
	ColonyView colView;
	AntColonyDriver colDrive;
	Queen qn;
	
	public AntColony(ColonyView colV, AntColonyDriver colD)
	{
		colGrid = new ColonyNode[27][27];
		colView = colV;
		colDrive = colD;
	}
	
	
	public void nextDay(int currDay)
	{
		try
		{
		int i, j;
		for(i = 0;i < 27; i++)
		{
			for(j = 0; j < 27; j++)
			{
				colGrid[i][j].nextDay(currDay);
			}
		}
		hatchBala(currDay);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void hatchBala(int currDay)
	{
		Random odds = new Random();
		int balaProb = odds.nextInt(100);
		int entProb = odds.nextInt(2);
		int a, b;
		a = 0;
		b = 1;
		ColonyNode balaEntrance;
		if(entProb == 0)
		{
			a = 25;
			b = 26;
		}
		balaEntrance = colGrid[a][b];
		if(balaProb <= 29)
		{
			balaEntrance.setVisible(true);
			Bala nBala = new Bala(balaEntrance);
			int id = qn.prevID++;
			nBala.setID(id);
			nBala.setHatch(currDay);
			balaEntrance.placeAnt(nBala);
		}
	}
	
	public void iColony()
	{
		int i, j;
		for(i = 0; i < 27; i++)
		{
			for(j = 0; j < 27; j++)
			{
				if(i == 13 && j == 13)
				{
					qNode(i,j);
				}
				else
				{
					rNode(i, j);
				}
			}
		}
	}
	
	public void qNode(int a, int b)
	{
		nodeView = new ColonyNodeView();
		colNode = new ColonyNode(nodeView, a, b);
		colView.addColonyNodeView(nodeView, a, b);
		colNode.setColony(this);
		qn = new Queen(colNode);
		nodeView.setQueen(true);
		colNode.setFood(1000);
		colNode.placeAnt(qn);
		colNode.setQnNode(true);
		colNode.setVisible(true);
		addNode(colNode, a, b);
		
		for(int c = 0; c < 50; c++)
		{
			Forager forager = new Forager(colNode);
			qn.hatchAnt(forager);
		}
		for(int d = 0; d < 10; d++)
		{
			Soldier soldier = new Soldier(colNode);
			qn.hatchAnt(soldier);
		}
		for(int e = 0; e < 4; e++)
		{
			Scout scout = new Scout(colNode);
			qn.hatchAnt(scout);
		}
	}
	
	public void rNode(int m, int n)
	{
		nodeView = new ColonyNodeView();
		colNode = new ColonyNode(nodeView, m, n);
		colView.addColonyNodeView(nodeView, m, n);
		colNode.setColony(this);
		colNode.setVisible(false);
		addNode(colNode, m, n);
		
		if((m >= 12 && m <=14) && (n >= 12 && n <= 14))
		{
			colGrid[m][n].setVisible(true);
		}
	}
	
	public ColonyView getView()
	{
		return colView;
	}
	
	public void addNode (ColonyNode newNode, int m, int n)
	{
		colGrid[m][n] = newNode;
	}
	
	public ArrayList<ColonyNode> surveyNodes(ColonyNode node) 
    {
		ArrayList<ColonyNode> surroundingNodes = new ArrayList<ColonyNode>();
		ArrayList<ColonyNode> nodesList = new ArrayList<ColonyNode>();
    	
    	int x = node.getXcoord();
    	int y = node.getYcoord();
    	try {
    	if (x - 1 >= 0 && y + 1 >= 0 && x - 1 <= 26 && y + 1 <= 26)
    	{
      		surroundingNodes.add(colGrid[x-1][y+1]);
    	}
    	//top
    	if (x >= 0 && y + 1 >= 0 && x <= 26 && y + 1 <= 26)
    	{
    		surroundingNodes.add(colGrid[x][y+1]);
    	}
    	//upper right
    	if (x + 1 >= 0 && y + 1 >= 0 && x + 1 <= 26 && y + 1 <= 26)
    	{
    		surroundingNodes.add(colGrid[x+1][y+1]);
    	}
    	//left
    	if (x - 1 >= 0 && y >= 0 && x - 1 <= 26 && y <= 26)
    	{
    		surroundingNodes.add(colGrid[x-1][y]);
    	}
    	//right
    	if (x + 1 >= 0 && y >= 0 && x + 1 <= 26 && y <= 26)
    	{
    		surroundingNodes.add(colGrid[x+1][y]);
    	}
    	//bottom left
    	if (x - 1 >= 0 && y - 1 >= 0 && x - 1 <= 26 && y - 1 <= 26)
    	{
    		surroundingNodes.add(colGrid[x-1][y-1]);
    	}
    	//bottom
    	if (x >= 0 && y - 1 >= 0 && x <= 26 && y - 1 <= 26)
    	{
    		surroundingNodes.add(colGrid[x][y-1]);
    	}
    	//bottom right
    	if (x + 1 >= 0 && y - 1 >=0 && x + 1 <= 26 && y - 1 <= 26)
    	{
    		surroundingNodes.add(colGrid[x+1][y-1]);
    	}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	for(ColonyNode aN : surroundingNodes)
    	{
    		if(aN != null)
    		{
    			nodesList.add(aN);
    		}
    	}
    	return nodesList;
    }
	
}