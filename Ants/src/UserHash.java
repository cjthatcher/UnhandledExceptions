import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class UserHash 
{
	private HashMap<String, User> userData=new HashMap<String, User>();
	private static UserHash instance=null;
	
	HashMap<String, User> getUserData()
	{
		return userData;
	}
	
	protected UserHash()
	{
		AccessRight rights1=new AccessRight("Right1");
		AccessRight rights2=new AccessRight("Right2");
		AccessRight rights3=new AccessRight("Right3");
		AccessRight rights4=new AccessRight("Right4");
		AccessRight rights5=new AccessRight("Right5");
		AccessRight rights6=new AccessRight("Right6");
		
		List<AccessRight> list1=new ArrayList<AccessRight>();
		List<AccessRight> list2=new ArrayList<AccessRight>();
		List<AccessRight> list3=new ArrayList<AccessRight>();
		List<AccessRight> list4=new ArrayList<AccessRight>();
		
		list1.add(rights1);
		list1.add(rights3);
		
		list2.add(rights4);
		list2.add(rights6);
		
		list3.add(rights2);
		list3.add(rights5);
		
		list4.add(rights1);
		list4.add(rights6);
		
		UserGroup group1=new UserGroup(list1);
		UserGroup group2=new UserGroup(list2);
		UserGroup group3=new UserGroup(list3);
		UserGroup group4=new UserGroup(list4);
		
		List<UserGroup> u1=new ArrayList<UserGroup>();
		List<UserGroup> u2=new ArrayList<UserGroup>();
		List<UserGroup> u3=new ArrayList<UserGroup>();
		List<UserGroup> u4=new ArrayList<UserGroup>();
		
		u1.add(group1);
		u1.add(group2);
		
		u2.add(group1);
		u2.add(group3);
		
		u3.add(group2);
		u3.add(group4);
		
		u4.add(group3);
		u4.add(group4);
		
		userData.put("GeorgeWashington", new User("georgeMoney", "George", "Washington", "redcoatsSuck", u1));
		userData.put("AbrahamLincoln", new User("awesomeAbe", "Abraham", "Lincoln", "vampires", u2));
		userData.put("TheodoreRoosevelt", new User("Teddy", "Theodore", "Roosevelt", "idowhatiwant", u3));
		userData.put("ThomasJefferson", new User("lil T", "Thomas", "Jefferson", "babyMaker", u4));
	}
	
	public static UserHash getInstance()
	{
		if(instance==null)
		{
			instance=new UserHash();
		}
		return instance;
	}
	
	public User getUser(String name)
	{
		return userData.get(name);
	}
}
