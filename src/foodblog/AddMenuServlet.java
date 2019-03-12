package foodblog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddMenuServlet
 */
@WebServlet("/AddMenuServlet")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		String rid= ((RestaurantBean)request.getSession(false).getAttribute("currentRestaurant")).getResName();
		MenuBean M=RestaurantDAO.setMenu(name,price,rid);
		//do stuff
		RestaurantBean R=(RestaurantBean)request.getSession(false).getAttribute("currentRestaurant");
		ArrayList<MenuBean> MB=R.getMenu();
		MB.add(M);
		R.setMenu(MB);
		HttpSession session = request.getSession(true);	    
        session.setAttribute("currentRestaurant",R);
        response.sendRedirect("resLogged.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
