package orders;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class a_up_s
 */
@WebServlet("/a_up_s")
public class a_up_s extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_up_s() {
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
		try{
			cc=A.a_up(oid);
		
		if(cc.contentEquals("refund_ok"))
		{
			String msg="succ";
			request.getRequestDispatcher("/a_search_s?id="+id+"&msg="+msg).forward(request, response);
			 
		}
		else
		{
			String msg="fail";
			request.getRequestDispatcher("/a_search_s?id="+id+"&msg="+msg).forward(request, response);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	else
		request.getRequestDispatcher("/faces/shopping/man_login.xhtml").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
