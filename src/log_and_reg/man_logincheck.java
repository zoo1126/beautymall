package log_and_reg;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DB_Operation;
import users.userbean;

/**
 * Servlet implementation class man_logincheck
 */
@WebServlet("/man_logincheck")
public class man_logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public man_logincheck() {
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
		userbean userbean=(userbean)request.getSession().getAttribute("userbean");
		DB_Operation dop=new DB_Operation();
		
		try {
			
			if(dop.isAdmin(userbean.getUserid(), userbean.getPassword()))
			{
				System.out.println(userbean.getUserid());
				response.sendRedirect(request.getContextPath()+"/shopping/manage_index.jsp?id="+userbean.getUserid());
			}
			else
			{

				response.sendRedirect(request.getContextPath()+"/faces/shopping/man_login.xhtml?fail");
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
