
public class Main {
	
	public static void main(String args[])
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(4);
		config.setBoardWidth(4);
		config.setDegreeOfRandomness(0.8f);
		config.setMillisecondDelay(500);
		config.setNumberOfColonies(1);
		config.setNumberOfFoodPiles(1);
		config.setPheromoneStrength(30);
		config.setStartingAnts(1);
		
		Ground.setConfigurationClass(config);
		
		ViewFrame vf = new ViewFrame();
		
		
		
	}
}
