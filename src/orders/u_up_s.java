package orders;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class u_up_s
 */
@WebServlet("/u_up_s")
public class u_up_s extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public u_up_s() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DB A=new DB();
		String oid=request.getParameter("oid");
		String id=request.getParameter("id");
		
	if(id!=null)	
	{	String cc;
		try {
			cc=A.u_up(oid);
		
		if(cc.contentEquals("app_succ"))
		{
			request.getRequestDispatcher("/u_search_s?id="+id+"&msg=succ").forward(request, response);
			 
		}
		else
		{
			request.getRequestDispatcher("/u_search_s?id="+id+"&msg=fail").forward(request, response);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
