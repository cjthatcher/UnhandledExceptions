package UnitTests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import Controller.ConfigurationClass;
import Model.Ant;
import Model.Colony;
import Model.Ground;
import Model.GroundCell;
import Model.Nest;
import Model.Pheromone;
import Model.PheromoneComparator;


public class UnitTests {
	
	public ConfigurationClass getConfigClass()
	{
		ConfigurationClass config = new ConfigurationClass();
		config.setAmountOfFoodPerPile(4);
		config.setAntBirthRate(100);
		config.setAntLifeSpan(500);
		config.setBoardHeight(10);
		config.setBoardWidth(10);
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
	
ConfigurationClass configTest=new ConfigurationClass();
	
	//tests for Colonies and Nests being placed properly
	//mainly tests Colony and Nest initializaion in ground constructor
	@Test
	public void getColonyPosition() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(0);
		Ground.setConfigurationClass(configTest);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[2][2];
		Nest nest=new Nest(new Colony(1, gc.getPosition()), gc);
		ground.getCellArray()[2][2].setNest(nest);
		assert(nest.getGroundCell().getPosition().getX()==2&&nest.getGroundCell().getPosition().getY()==2);
	}
	
	//tests for Ants being placed properly
	//mainly tests Colony's addAnt function
	@Test
	public void getAntPosition() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(1);
		Ground.setConfigurationClass(configTest);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[1][3];
		Nest nest=new Nest(new Colony(1, gc.getPosition()), gc);
		nest.getColony().addAnt(gc);
		assert(nest.getColony().getAnts().get(0).getLocation()==gc);
	}

	//tests for GroundCell having a correct list of ants
	//mainly tests GroundCell's getAnt function
	@Test
	public void getAntList() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(1);
		Ground.setConfigurationClass(configTest);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[1][3];
		Nest nest=new Nest(new Colony(1, gc.getPosition()), gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		assert(gc.getAnt().size()==3);
	}
	
	//tests to make sure Ants move from the cell they were initially on
	//mainly tests Ant's moveDirection function
	@Test
	public void testAntMovement() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(1);
		Ground.setConfigurationClass(configTest);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[1][3];
		Nest nest=new Nest(new Colony(1, gc.getPosition()), gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		List<Ant> antList=new ArrayList<Ant>(gc.getAnt());
		for(Ant ant:antList)
		{
			ant.moveDirection();
		}
		assert(gc.getAnt().size()==0);
	}
	
	//tests to make sure Ants move to Pheromones correctly
	//mainly tests Ground's findStrongestPheromone function, by way of moveDirection
	@Test
	public void testPheromoneMovement() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(1);
		Ground.setConfigurationClass(configTest);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[1][3];
		Colony colony=new Colony(1, gc.getPosition());
		Nest nest=new Nest(colony, gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		nest.getColony().addAnt(gc);
		ground.getCellArray()[1][2].addPheromone(new Pheromone(colony, 3, ground.getCellArray()[1][2]));
		List<Ant> antList=new ArrayList<Ant>(gc.getAnt());
		for(Ant ant:antList)
		{
			ant.moveDirection();
		}
		assert(ground.getCellArray()[1][2].getAnt().size()==5);
		ground.getCellArray()[0][2].addPheromone(new Pheromone(colony, 4, ground.getCellArray()[1][2]));
		gc=ground.getCellArray()[1][2];
		antList=new ArrayList<Ant>(gc.getAnt());
		for(Ant ant:antList)
		{
			ant.moveDirection();
		}
		assert(ground.getCellArray()[1][2].getAnt().size()==5);
	}
	


}
