import java.util.*;

public class ColonyNode
{

    int x, y;
    ColonyNode [][] colGrid;
    ColonyView colView;
    ArrayList<ColonyNode> adjacentNodes;
    boolean isVisible;
    ArrayList<Ant> antList;
    ArrayList<Ant> antsToRemove;
    ArrayList<Ant> antsToAdd;
    int food;
    int pheromone;
    boolean isQnNode;
    ColonyNodeView nodeView;
    boolean searchingList;
    AntColony colony;
    
    ColonyNode (int a, int b)
    {
    	
    	this.x=a;
    	this.y=b;
    }

    ColonyNode(ColonyNodeView nodeV, int x, int y)
    {
        antList = new ArrayList<Ant>();
        antsToRemove = new ArrayList<Ant>();
        antsToAdd = new ArrayList<Ant>();
        nodeView = nodeV;
        nodeView.setForagerCount(0);
        nodeView.setBalaCount(0);
        nodeView.setSoldierCount(0);
        nodeView.setScoutCount(0);
        Random randomFood = new Random();
        if (randomFood.nextInt(3) == 0) 
        {
            food = randomFood.nextInt((500) + 500);
        } 
        else
        {
        	food = 0;
        }
        nodeView.setFoodAmount(food);
        this.x = x;
        this.y = y;
        pheromone = 0;
        nodeView.setPheromoneLevel(pheromone);
    }

    public void setFood(int foodCount) 
    {
        if(foodCount < 0)
        {
        	food = 0;
        }
        else if (foodCount > 1000)
        {
        	food = 1000;
        }
        else
        	food = foodCount;
    }

    public int getFood() 
    {
        return food;
    }

    public void setPheromone(int phero) 
    {
        if(phero < 0)
        {
        	pheromone = 0;
        }
        else if (phero > 1000)
        {
        	pheromone = 1000;
        }
        else
        	pheromone = phero;
    }

    public int getPheromone()
    {
        return pheromone;
    }

    public int getXcoord() 
    {
        return x;
    }

    public int getYcoord() 
    {
        return y;
    }
    
    public void setQnNode(boolean isQnNode)
    {
    	this.isQnNode = isQnNode;
    	if(isQnNode == true)
    	{
    		nodeView.showQueenIcon();
    	}
    }
    
    public boolean isQnNode() 
    {
        return isQnNode;
    }

    public void setColony(AntColony col) 
    {
        colony = col;
    }
    
    public void placeAnt(Ant nAnt) 
    {
        if (searchingList == true) 
        {
            antsToAdd.add(nAnt);
        }
        else 
        {
            antList.add(nAnt);
        }
        updateNodeView();
    }

    public void removeAnt(Ant nAnt) 
    {
        if (searchingList == true)
        {
            antsToRemove.add(nAnt);
        }
        else 
        {
            antList.remove(nAnt);
        }
        updateNodeView();
    }

    public void setVisible(boolean vis) 
    {
        if (vis == true) 
        {
            nodeView.showNode();
            isVisible = vis;
        }
        if (vis == false) 
        {
            nodeView.hideNode();
        }
    }
    
    public boolean getVisiblity()
    {
    	return isVisible;
    }

    public int countAntByClass(Ant antClass)
    {
    	int count = 0;
    	for(int i = 0; i < antList.size(); i++)
    	{
    		if(antList.get(i).getClass() == ( antClass.getClass()))
    		{
    			count++;		
    		}
    	}
    	return count;
    }

    public void updateNodeView() 
    {	
    	this.nodeView.setFoodAmount(food);
        this.nodeView.setPheromoneLevel(pheromone);       
        if (countAntByClass(new Queen()) > 0) 
        {
            nodeView.showQueenIcon();
        }

        nodeView.setForagerCount(countAntByClass(new Forager()));
        if (countAntByClass(new Forager()) > 0)
        {
            nodeView.showForagerIcon();
        }
        else
        {
        	nodeView.hideForagerIcon();
        }
        
        nodeView.setScoutCount(countAntByClass(new Scout()));
        if (countAntByClass(new Scout()) > 0)
        {
            nodeView.showScoutIcon();
        }
        else 
        {
        	nodeView.hideScoutIcon();
        }
        
        nodeView.setSoldierCount(countAntByClass(new Soldier()));
        if (countAntByClass(new Soldier()) > 0)
        {
            nodeView.showSoldierIcon();
        }
        else
        {
        	nodeView.hideSoldierIcon();
        }

        nodeView.setBalaCount(countAntByClass(new Bala()));
        if (countAntByClass(new Bala()) > 0)
        {
            nodeView.showBalaIcon();
        }
        else
        {
        	nodeView.hideBalaIcon();
        }
    }

	public void updateList() 
	{	
		antList.removeAll(antsToRemove);
		antList.addAll(antsToAdd);
        antsToRemove.clear();
        antsToAdd.clear(); 
    }

    public void nextDay(int currDay)
    { 
    	if (currDay % 10 == 0)
        {
    		this.pheromone = pheromone/2;
        }
        searchingList = true;
        for (Ant ants : antList) 
        {
            ants.nextDay(currDay);
        }
        searchingList = false;
        updateList();
        updateNodeView();
    }

     
    public ArrayList<Ant> getBalaAnts(ColonyNode loc)
    {
    	ArrayList<Ant> balaAnts = new ArrayList<Ant>();
    	for (int i = 0; i < loc.antList.size(); i++) 
        {
            if (loc.antList.get(i).getClass() == new Bala().getClass())
            {
                balaAnts.add(loc.antList.get(i));
            }
        }
        return balaAnts;
    }

    
    
    public ArrayList<Ant> getfriendAnts(ColonyNode node) 
    {
        ArrayList<Ant> friendAnts = new ArrayList<Ant>();
        for (int i = 0; i <node.antList.size(); i++) 
        {
        	
            if (node.antList.get(i).getClass() != new Bala().getClass())
            {
                friendAnts.add(node.antList.get(i));
            }
        }
        return friendAnts;
    }
    
    public ArrayList<ColonyNode> surveyNodes() 
    {
    	return colony.surveyNodes(this);
    }
    
	
	public ColonyView getView()
	{
		return colView;
	}
	
	
}
