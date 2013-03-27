import java.util.Date;


public class Session //Is a singleton
 {
     static Date loginTime;
     int accessLevel;
     private static Session instance=null;
     
     protected Session(int accessLevel)
     {
     	this.accessLevel=accessLevel;
     }
     
     public static Session getInstance()
     {
    	if (instance == null)
    	{
    		instance = new Session(5);
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
 
 }