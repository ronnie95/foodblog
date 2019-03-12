package foodblog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;




/**
 * Servlet implementation class UploadServlet
 */

@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	
	private final String UPLOAD_DIRECTORY = "D:/";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ArrayList<ImageBean> img=new ArrayList<ImageBean>();
		
		String otherFieldValue=null; 
		String text1=null;
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
				
				String txt=request.getParameter("text1");
				System.out.println(txt);
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
				for(FileItem item : multiparts)
				{
					if(!item.isFormField())
					{
						//if(item==null) continue;
						String name = new File(item.getName()).getName();
						if(name=="") continue;
						System.out.println("filename:" + name);
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
						ImageBean temp=new ImageBean();
						temp.setUrl(name);
						img.add(temp);
						
					}
					else {
			            String otherFieldName = item.getFieldName();
			            System.out.println(otherFieldName);
			            if(otherFieldName.equals("text1"))
			            	text1 = item.getString();
			  
			            System.out.println(text1);
			             }
				}
				request.setAttribute("message", "File uploaded successfully.");
				PostBean P=new PostBean();
				P.setText(text1);
				P.setImages(img);
				P.setLikes(0);
				P.setUser((UserBean) request.getSession(false).getAttribute("currentSessionUser"));
				
				System.out.println(((UserBean)request.getSession(false).getAttribute("currentSessionUser")).getUsername());
				System.out.println("other: "+text1);
				//call DAO
				PostBean recv=PostDAO.setPost(P);
				ArrayList<PostBean> PB=(ArrayList<PostBean>) request.getSession(false).getAttribute("posts");
				PB.add(0,recv);
				UserBean U=(UserBean) request.getSession(false).getAttribute("currentSessionUser");
				U.setRewards(U.getRewards()+1);
				UserDAO.updateRewards(U);
				HttpSession session = request.getSession(true);	    
		        session.setAttribute("posts",PB);
		        
		        //response.sendRedirect("userLogged.jsp");
				
				
			}
			catch(Exception ex)
			{
				request.setAttribute("message", "File upload failed due to : " + ex);
			}
		}
		else
		{
			request.setAttribute("message", "Sorry this servlet only handles file upload request.");
		}
		//request.getRequestDispatcher("/userLogged.jsp").forward(request, response);
		response.sendRedirect("userLogged.jsp");
	}

}
