import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

public class Ground {
	int width;
	int height;
	GroundCell[][] cellArray;
	ConfigurationClass config;
	static Ground instance;
	Timer time;
	int delay=1000; //this is in milliseconds
	
	public static Ground getInstance()
	{
		return instance;
	}
	
	public void CreateGround(ConfigurationClass config)
	{
		instance = new Ground(config);
	}
	
	private Ground(ConfigurationClass config)
	{
		this.width = config.getBoardWidth();
		this.height = config.getBoardHeight();
		
		int numColonies = config.getNumberOfColonies();
		int numFood = config.getNumberOfFoodPiles();
		
		List<Position> colonyPositions = new ArrayList<Position>();
		List<Position> foodPositions = new ArrayList<Position>();
		
		
		while(colonyPositions.size() < numColonies)
		{
			int x = (int)(Math.random() * config.getBoardWidth());
			int y = (int)(Math.random() * config.getBoardHeight());
			
			Position position = new Position(x, y);
			
			boolean inList = false;
			
			for(int i=0; i<colonyPositions.size(); ++i)
			{
				Position temp = colonyPositions.get(i);
				if( temp.x == position.x && temp.y == position.y)
				{
					inList = true;
				}
			}
			
			if(inList == false)
			{
				colonyPositions.add(position);
			}
			
		}
		
		while(foodPositions.size() < numFood)
		{
			int x = (int)(Math.random() * config.getBoardWidth());
			int y = (int)(Math.random() * config.getBoardHeight());
			
			Position position = new Position(x, y);
			
			if(!foodPositions.contains(position))
			{
				foodPositions.add(position);
			}
			
		}
		
		cellArray = new GroundCell[height][width];

		time=new Timer(delay, new timeListener());
	}

	public Position findStrongestPheromone(Colony col, GroundCell gc, boolean hasFood){
		int orig_x = gc.getPosition().getX();
		int orig_y = gc.getPosition().getY();
		List<Pheromone> pherList = new ArrayList<Pheromone>();
		Map<Pheromone,Position> pherMap = new HashMap<Pheromone, Position>();

		for(int i = -1; i <= 1; ++i){
			int temp_x = orig_x + i;
			if(temp_x < 0 || temp_x >= width)
				continue;
			else{
				for(int j = -1; j <= 1; ++j){
					int temp_y = orig_y + j;
					if(temp_y < 0 || temp_y >= height){
						continue;
					}else if(temp_x == orig_x && temp_y == orig_y){
						continue;
					}else{
						Pheromone temp_p = checkForPheromone(temp_x, temp_y, col);
						if(temp_p != null){
							pherList.add(temp_p);
							pherMap.put(temp_p, new Position(temp_x,temp_y));
						}
					}
				}
			}
		}
		
		//Make a comparator here.
		Collections.sort(pherList, new PheromoneComparator());
		if(pherList.size() >= 2){
			if(hasFood){
				Pheromone strongest = pherList.get(1);
				return pherMap.get(strongest);
			}else{
				Pheromone weakest = pherList.get(pherList.size() - 1);
				return pherMap.get(weakest);
			}
		}else if(pherList.size() == 1){
			//If he doesnt' have food, follow it.
			
			//if he does have food --->
			
			//Make a list of all the places the ant has been since it has had food
			//Make sure it doesn't step on those places again (avoid cycles)
			//If he's out of viable options, just send him straight home. Mercy, right?
			
			//if pheromoneList.size() == 0, 
			
			//if has food --> 80% towards home
			//if no food --> random as can be.
			
			if(hasFood){
			//No pheromones? Take an 80% chance towards home
			}else{

			}
		}
	}

	private Pheromone checkForPheromone(int x, int y, Colony col){
		return cellArray[x][y].getColonyPheromone(col);
	}
	
	private class timeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for(int i=0;i<cellArray.length;++i)
			{
				for(int j=0;j<cellArray[i].length;++j)
				{
					for(Pheromone p:cellArray[i][j].getPheromone())
					{
						p.agePheromone();
					}
					for(Ant a:cellArray[i][j].getAnt())
					{
						a.moveDirection();
					}
				}
			}
		}
	}
}

