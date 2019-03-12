<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="foodblog.MenuBean"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="SelectRestaurantServlet" method="post">
Select restaurant:
<select name="res">
<%ArrayList<String> res = (ArrayList<String>) (session.getAttribute("allRestaurants"));
for(String s : res)
	out.println("<option value='"+s+"'>"+s+"</option>");

%>
</select>
<input type="submit" value="Go"/>
</form>

<form action="ContactRestaurantServlet" method="post">
<table>
<%ArrayList<MenuBean> M=(ArrayList<MenuBean>) (session.getAttribute("menu"));

if(M!=null)
{
	int c=0;
	for(MenuBean obj : M)
	{
		out.println("<tr><td>"+obj.getName()+"</td><td> Price: "+obj.getPrice()+"</td><td> Quantity: <input type='text' name='item"+(c++)+"' value='0'/></td></tr><br>");
	}
	out.println("</table><input type='submit' value='submit'/>");
}

%>

</form>


</body>
</html>