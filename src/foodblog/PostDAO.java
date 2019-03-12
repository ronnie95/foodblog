package foodblog;


//import java.text.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class PostDAO 	
{
   static Connection currentCon = null;
  
   static ResultSet postquery = null,userquery=null,imagequery=null,commentsquery=null,likesquery=null; 
  
  
	
	
   public static ArrayList<PostBean> getPosts() {
	
      //preparing some objects for connection 
      Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;    
      ArrayList<PostBean> posts=new ArrayList<PostBean>();
     // String username = bean.getUsername();    
      //String password = bean.getPassword();   
	    
      //String searchQuery ="select * from post" ;
	    
   // "System.out.println" prints in the console; Normally used to trace the process
   //System.out.println("Your user name is " + username);          
   //System.out.println("Your password is " + password);
   //System.out.println("Query: "+searchQuery);
	int c=0;    
   try 
   {
      //connect to DB 
      currentCon = ConnectionManager.getConnection();
      stmt=currentCon.createStatement();
      
      stmt1=currentCon.createStatement();
      stmt2=currentCon.createStatement();
      stmt3=currentCon.createStatement();
      
      postquery = stmt.executeQuery("select * from post order by ts desc");	        
      //boolean more = postquery.next();
	    boolean more=true;  
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("No posts to show");
         //bean.setValid(false);
      } 
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {
         while(postquery.next())
         {
        	System.out.println("In postquery");
        	String pid=postquery.getString("id");
        	
        	 PostBean P=new PostBean();
        	 P.setId(pid);
        	 P.setText(postquery.getString("text"));
        	 P.setLikes(postquery.getLong("likes"));
        	 P.setTs(postquery.getString("ts"));
        	 System.out.println("After initial");
        	 
        	 String uid=postquery.getString("uid");
        	 userquery=stmt2.executeQuery("select * from users where username= '"+uid+"'");	 
        	 System.out.println("After userquery 1");
        	 userquery.next();
        		UserBean U=new UserBean();
        		U.setUserName(userquery.getString("username"));
        		U.setFirstName(userquery.getString("FirstName"));
        		U.setLastName(userquery.getString("LastName"));
        		U.setPhoto(userquery.getString("photo"));
        		P.setUser(U);
        	 userquery.close();
        	 System.out.println("select * from image where pid='"+pid+"'");
        	 imagequery=stmt2.executeQuery("select * from image where pid='"+pid+"'");	 
        	 
        	 ArrayList<ImageBean> Im=new ArrayList<ImageBean>();
        	 while(imagequery.next())
        	 {
        		 ImageBean I=new ImageBean();
        		 I.setId(imagequery.getInt("id"));
        		 I.setUrl(imagequery.getString("url"));
        		 Im.add(I);
        	 }
        	 imagequery.close();
        	 P.setImages(Im);
        	 System.out.println("After imagequery 1");
        	 
        	 commentsquery=stmt2.executeQuery("select * from comments where pid='"+pid+"'");	  
        	 System.out.println("After commentsquery");
        	 ArrayList<CommentsBean> Cm=new ArrayList<CommentsBean>();
        	 while(commentsquery.next())
        	 {
        		 CommentsBean C=new CommentsBean();
        		 int cid=commentsquery.getInt("id");
        		 C.setId(cid);
        		 System.out.println("cid "+cid);
        		 C.setText(commentsquery.getString("text"));
        		 userquery.close();
        		 userquery=stmt1.executeQuery("select * from users where username= '"+commentsquery.getString("uid")+"'");
        		 
        		 userquery.next();
        		 U=new UserBean();
         		U.setUserName(userquery.getString("username"));
         		U.setFirstName(userquery.getString("FirstName"));
         		U.setLastName(userquery.getString("LastName"));
         		
         		
         		C.setUser(U);
         		
         		Cm.add(C);
         		System.out.println("After user1 set for comments");
        	 }
        	 
        	 userquery.close();
        	 commentsquery.close();
        	 
        	 P.setComments(Cm);
        	 
        	 System.out.println("After comments set");
        	 
        	 likesquery=stmt2.executeQuery("select * from likes where pid='"+pid+"'");	
        	 ArrayList<LikesBean> Lm=new ArrayList<LikesBean>();
        	 while(likesquery.next())
        	 {
        		 LikesBean L=new LikesBean();
        		 L.setId(likesquery.getInt("id"));
        		 userquery.close();
        		 userquery=stmt3.executeQuery("select * from users where username= '"+likesquery.getString("uid")+"'");
        		 userquery.next();
        		  U=new UserBean();
         		U.setUserName(userquery.getString("username"));
         		U.setFirstName(userquery.getString("FirstName"));
         		U.setLastName(userquery.getString("LastName"));
         		L.setUser(U);
         		Lm.add(L);
        	 }
        	
        	 userquery.close();
        	 likesquery.close();
        	 P.setLikesObj(Lm);
        	 posts.add(P);
        	 System.out.println("After likes set");
        	 c++;
        	 P.setLikes(P.getLikesObj().size());
        	 //System.out.println(c);
         }
         System.out.println(c+" posts added");
         postquery.close();
         
         
      }
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In failed: An Exception has occurred! " + ex);
   } 
	    
   //some exception handling
   finally 
   {
      
//	
//      if (stmt != null) {
//         try {
//            stmt.close();
//         } catch (Exception e) {}
//            stmt = null;
//         }
//	
//      if (currentCon != null) {
//         try {
//            currentCon.close();
//         } catch (Exception e) {
//         }
//
//         currentCon = null;
     // }
   }

return posts;
	
   }	
   
   public static PostBean setPost(PostBean P)
   {
	   Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;    
	   
	   try {
		   currentCon = ConnectionManager.getConnection();
		   stmt=currentCon.createStatement();
		   postquery = stmt.executeQuery("select max(cast(id as unsigned)) as id from post");
		   postquery.next();
		   int newId=postquery.getInt("id") + 1;
		   
		   postquery.close();
		   String text=P.getText();
		   String uid=P.getUser().getUsername();
		   P.setId(String.valueOf(newId));
		   //Date object
			 Date date= new Date();
		         //getTime() returns current time in milliseconds
			 long time = date.getTime();
		         //Passed the milliseconds to constructor of Timestamp class 
			 Timestamp ts = new Timestamp(time);
			 System.out.println("time="+String.valueOf(ts));
		   P.setTs(String.valueOf(ts));
		   
		   
		   
		   int res=stmt.executeUpdate("insert into post (id,text,likes,uid,ts) values ('"+newId+"','"+text+"','0','"+uid+"','"+ts+"')");
		   
		   ArrayList<ImageBean> I=P.getImages();
		   imagequery=stmt.executeQuery("select max(cast(id as unsigned)) as id from image");
		   imagequery.next();
		   int newImageId=imagequery.getInt("id") + 1;
		   imagequery.close();
		   for(ImageBean obj : I)
		   {
			   res=stmt.executeUpdate("insert into image (id,url,pid) values ('"+newImageId+"','"+obj.getUrl()+"','"+newId+"')");
			   
			   newImageId++;
		   }
		  
	   }
	   catch(Exception E)
	   {
		   System.out.println(E.getMessage());
	   }
	   return P;
	   
   }
   
   
   public static LikesBean setLikes(String id,String uid)
   {
	   Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;  
	   LikesBean L=null;
	   try {
		   currentCon = ConnectionManager.getConnection();
		   stmt=currentCon.createStatement();
		   postquery = stmt.executeQuery("select max(cast(id as unsigned)) as id from likes");
		   postquery.next();
		   int newId=postquery.getInt("id") + 1;
		   System.out.println(newId+" "+uid+" "+id);
		   postquery.close();
		   likesquery=stmt.executeQuery("select * from likes where uid='"+uid+"' and pid='"+id+"'");
		  // likesquery.close();
		   
		   if(!likesquery.next())
		   {
			   int res=stmt.executeUpdate("insert into likes(id,uid,pid) values ('"+newId+"','"+uid+"','"+id+"')");
			   L=new LikesBean();
			   L.setId(newId);
			   userquery=stmt.executeQuery("select * from users where username='"+uid+"'");
			   userquery.next();
			   UserBean U=new UserBean();
			   U.setFirstName(userquery.getString("FirstName"));
			   U.setLastName(userquery.getString("LastName"));
			   U.setUserName(uid);
			   L.setUser(U);
		   }
		   else L=null;
		   likesquery.close();
	   }
	   catch(Exception E)
	   {
		   System.out.println(E.getMessage());
	   }
	   if(L==null) System.out.println("L is null");
	   return L;
   }
   
   
   public static CommentsBean setComment(String id,String uid,String comment)
   {
	   Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;  
	   CommentsBean C=null;
	   try {
		   currentCon = ConnectionManager.getConnection();
		   stmt=currentCon.createStatement();
		   postquery = stmt.executeQuery("select max(cast(id as unsigned)) as id from comments");
		   postquery.next();
		   int newId=postquery.getInt("id") + 1;
		   System.out.println(newId+" "+uid+" "+id+" "+comment);
		   postquery.close();
		   
		  
			   int res=stmt.executeUpdate("insert into comments(id,uid,pid,text) values ('"+newId+"','"+uid+"','"+id+"','"+comment+"')");
			   C=new CommentsBean();
			   C.setId(newId);
			   userquery=stmt.executeQuery("select * from users where username='"+uid+"'");
			   userquery.next();
			   UserBean U=new UserBean();
			   U.setFirstName(userquery.getString("FirstName"));
			   U.setLastName(userquery.getString("LastName"));
			   U.setUserName(uid);
			   C.setUser(U);
			   C.setText(comment);
		   
		   
	   }
	   catch(Exception E)
	   {
		   System.out.println(E.getMessage());
	   }
	   if(C==null) System.out.println("C is null");
	   return C;
   }
   
   
   public static ArrayList<OfferBean> getOffers() 
   {
	   ArrayList<OfferBean> O=new ArrayList<OfferBean>();
	   Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;  
	   CommentsBean C=null;
	   try {
		   currentCon = ConnectionManager.getConnection();
		   stmt=currentCon.createStatement();
		   postquery = stmt.executeQuery("select * from offers");
		   while(postquery.next())
		   {
			   OfferBean temp=new OfferBean();
			   temp.setId(postquery.getInt("id"));
			   temp.setText(postquery.getString("text"));
			   temp.setRid(postquery.getString("rid"));
			   O.add(temp);
		   }
		   
	   }
	   catch(Exception E)
	   {
		   System.out.println(E.getMessage());
	   }
	   
	   return O;
	   
	   
	   
   }
   
}
