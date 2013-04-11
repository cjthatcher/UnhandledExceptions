package Controller;
import Model.Ground;
import View.ViewFrame;


public class Main {
	
	public static void main(String args[])
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(20);
		config.setBoardWidth(20);
		config.setDegreeOfRandomness(0.5f);
		config.setMillisecondDelay(50);
		config.setNumberOfColonies(4);
		config.setNumberOfFoodPiles(10);
		config.setPheromoneStrength(35);
		config.setStartingAnts(2);
		
		Ground.setConfigurationClass(config);
		AntTimer timer=new AntTimer();
		ViewFrame vf = new ViewFrame();
		
	
	}
}
