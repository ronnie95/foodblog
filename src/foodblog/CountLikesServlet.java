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
 * Servlet implementation class CountLikesServlet
 */
@WebServlet("/CountLikesServlet")
public class CountLikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountLikesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		System.out.println("id="+id);
		ArrayList<PostBean> PB=(ArrayList<PostBean>) request.getSession(false).getAttribute("posts");
		PostBean P=null;
		for(PostBean obj : PB)
		{
			if(obj.getId().equals(id)) {
				P=obj;
				break;
			}
			
			
		}
		
		ArrayList<LikesBean> L=P.getLikesObj();
		HttpSession session = request.getSession(true);	    
        session.setAttribute("likes",L);
		response.sendRedirect("showLikes.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
