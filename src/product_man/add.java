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
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
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
		System.out.println(pid);
		String pname=request.getParameter("pname");
		String sort=request.getParameter("sort");
		String brand=request.getParameter("brand");
		String price=request.getParameter("price");
		String discount=request.getParameter("discount");
		DB_Operation db=new DB_Operation();
try {
		if(!db.ispidexist(pid))
		{	product pro=new product();
			pro.setPid(pid);
			pro.setPname(pname);
			pro.setBrand(brand);
			pro.setPsort(sort);
			pro.setLiked(0);
			pro.setDiscount(Float.parseFloat(discount));
			pro.setPrice(Float.parseFloat(price));
		
			boolean res=db.addproduct(pro);
			if(res)
			{
				request.setAttribute("msg", "添加成功");
				if(id!=null)
					request.getRequestDispatcher("/shopping/manage_index.jsp?id="+id+"&msg=添加成功").forward(request, response);
				else
					request.getRequestDispatcher("/shopping/manage_index.jsp"+"&msg=添加成功").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "添加失败");
				if(id!=null)
					request.getRequestDispatcher("/shopping/manage_index.jsp?id="+id+"&msg=添加失败").forward(request, response);
				else
					request.getRequestDispatcher("/shopping/manage_index.jsp"+"&msg=添加失败").forward(request, response);
			}
		
		}
		else
		{
			request.setAttribute("msg", "编号已存在");
			if(id!=null)
				request.getRequestDispatcher("shopping/manage_index.jsp?id="+id+"&msg=编号已存在").forward(request, response);
			else
				request.getRequestDispatcher("shopping/manage_index.jsp"+"&msg=编号已存在").forward(request, response);
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
