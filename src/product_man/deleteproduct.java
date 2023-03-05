package product_man;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DB_Operation;

/**
 * Servlet implementation class deleteproduct
 */
@WebServlet("/deleteproduct")
public class deleteproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String pid=request.getParameter("pid");
		if(id!=null)
		{
			System.out.println(id+"11111");
			System.out.println(pid);
			DB_Operation db=new DB_Operation();
			try {
				boolean res=db.deleteproduct(pid);
					request.setAttribute("id", id);
					if(res)
					{
						request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id+"&msg=É¾³ý³É¹¦").forward(request, response);
						
					}
					else
					{
						request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id+"&msg=É¾³ýÊ§°Ü").forward(request, response);
					}
					
				
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			request.getRequestDispatcher("/shopping/man_login.xhtml").forward(request, response);
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
