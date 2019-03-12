
   <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="foodblog.UserBean"
         import="foodblog.PostBean"
         import="foodblog.CommentsBean"
         import="foodblog.ImageBean"
         import="foodblog.LikesBean"
         import="foodblog.OfferBean"
         import="java.util.*" 
         
   %>
 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
      </head>
	
      <body>
      <marquee>
      
       <b>Mega Offers   &nbsp;&nbsp;&nbsp;   </b>
            <% ArrayList<OfferBean> O=(ArrayList<OfferBean>) (session.getAttribute("offers")) ; 
            
            	for(OfferBean obj : O)
            	{
            		out.println(obj.getText()+" by "+obj.getRid()+"&nbsp;&nbsp;&nbsp;");
            	}
            
            %>
            </marquee>
            <br><br>
		
         <center>
        
        	
            <% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>
			<% out.println("<img src='D:/"+currentUser.getPhoto()+"' width='50px' height='50px'/>");  %>
            <b>Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %><br>
            
            
            
            Usable Reward Points: <%=currentUser.getRewards() %><br></b>
            <form action='LogoutServlet' method='post'>
            	<input type="submit" value="Logout"/>
            </form>
             
            <a href='AllRestaurantsServlet'>Place Order</a><br><br>
            <a href='showOrders.jsp'>View Orders</a><br>
            <form action="UploadFileServlet" method="post" enctype="multipart/form-data" name="form1" id="form1">
		<center>
			<table border="1">
				<tr>
					<td align="center"><b>Make a new Post</b></td>
				</tr>
				<tr>
					<td>
						<textarea name="text1"></textarea><br>
						Upload Images : <input name="file" type="file" id="file" multiple>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" name="Submit" value="Submit Post">
					</td>
				</tr>
			</table>
		</center>
	</form>
            
            
            <center>
            
            
            
            
            
            
            <table align="center" style="margin-left:auto; margin-right:auto;">
            <%  ArrayList<PostBean> P= (ArrayList<PostBean>) (session.getAttribute("posts")) ; 
            	
            	for(PostBean obj : P)
            	{
            		out.println("<tr><td>");
            		out.println("<img src='D:/"+obj.getUser().getPhoto()+"' width='50px' height='50px'/>");
            		out.println("<br>"+obj.getUser().getFirstName()+" "+obj.getUser().getLastName()+": <br>");
            		out.println("<br>Posted on: "+obj.getTs());
            		out.println("</tr>");
            		out.println("<tr>");
            		out.println("<td><b>"+obj.getText()+"<b></td>");
            		out.println("</tr>");
            		out.println("<tr><td>");
            		
            		for(ImageBean iobj: obj.getImages())
            		{
            			out.println("<img src='D:/"+iobj.getUrl()+"'width='500px' height='500px'/>");
            		}
            		out.println("</td></tr>");
            		out.println("<tr><td>");
            		out.println("<a href='CountLikesServlet?id="+obj.getId()+"'>Likes: "+obj.getLikes()+"</a>");
            		
            		out.println("<form action='LikeServlet' method='post'><input type='submit'value='like'/><input type='hidden' name='id' value='"+obj.getId()+"'></form>");
            		out.println("</td></tr>");
            		out.println("<tr><td>");
            		out.println("<form action='CommentServlet' method='post'><input type='text' name='comment'/><input type='submit'value='comment'/><input type='hidden' name='id' value='"+obj.getId()+"'></form>");
            		out.println("</td></tr>");
            		
            		if(obj.getComments()!=null){
            		
            		for(CommentsBean cobj: obj.getComments())
            		{	out.println("<tr><td>");
            			out.println(cobj.getUser().getFirstName()+" "+cobj.getUser().getLastName()+":");
            			out.println(cobj.getText()+"<br>");
            			out.println("</td></tr>");
            		}
            		
            		}
            		out.println("<tr><td><br><br></td></tr>");
            	}
            
            %>
            </table>
            </center>
         </center>

      </body>
	
   </html>
