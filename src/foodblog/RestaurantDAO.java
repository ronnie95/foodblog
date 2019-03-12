package foodblog;


import java.text.*;
import java.util.*;
import java.sql.*;

public class RestaurantDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
	
	
   public static RestaurantBean login(RestaurantBean bean) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	
      String username = bean.getResName();    
      String password = bean.getPassword();   
	    
      String searchQuery =
            "select * from restaurant where name='"
                     + username
                     + "' AND password='"
                     + password
                     + "'";
	    
   // "System.out.println" prints in the console; Normally used to trace the process
   System.out.println("Your user name is " + username);          
   System.out.println("Your password is " + password);
   System.out.println("Query: "+searchQuery);
	    
   try 
   {
      //connect to DB 
      currentCon = ConnectionManager.getConnection();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	        
      boolean more = rs.next();
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Sorry, you are not a registered  user! Please sign up first");
         return null;
      } 
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {
         String loc = rs.getString("location");
         int seat = rs.getInt("seat");
	     	
         System.out.println("Welcome " + username);
         bean.setLocation(loc);
         bean.setSeats(seat);
         
         rs=stmt.executeQuery("select * from menu where rid='"+username+"'");
         ArrayList<MenuBean> M=new ArrayList<MenuBean>();
         while(rs.next())
         {
        	 MenuBean temp=new MenuBean();
        	 temp.setName(rs.getString("name"));
        	 temp.setPrice(rs.getInt("price"));
        	 M.add(temp);
         }
         bean.setMenu(M);
         
         
         
         
         
      }
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In failed: An Exception has occurred! " + ex);
   } 
	    
   //some exception handling
   finally 
   {
      if (rs != null)	{
         try {
            rs.close();
         } catch (Exception e) {}
            rs = null;
         }
	
      if (stmt != null) {
         try {
            stmt.close();
         } catch (Exception e) {}
            stmt = null;
         }
	
      if (currentCon != null) {
         try {
            currentCon.close();
         } catch (Exception e) {
         }

         currentCon = null;
      }
   }

return bean;
	
   }	
   
   public static MenuBean setMenu(String name,int price,String rid)
   {
	   Statement stmt = null;   
	   MenuBean M=null;
	   try {
		   currentCon = ConnectionManager.getConnection();
		   stmt=currentCon.createStatement();
		   rs = stmt.executeQuery("select max(id) from menu");
		   rs.next();
		   int newId=rs.getInt("max(id)") + 1;	
		   
		   int r=stmt.executeUpdate("insert into menu(id,name,rid,price) values('"+newId+"','"+name+"','"+rid+"','"+price+"')");
		   M=new MenuBean();
		   M.setName(name);
		   M.setPrice(price);
	   }
	   catch(Exception E)
	   {
		   System.out.println(E.getMessage());
	   }
	   
	   return M;
   }
}