import java.util.HashMap;
import java.util.Map;

 public class Authenticator
 {
	 UserHash uHash = UserHash.getInstance();
	 
     public String login(String userName, String password)
     {
    	 HashMap<String, User> userMap = uHash.getUserData();
    	 
    	 if (userMap.containsKey(userName))
    	 {
    		 User temp = (User) userMap.get(userName);
    		 
    		 if (temp.check(userName, password))
    		 {
    			 Session s = Session.getInstance();
    			 String name = String.format("%s %s", temp.getFirstName(), temp.getLastName());
    			 String time = s.getTime().toString();
    			 String accessLevel = Integer.toString(s.getAccessLevel());
    			 
    			 StringBuilder sb = new StringBuilder();
    			 sb.append(String.format("%s successfully logged in at %s with access level %s", name, time, accessLevel));
    			 return sb.toString();
    		 }
    		 else
    		 {
    			 String name = String.format("%s %s", temp.getFirstName(), temp.getLastName());
    			 String message = String.format("Password Incorrect. You provided %s", name);
    			 return message;
    		 }
    	 }
    	 
    	 return "User does not exist";
     }
 
 
 }