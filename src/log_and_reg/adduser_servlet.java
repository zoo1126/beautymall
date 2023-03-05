package log_and_reg;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import DB.DB_Operation;
import users.userbean;

/**
 * Servlet implementation class adduser_servlet
 */
@WebServlet("/adduser_servlet")
public class adduser_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adduser_servlet() {
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
		String arg[]= {userbean.getUserid(),userbean.getUsername(),userbean.getTelnumber(),userbean.getPassword()};
		try {
			if(dop.adduser(arg))
			{

				response.sendRedirect(request.getContextPath()+"/faces/shopping/login.xhtml?ok");
			}
			else
			{
				response.sendRedirect(request.getContextPath()+"/faces/shopping/signin.xhtml?fail");
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
