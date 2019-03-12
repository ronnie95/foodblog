<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>


<html>
	<head>
                                <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
	</head>

	<body>
		<form action="RestaurantServlet" method="post">

			Restaurant		
			<input type="text" name="un"/><br>		
		
			password
			<input type="password" name="pw"/>
			
			<input type="submit" value="submit">			
		
		</form>
		<a href="newrestaurant.jsp">Sign Up</a>
	</body>
</html>