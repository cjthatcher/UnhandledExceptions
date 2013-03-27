import static org.junit.Assert.*;

import org.junit.Test;


public class TestCase {

	@Test
	public void Test_01_authenticator_construct() {
		
		Authenticator a = new Authenticator();
		
		assertNotNull(a);
	}
	
	@Test
	public void test_02_signin_success()
	{
		Authenticator a = new Authenticator();
		
		String successString = a.login("georgeMoney", "redcoatsSuck");
		
		boolean itWorked = successString.startsWith("George Washington successfully logged in at");
		
		assertTrue(itWorked);
	}

}
