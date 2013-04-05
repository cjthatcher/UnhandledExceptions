
public class GroundCell {
	Position p;
	
	Pheromone pheromone;
	FoodPile foodPile;
	Nest nest;
	
	public GroundCell(Position p)
	{
		this.p = p;
	}
	
	public void setPheromone(Pheromone p)
	{
		this.pheromone = p;
	}
}
