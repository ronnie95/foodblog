<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="foodblog.RequestOrder"
    import="java.util.*"
    import="foodblog.OrderDAO"
    import="foodblog.UserBean"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>

<%  
	ArrayList<RequestOrder> R=(ArrayList<RequestOrder>)(session.getAttribute("pending")); 
            	
            	for(RequestOrder obj : R)
            	{
            		out.println("<tr><td><br>"+obj.getUid()+" "+obj.getOrderList()+"</td><td> Status: ");
            		if(obj.getUvalid()==0 && obj.getRvalid()==0) 
            		{
            			out.println("<form action='AcceptOrderServlet' method='post'><input type='hidden' name='oid' value='"+obj.getId()+"'/><input type='submit' value='Accept'/></form>");
            			out.println("<form action='RejectOrderServlet' method='post'><input type='hidden' name='oid' value='"+obj.getId()+"'/><input type='submit' value='Reject'/></form>");

            		}
            		else if(obj.getUvalid()==1 && obj.getRvalid()==1) out.println("Accepted");
            		else out.println("Rejected");
            		
            		out.println("</td></tr>");
            		
            		
            	}
            
            %>
            </table>
</body>
</html>