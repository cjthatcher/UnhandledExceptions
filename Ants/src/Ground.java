import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ground {
	int width;
	int height;
	GroundCell[][] cellArray;
	ConfigurationClass config;
	static Ground instance;
	
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
		
		cellArray = new GroundCell[height][width];

		
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
		Arrays.sort(pherList);
		if(pherList.size() >= 2){
			if(hasFood){
				Pheromone strongest = pherList.get(1);
				return pherMap.get(strongest);
			}else{
				Pheromone weakest = pherList.get(pherList.size() - 1);
				return pherMap.get(weakest);
			}
		}else if(pherList.size() == 1){
			if(hasFood){
				
			}else{

			}
		}
	}

	private Pheromone checkForPheromone(int x, int y, Colony col){
		if(){
			return null;
		}else{

		}
	}
}

