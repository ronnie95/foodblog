<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="foodblog.UserBean"
         import="foodblog.PostBean"
         import="foodblog.CommentsBean"
         import="foodblog.ImageBean"
         import="foodblog.LikesBean"
         import="java.util.*" 
         
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%  ArrayList<LikesBean> L= (ArrayList<LikesBean>) (session.getAttribute("likes")) ; 
            	if(L!=null){
            	for(LikesBean obj : L)
            	{
            		out.println(obj.getUser().getFirstName()+" "+obj.getUser().getLastName()+"<br>");
            	}
            	}
            
            %>
</body>
</html>