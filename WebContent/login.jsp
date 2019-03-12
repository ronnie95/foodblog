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
		<form action="LoginServlet" method="post">

			Please enter your username 		
			<input type="text" name="un"/><br>		
		
			Please enter your password
			<input type="password" name="pw"/>
			
			<input type="submit" value="submit">			
		
		</form>
		<a href="usersignup.jsp">Sign Up</a>
	</body>
</html>