
public class GroundCell {
	Position p;
	
	Pheromone pheromone;
	FoodPile foodPile;
	Nest nest;
	
	public GroundCell(Position p)
	{
		this.p = p;
		pheromone = null;
		foodPile = null;
		nest = null;
	}
	
	public void setPheromone(Pheromone p)
	{
		this.pheromone = p;
	}
	
	public Pheromone getPheromone()
	{
		return pheromone;
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
