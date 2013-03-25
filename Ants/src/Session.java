import java.util.Date;


public class Session //Is a singleton
 {
     Date loginTime;
     int accessLevel;
     private static Session instance=null;
     
     protected Session(int accessLevel)
     {
     	this.accessLevel=accessLevel;
     	loginTime=new Date();
     }
     
     public static Session getInstance()
     {
     	return instance;
     }
     
     public static Session getInstance(int accessLevel)
     {
     	if(instance==null)
     	{
     		instance=new Session(accessLevel);
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