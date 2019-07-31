public abstract class  Ant
{
  int AntID;

  int hatchDate;

  ColonyNode location;

  public Ant (ColonyNode node)
  {
    this.AntID = 0;


    this.location = node;
  }

  public Ant()
  {

  }

  public void setID(int ID)
  {
    AntID = ID;
  }

  public void setHatch (int currD)
  {
    hatchDate = currD;
  }

  public void setDead()
  {
    location.removeAnt(this);
  }

  public void nextDay(int currDay)
  {
	  
  }
  
  public void move (ColonyNode node)
  {
    location.removeAnt(this);
    location = node;
    location.placeAnt(this);
  }


}
