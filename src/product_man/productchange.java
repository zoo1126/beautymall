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
 * Servlet implementation class productchange
 */
@WebServlet("/productchange")
public class productchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productchange() {
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
		
		String oldpid=request.getParameter("oldpid");

		String pid=request.getParameter("pid");

		String pname=request.getParameter("pname");

		String sort=request.getParameter("sort");
	
		String brand=request.getParameter("brand");
		
		String price=request.getParameter("price");
		
		String discount=request.getParameter("discount");
		
		String msg="修改失败";
	if(id!=null)	
	{
		DB_Operation db=new DB_Operation();
	
		try {
			if(pid!=null&&!"".contentEquals(pid)&&oldpid.contentEquals(pid))
			{
				boolean rea=db.changeproduct(pid,pname,sort,brand,price,discount,oldpid);
				if(rea)
				{
					msg="修改成功";
					request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id+"&msg="+msg).forward(request, response);
				}
			}
			else if(!oldpid.contentEquals(pid)&&db.ispidexist(pid))
			{
				msg="该编号已存在！";
				request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id+"&msg="+msg).forward(request, response);
			}
			else
			{
				msg="输入错误！";
				request.getRequestDispatcher("/shopping/search_byid.jsp?id="+id+"&msg="+msg).forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	else {
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
