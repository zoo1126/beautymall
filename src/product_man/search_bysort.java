package product_man;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DB_Operation;
import product.product;

/**
 * Servlet implementation class search_bysort
 */
@WebServlet("/search_bysort")
public class search_bysort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search_bysort() {
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
		String sort=request.getParameter("sort");
		String select=request.getParameter("select");
		if(sort!=""&&sort!=null)
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> product;
			try {
				if(select!=null)
				{
					if("1".contentEquals(select))
					{
						product=db.queryproduct("分类",sort, "价格", "升序");
					}
					else if("2".contentEquals(select))
					{
						product=db.queryproduct("分类", sort, "价格", "降序");
					}
					else
					{
						product=db.queryproduct("分类",sort, null, "");
					}
				}
				else
				{
					product=db.queryproduct("分类", sort, null, "");
					select="0";
				}
				if(id!=null)
				{
					request.setAttribute("id", id);
					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("sort", sort);
					request.getRequestDispatcher("/shopping/search_bysort.jsp?id="+id+"&sort="+sort+"&select="+select).forward(request, response);
				}
				else
				{

					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("sort", sort);
					request.getRequestDispatcher("/shopping/search_bysort.jsp?sort="+sort+"&select="+select).forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			if(id!=null)
				request.getRequestDispatcher("/shopping/index.jsp?id="+id).forward(request, response);
			else
				request.getRequestDispatcher("/shopping/index.jsp").forward(request, response);
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
