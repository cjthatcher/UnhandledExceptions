import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class MovementTests {

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