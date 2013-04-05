
public class Ground {
	int width;
	int height;
	GroundCell[][] cellArray;
	ConfigurationClass config;
	
	public Ground(ConfigurationClass config)
	{
		this.width = config.getBoardWidth();
		this.height = config.getBoardHeight();
		
		cellArray = new GroundCell[height][width];
	}

	
	
}
