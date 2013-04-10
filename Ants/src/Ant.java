import java.util.ArrayList;
import java.util.List;


public class Ant {

	private GroundCell gc;
	private Colony col;
	private Direction dir;
	private  boolean food;
	private List<Position> visited;

	public Ant(Colony col, GroundCell gc){
		this.gc = gc;
		this.col = col;
		visited=new ArrayList<Position>();
		gc.addAnt(this);
	}
	
	public List<Position> getVisited()
	{
		return visited;
	}
	
	public GroundCell getLocation() {
		return gc;
	}

	public void setLocation(GroundCell gc) {
		this.gc = gc;
	}

	public Colony getCol() {
		return col;
	}
	public void setCol(Colony col) {
		this.col = col;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public boolean isCarryingFood(){
		if (this.food == true){
			return true;
		}
		return false;
	}

	public void moveDirection(){
		if(food)
		{
			visited.add(gc.getPosition());
			gc.addPheromone(new Pheromone(col, Ground.getInstance().getConfig().getPheromoneStrength(), gc));
		}
		
		Ground ground = Ground.getInstance();
		
		Position nextPosition = ground.findStrongestPheromone(col, gc, food, this);
		
		gc.loseAnt(this);
		
		gc = ground.cellArray[nextPosition.getX()][nextPosition.getY()];
		gc.addAnt(this);
		if(gc.getFoodPile()!=null&&(gc.getFoodPile().getFoodAmount()>0&&!food))
		{
			food=true;
			gc.getFoodPile().decrementFood();
		}
		
		System.out.println("Ant moved - new position = " + nextPosition.getY() + ", " + nextPosition.getX());
	}
}