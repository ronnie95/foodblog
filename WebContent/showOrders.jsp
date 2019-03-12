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
	ArrayList<RequestOrder> R=OrderDAO.getOrders(((UserBean)session.getAttribute("currentSessionUser")).getUsername()); 
            	
            	for(RequestOrder obj : R)
            	{
            		out.println("<tr><td><br>"+obj.getRid()+" "+obj.getOrderList()+"</td><td> Status: ");
            		if(obj.getUvalid()==0 && obj.getRvalid()==0) out.println("Pending");
            		else if(obj.getUvalid()==1 && obj.getRvalid()==1)
            			{out.println("Accepted");
            			 out.println("<form action=\"MakePostServlet?oid="+obj.getId()+"\" method='post'><input type='submit' value='Write a review'/></form>");
            			
            			}
            		else if(obj.getUvalid()==1 && obj.getRvalid()==0) out.println("Rejected");
            		out.println("</td></tr>");
            	}
            
            %>
            </table>
</body>
</html>