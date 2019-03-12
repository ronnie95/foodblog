package foodblog;


//import java.text.*;
import java.util.*;
import java.sql.*;

public class OrderDAO 	
{
   static Connection currentCon = null;
  
   static ResultSet postquery = null,userquery=null,imagequery=null,commentsquery=null,likesquery=null; 
  
  
	
	
   public static void setOrder(RequestOrder R) {
	
      //preparing some objects for connection 
      Statement stmt = null;   
    
	
   try 
   {
      //connect to DB 
      currentCon = ConnectionManager.getConnection();
      stmt=currentCon.createStatement();
      
      
      
      postquery = stmt.executeQuery("select max(cast(id as unsigned)) as id from request");
      postquery.next();
      int newId=postquery.getInt("id") + 1;
      int res=stmt.executeUpdate("insert into request(id,uid,rid,uvalid,rvalid,orderlist) values ('"+newId+"','"+R.getUid()+"','"+R.getRid()+"','"+R.getUvalid()+"','"+R.getRvalid()+"','"+R.getOrderList()+"')");
      
      //boolean more = postquery.next();
	 
   } 

   catch (Exception ex) 
   {
      System.out.println(ex.getMessage());
   } 
	    
   //some exception handling
 
	
   }
   
   public static ArrayList<RequestOrder> getOrders(String uid)
   {
	   ArrayList<RequestOrder> R=new ArrayList<RequestOrder>();
	   Statement stmt = null;   
	    
		
	   try 
	   {
	      //connect to DB 
	      currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      
	      
	     
	      postquery = stmt.executeQuery("select * from request where uid='"+uid+"'");
	      while(postquery.next())
	      {
	    	  RequestOrder temp=new RequestOrder();
	    	  temp.setId(postquery.getInt("id"));
	    	  temp.setOrderList(postquery.getString("orderlist"));
	    	  temp.setRid(postquery.getString("rid"));
	    	  temp.setUid(postquery.getString("uid"));
	    	  temp.setRvalid(postquery.getInt("rvalid"));
	    	  temp.setUvalid(postquery.getInt("uvalid"));
	    	  R.add(temp);
	    	  
	      }
	      
	      //boolean more = postquery.next();
		 
	   } 

	   catch (Exception ex) 
	   {
	      System.out.println(ex.getMessage());
	   } 
	   
	   return R;
   }
   
  
}
