import java.util.List;



public class User {
	private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserGroup group;
    
    public User(String username, String first, String last, String password, UserGroup group)
    {
    	this.username = username;
    	this.firstName = first;
    	this.lastName = last;
    	this.password = password;
    	this.group = group;
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGroup getGroup() {
		return group;
	}

	public void setGroup(UserGroup group) {
		this.group = group;
	}

	public boolean check(String username, String password)
    {
        if(username == this.username && password == this.password)
        	return true;
        else
        	return false;
    }
}
