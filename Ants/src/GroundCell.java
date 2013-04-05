import java.util.ArrayList;
import java.util.List;


public class GroundCell {
	Position p;
	
	List<Pheromone> pheromoneList = new ArrayList<Pheromone>();
	FoodPile foodPile;
	Nest nest;
	List<Ant> antList = new ArrayList<Ant>();
	
	public GroundCell(Position p)
	{
		this.p = p;
		pheromoneList.clear();
		foodPile = null;
		nest = null;
	}
	
	public void loseAnt(Ant a)
	{
		if (antList.contains(a))
		{
			antList.remove(a);
		}
	}
	
	public void addAnt(Ant a)
	{
		if (!antList.contains(a))
		{
			antList.add(a);
		}
	}
	
	public void addPheromone(Pheromone p)
	{
		Colony tempColony = p.getColony();
		
		for (Pheromone phero : pheromoneList)
		{
			if (phero.getColony() == tempColony)
			{
				pheromoneList.remove(phero);
			}
		}
		
		pheromoneList.add(p);
	}
	
	public List<Pheromone> getPheromone()
	{
		return pheromoneList;
	}
	
	public Pheromone getColonyPheromone(Colony c)
	{
		for (Pheromone p : pheromoneList)
		{
			if (p.getColony() == c)
				return p;
		}
		
		return null;
	}
	
	public void setFoodPile(FoodPile fp)
	{
		this.foodPile = fp;
	}
	
	public FoodPile getFoodPile()
	{
		return foodPile;
	}
	
	public void setNest(Nest n)
	{
		this.nest = n;
	}
	
	public Nest getNest()
	{
		return nest;
	}
	
	
}
