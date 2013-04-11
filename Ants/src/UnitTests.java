import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class UnitTests {
	
	public ConfigurationClass getConfigClass()
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(20);
		config.setBoardWidth(20);
		config.setDegreeOfRandomness(0.8f);
		config.setMillisecondDelay(500000);
		config.setNumberOfColonies(1);
		config.setNumberOfFoodPiles(1);
		config.setPheromoneStrength(30);
		config.setStartingAnts(1);

		
		return config;
	}
	
	/*
	 * This is a test of our ground's singleton pattern.
	 */
	@Test
	public void instantiateGround() {
		
		Ground.setConfigurationClass(getConfigClass());
		
		Ground testGround = Ground.getInstance();
		
		assertNotNull(testGround);
	}
	
	
	/*
	 * This test verifies that we have 
	 * 1) The correct number of ants initialized after board creation
	 * 
	 * It is a general test on board initialization.	
	 */
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
	
	
	/*
	 * This test verifies that we have 
	 * 1) The correct number of nests and colonies
	 * 
	 * It is a general test on board initialization.	
	 */
	@Test
	public void verifyNestCreation()
	{
		ConfigurationClass config = getConfigClass();
		
		Ground.setConfigurationClass(config);
		
		Ground testGround = Ground.getInstance();
		
		int predictedNumberOfNests = config.getNumberOfColonies();
		
		int x = config.getBoardWidth();
		int y = config.getBoardHeight();
		
		GroundCell[][] gcArray = testGround.getCellArray();
		
		int realNumberOfNests = 0;
		
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				if (gcArray[i][j].getNest() != null)
				{
					realNumberOfNests++;
				}
			}
		}
		
		assertEquals(realNumberOfNests, predictedNumberOfNests);
	}
	

/*
 * This test verifies that we have 
 * 1) The correct number of food piles
 * 2) The right amount of food on the board
 * 
 * It is a general test on board initialization.	
 */
	@Test
	public void verifyTotalAmountOfFood()
	{
		ConfigurationClass config = getConfigClass();
		
		Ground.setConfigurationClass(config);
		
		Ground testGround = Ground.getInstance();
		
		int predictedAmountOfFood = config.getNumberOfFoodPiles() * config.getAmountOfFoodPerPile();
		
		int x = config.getBoardWidth();
		int y = config.getBoardHeight();
		
		GroundCell[][] gcArray = testGround.getCellArray();
		
		int realAmountOfFood = 0;
		
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				if (gcArray[i][j].getFoodPile() != null)
				{
					realAmountOfFood += gcArray[i][j].getFoodPile().getFoodAmount();
				}
			}
		}
		
		assertEquals(realAmountOfFood, predictedAmountOfFood);
	}
	
	
	/*
	 * This test verifies that our getPheromone function returns correctly when there is no pheromone on the groundCell.
	 */
	@Test
	public void verifyPheromoneListNullRetrieval()
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setBoardHeight(3);
		config.setBoardWidth(3);
		config.setNumberOfColonies(1);
		config.setStartingAnts(0);
		config.setMillisecondDelay(5000);
		
		Ground.setConfigurationClass(config);
		
		Ground ground = Ground.getInstance();
		
		GroundCell[][] gcArray = ground.getCellArray();
		
		GroundCell gc = gcArray[1][1];
		
		List<Pheromone> pherList = gc.getPheromone();
		
		assertEquals(pherList.size(), 0);
	}
	

	/*
	 * This test verifies that our getPheromone function returns correctly when there are pheromones on the groundCell
	 */
	@Test
	public void verifyPheromoneListFullRetrieval()
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setBoardHeight(3);
		config.setBoardWidth(3);
		config.setNumberOfColonies(1);
		config.setStartingAnts(1);
		
		Ground.setConfigurationClass(config);
		
		Ground ground = Ground.getInstance();
		
		GroundCell[][] gcArray = ground.getCellArray();
		
		GroundCell gc = gcArray[0][0];
		
		gc.addPheromone(new Pheromone(new Colony(0, null), 50, gc));
		gc.addPheromone(new Pheromone(new Colony(1, null), 30, gc));
		
		List<Pheromone> pherList = gc.getPheromone();
		
		assertNotNull(pherList);
	}
	
	/*
	 * This test verifies that our PheromoneComparator class allows us to sort our pheromones in the list correctly
	 */
	@Test
	public void verifyPheromoneListSort()
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setBoardHeight(3);
		config.setBoardWidth(3);
		config.setNumberOfColonies(1);
		config.setStartingAnts(1);
		
		Ground.setConfigurationClass(config);
		
		Ground ground = Ground.getInstance();
		
		GroundCell[][] gcArray = ground.getCellArray();
		
		GroundCell gc = gcArray[0][0];
		
		gc.addPheromone(new Pheromone(new Colony(0, null), 30, gc));
		gc.addPheromone(new Pheromone(new Colony(1, null), 50, gc));
		gc.addPheromone(new Pheromone(new Colony(1, null), 20, gc));
		gc.addPheromone(new Pheromone(new Colony(1, null), 70, gc));
		
		List<Pheromone> pherList = gc.getPheromone();
		
		Collections.sort(pherList, new PheromoneComparator());
		
		Pheromone p = pherList.get(0);
		
		assertEquals(p.getStrength(), 20);
	}
	
	


}
