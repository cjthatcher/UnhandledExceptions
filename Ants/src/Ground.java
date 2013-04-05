
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

	
	
}
