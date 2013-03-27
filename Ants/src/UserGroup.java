import java.util.List;


public class UserGroup
{
	private String name;
   
	private List<AccessRight> listOfRights;

	public UserGroup(List<AccessRight> rights)
	{
		listOfRights = rights;
	}
	
	String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public List<AccessRight> getRights()
	{
		if(listOfRights.size()>0)
		{
			return listOfRights;
		}
		return null;
	}
	
}