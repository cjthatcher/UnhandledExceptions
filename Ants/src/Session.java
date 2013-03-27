import java.util.Date;


public class Session //Is a singleton
 {
     static Date loginTime;
     int accessLevel;
     private static Session instance=null;
     User thisUser;
     
     
     protected Session()
     {
     	//don't sweat this.
     }
     
     public void setAccessLevel(int accessLevel)
     {
    	 this.accessLevel = accessLevel;
     }
     
     public static Session getInstance()
     {
    	if (instance == null)
    	{
    		instance = new Session();
    		instance.loginTime = new Date();
    	}
    	
     	return instance;
     }

     public Date getTime()
     {
     	return loginTime;
     }
     public int getAccessLevel()
     {
     	return accessLevel;
     }
     
     public User getUser()
     {
    	 return thisUser;
     }
     
     public Date getLoginTime()
     {
    	 return loginTime;
     }
     
     void setUser(User u)
     {
    	 thisUser = u;
     }

    
 }