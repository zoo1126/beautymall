package orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class u_search_s
 */
@WebServlet("/u_search_s")
public class u_search_s extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public u_search_s() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DB A=new DB();
		String id=request.getParameter("id");
		String msg=request.getParameter("msg");
	if(id!=null)
	{	
		ArrayList<order> orders;
		try {
			orders=A.u_search(id);
			request.setAttribute("orders", orders);
			request.setAttribute("id", id);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/shopping/user_order.jsp?id="+id).forward(request, response);
	
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
	}
	else
		request.getRequestDispatcher("/faces/shopping/login.xhtml").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
 

}
