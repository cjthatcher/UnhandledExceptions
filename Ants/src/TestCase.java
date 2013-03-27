import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;


public class TestCase {

	@Test
	public void Test_01_authenticator_construct() {
		
		Authenticator a = new Authenticator();
		
		assertNotNull(a);
	}

	@Test
	public void Test_02_no_user()
	{
		Authenticator a = new Authenticator();
		
		String failString = a.login("preston", "myPassword");
		
		boolean itWorked = failString.startsWith("User does not exist");

		assertTrue(itWorked);
	}

	@Test
	public void Test_03_wrong_password()
	{
		Authenticator a = new Authenticator();
		
		String failString = a.login("georgeMoney", "myPassword");
		
		boolean itWorked = failString.startsWith("Password Incorrect");

		assertTrue(itWorked);
	}

	
	@Test
	public void test_04_signin_success()
	{
		Authenticator a = new Authenticator();
		
		String successString = a.login("georgeMoney", "redcoatsSuck");
		
		boolean itWorked = successString.startsWith("George Washington successfully logged in at");
		
		assertTrue(itWorked);
	}

	@Test
	public void test_05_successful_login_rights()
	{
		Authenticator a = new Authenticator();
		Session s = Session.getInstance();
		
		a.login("georgeMoney", "redcoatsSuck");
		int rights = s.getAccessLevel();
			
		assertEquals(2, rights);

	}
	
	@Test
	public void test_06_getLoginTime()
	{
		Authenticator a = new Authenticator();
		
		a.login("georgeMoney", "redcoatsSuck");
		assertNotNull(Session.getInstance().getLoginTime());
		
	}

	@Test
	public void test_07_getSession()
	{
		assertNotNull(Session.getInstance());
	}
	
	@Test
	public void test_08_getUserHash()
	{
		UserHash u = UserHash.getInstance();
		assertNotNull(u);
	}
	
	@Test
	public void test_09_getHashData()
	{
		UserHash u = UserHash.getInstance();
		Map<String, User> m = u.getUserData();
		
		assertNotNull(m);
	}
	
	@Test
	public void test_10_getExistingUser()
	{
		UserHash u = UserHash.getInstance();
		Map<String, User> m = u.getUserData();
		
		User temp = m.get("georgeMoney");
		assertEquals("George", temp.getFirstName());
		assertEquals("Washington", temp.getLastName());
	}
	
	@Test
	public void test_11_getNonUser()
	{
		UserHash u = UserHash.getInstance();
		Map<String, User> m = u.getUserData();
		
		User temp = m.get("imNotReal");
		assertNull(temp);
	}

}
