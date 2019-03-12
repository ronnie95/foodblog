 <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="foodblog.RestaurantBean"
         import="foodblog.MenuBean"
         
         import="java.util.*" 
         
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Logged In
<form action='LogoutServlet' method='post'>
            	<input type="submit" value="Logout"/>
           </form>
<a href="FetchOrdersServlet">View Orders</a>
<table>
<%  
	RestaurantBean R=(RestaurantBean)(session.getAttribute("currentRestaurant"));
	ArrayList<MenuBean> M= R.getMenu() ; 
            	
            	for(MenuBean obj : M)
            	{
            		out.println("<tr><td><br>"+obj.getName()+" </td><td>Price: "+obj.getPrice()+"</td></tr>");
            		
            	}
            
            %>
            </table>
Add Menu<br>
<form action="AddMenuServlet" method="post">
Name:<input type="text" name="name"/><br>
Price: <input type="text" name="price"/><br>
<input type="submit" value="add" />

</form>
</body>
</html>