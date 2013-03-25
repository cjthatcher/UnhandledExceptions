import java.util.HashMap;
import java.util.Map;

 public class Authenticator
 {
	 Map userMap = new HashMap<String, User>();
	 
     public String login(String userName, String password)
     {
    	 if (userMap.containsKey(userName))
    	 {
    		 User temp = (User) userMap.get(userName);
    		 
    		 if (temp.check(userName, password))
    		 {
    			 Session s = Session.getInstance();
    			 String name = String.format("%s %s", temp.getFirstName(), temp.getLastName());
    			 String time = s.getTime().toString();
    			 String accessLevel = Integer.toString(s.getAccessLevel());
    			 
    			 
    			 //successfully logged in
    		 }
    	 }
    	 return "Shutup!";

     }
 
 
 }