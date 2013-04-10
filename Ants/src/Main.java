
public class Main {
	
	public static void main(String args[])
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(40);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(40);
		config.setBoardWidth(40);
		config.setDegreeOfRandomness(0.8f);
		config.setMillisecondDelay(500);
		config.setNumberOfColonies(4);
		config.setNumberOfFoodPiles(12);
		config.setPheromoneStrength(30);
		config.setStartingAnts(3);
		
		Ground.setConfigurationClass(config);
		
		ViewFrame vf = new ViewFrame();
		
		
		
	}
}
