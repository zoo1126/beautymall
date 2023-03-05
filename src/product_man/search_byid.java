package product_man;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DB_Operation;
import product.product;

/**
 * Servlet implementation class search_byid
 */
@WebServlet("/search_byid")
public class search_byid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search_byid() {
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
		DB_Operation db=new DB_Operation();
		product pro=new product();
		try {
			pro=db.idproduct(pid);
			if(id!=null)
			{
				System.out.println(id);
				request.setAttribute("id", id);
				request.setAttribute("product", pro);
				request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id).forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("faces/shopping/man_login.xhtml").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
