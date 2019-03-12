package foodblog;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try
		{	    

		     RestaurantBean res = new RestaurantBean();
		     res.setresName(request.getParameter("un"));
		     res.setPassword(request.getParameter("pw"));

		     res = RestaurantDAO.login(res);
			   		    
		     if (res!=null)
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentRestaurant",res);  
		          session.setAttribute("menu",null);
		          //ArrayList<RequestOrder> R=OrderDAO.getOrders(((UserBean) request.getSession(false).getAttribute("currentSessionUser")).getUsername());
		          session.setAttribute("orders",null);
		          response.sendRedirect("resLogged.jsp"); //logged-in page      		
		     }
			        
		     else 
		          response.sendRedirect("invalidLogin.jsp"); //error page 
		} 
				
				
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
