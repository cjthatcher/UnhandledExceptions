
public class Main {
	
	public static void main(String args[])
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(40);
		config.setBoardWidth(40);
		config.setDegreeOfRandomness(0.5f);
		config.setMillisecondDelay(50);
		config.setNumberOfColonies(4);
		config.setNumberOfFoodPiles(25);
		config.setPheromoneStrength(50);
		config.setStartingAnts(5);
		
		Ground.setConfigurationClass(config);
		AntTimer timer=new AntTimer();
		ViewFrame vf = new ViewFrame();
		
	
	}
}
