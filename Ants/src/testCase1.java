import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class testCase1 {
	
	public ConfigurationClass getConfigClass()
	{
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
		
		return config;
	}
	
	@Test
	public void instantiateGround() {
		
		Ground.setConfigurationClass(getConfigClass());
		
		Ground testGround = Ground.getInstance();
		
		assertNotNull(testGround);
	}
	
	@Test
	public void verifyAntCreation() {
		ConfigurationClass config = getConfigClass();
		
		Ground.setConfigurationClass(config);
		
		Ground testGround = Ground.getInstance();
		
		int predictedNumberOfAnts = config.getStartingAnts() * config.getNumberOfColonies();
		
		int x = config.getBoardWidth();
		int y = config.getBoardHeight();
		
		GroundCell[][] gcArray = testGround.getCellArray();
		
		int realNumberOfAnts = 0;
		
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				if (gcArray[i][j].getAnt() != null)
				{
					List<Ant> tempAntList = gcArray[i][j].getAnt();
					
					if (tempAntList.size() > 0)
					{
						realNumberOfAnts += tempAntList.size();
					}
				}
			}
		}
		
		assertEquals(realNumberOfAnts, predictedNumberOfAnts);
		
	}

}
