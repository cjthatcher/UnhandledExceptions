import static org.junit.Assert.*;

import org.junit.Test;


public class MovementTests {

	ConfigurationClass configTest;
	
	@Test
	public void test() {
		configTest.setBoardWidth(4);
		configTest.setBoardHeight(4);
		configTest.setNumberOfColonies(0);
		Ground ground=Ground.getInstance();
		GroundCell gc=ground.getCellArray()[2][2];
		Nest nest=new Nest(new Colony(1, gc.getPosition()), gc);
		ground.getCellArray()[2][2].setNest(nest);
		assert(nest.getGroundCell().getPosition().getX()==2&&nest.getGroundCell().getPosition().getY()==2);
	}

}
