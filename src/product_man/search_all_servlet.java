package product_man;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DB_Con;
import DB.DB_Operation;
import product.product;
import product.productbean;
import users.userbean;

/**
 * Servlet implementation class search_all_servlet
 */
@WebServlet("/search_all_servlet")
public class search_all_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search_all_servlet() {
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
		String select=request.getParameter("select");
		String man=request.getParameter("man");
		if(id!=null&&man==null)
		{	DB_Operation db=new DB_Operation();
			ArrayList<product> pro;
			try {

				if(select!=null)
				{	
					if("2".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "降序");
					}
					else if("1".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "升序");
					}
					else
					{
						pro=db.queryproduct("all", null, null, "");
					}
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/user_searchall.jsp?id="+id+"&select="+select).forward(request, response);
				}
				else
				{
					pro=db.queryproduct("all", null, null, "");
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/user_searchall.jsp?id="+id+"&select=0").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ArrayList<product> pr=pro.getpros();
				
			
		}
		else if(id!=null&&man!=null&&"true".contentEquals(man))
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> pro;
			try {
				if(select!=null)
				{	
					if("2".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "降序");
					}
					else if("1".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "升序");
					}
					else
					{
						pro=db.queryproduct("all", null, null, "");
					}
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/man_searchall.jsp?id="+id+"&select="+select).forward(request, response);
				}
				else
				{
					pro=db.queryproduct("all", null, null, "");
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/man_searchall.jsp?id="+id+"&select=0").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(id==null&&man!=null&&"true".contentEquals(man))
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> pro;
			try {
				if(select!=null)
				{	
					if("2".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "降序");
					}
					else if("1".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "升序");
					}
					else
					{
						pro=db.queryproduct("all", null, null, "");
					}
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/man_searchall.jsp?select="+select).forward(request, response);
				}
				else
				{
					pro=db.queryproduct("all", null, null, "");
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/man_searchall.jsp?select=0").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> pro;
			try {
				
				if(select!=null)
				{	
					if("2".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "降序");
					}
					else if("1".contentEquals(select))
					{
						pro=db.queryproduct("all", null, "价格", "升序");
					}
					else
					{
						pro=db.queryproduct("all", null, null, "");
					}
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/user_searchall.jsp?select="+select).forward(request, response);
				}
				else
				{
					pro=db.queryproduct("all", null, null, "");
					request.setAttribute("id", id);
					request.setAttribute("product", pro);
					request.getRequestDispatcher("/shopping/user_searchall.jsp?select=0").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
