
public class Pheromone {
	Colony colony;
	int strength;
	GroundCell gc;
	
	public Pheromone(Colony c, int str, GroundCell gc)
	{
		this.colony = c;
		strength=str;
		this.gc=gc;
	}
	
	public Colony getColony()
	{
		return colony;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public GroundCell getLocation()
	{
		return gc;
	}
	
	public void agePheromone()
	{
		--strength;
		if(strength<=0)
		{
			gc.losePheromone(this);
		}
	}
}
