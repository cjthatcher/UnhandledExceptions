import static org.junit.Assert.*;

import org.junit.Test;


public class testCase1 {
	
	@Test
	public void getAntPosition() {
		
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(20);
		config.setBoardWidth(20);
		config.setDegreeOfRandomness(0.8f);
		config.setMillisecondDelay(500);
		config.setNumberOfColonies(1);
		config.setNumberOfFoodPiles(1);
		config.setPheromoneStrength(30);
		config.setStartingAnts(1);
		
		Ground.setConfigurationClass(config);
		
		Ground testGround = Ground.getInstance();
		
		
		Ant testAnt = new Ant()
		
		
		fail("Not yet implemented");
	}

}
