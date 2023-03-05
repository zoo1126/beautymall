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
 * Servlet implementation class search_special
 */
@WebServlet("/search_special")
public class search_special extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search_special() {
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
		String type=request.getParameter("type");
		String select=request.getParameter("select");
		if(type!=null&&"0".contentEquals(type))
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> product;
			try {
				if(select!=null)
				{
					if("1".contentEquals(select))
					{
						product=db.queryproduct("�ۿ�", null, "�۸�", "����");
					}
					else if("2".contentEquals(select))
					{
						product=db.queryproduct("�ۿ�", null, "�۸�", "����");
					}
					else
					{
						product=db.queryproduct("�ۿ�", null, null, "");
					}
				}
				else
				{
					product=db.queryproduct("�ۿ�", null, null, "");
					select="0";
				}
				if(id!=null)
				{
					request.setAttribute("id", id);
					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?id="+id+"&type="+type+"&select="+select).forward(request, response);
				}
				else
				{

					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?type="+type+"&select="+select).forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type!=null&&"1".contentEquals(type))
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> product;
			try {
				if(select!=null)
				{
					if("1".contentEquals(select))
					{
						product=db.queryproduct("��ױ", null, "�۸�", "����");
					}
					else if("2".contentEquals(select))
					{
						product=db.queryproduct("��ױ", null, "�۸�", "����");
					}
					else
					{
						product=db.queryproduct("��ױ", null, null, "");
					}
				}
				else
				{
					product=db.queryproduct("��ױ", null, null, "");
					select="0";
				}
				if(id!=null)
				{
					request.setAttribute("id", id);
					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?id="+id+"&type="+type+"&select="+select).forward(request, response);
				}
				else
				{

					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?type="+type+"&select="+select).forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type!=null&&"2".contentEquals(type))
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> product;
			try {
				if(select!=null)
				{
					if("1".contentEquals(select))
					{
						product=db.queryproduct("����", null, "�۸�", "����");
					}
					else if("2".contentEquals(select))
					{
						product=db.queryproduct("����", null, "�۸�", "����");
					}
					else
					{
						product=db.queryproduct("����", null, null, "");
					}
				}
				else
				{
					product=db.queryproduct("����", null, null, "");
					select="0";
				}
				if(id!=null)
				{
					request.setAttribute("id", id);
					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?id="+id+"&type="+type+"&select="+select).forward(request, response);
				}
				else
				{

					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?type="+type+"&select="+select).forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type!=null&&"3".contentEquals(type))	
		{
			DB_Operation db=new DB_Operation();
			ArrayList<product> product;
			try {
				if(select!=null)
				{
					if("1".contentEquals(select))
					{
						product=db.queryproduct("�ں�", null, "�۸�", "����");
					}
					else if("2".contentEquals(select))
					{
						product=db.queryproduct("�ں�", null, "�۸�", "����");
					}
					else
					{
						product=db.queryproduct("�ں�", null, null, "");
					}
				}
				else
				{
					product=db.queryproduct("�ں�", null, null, "");
					select="0";
				}
				if(id!=null)
				{
					request.setAttribute("id", id);
					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?id="+id+"&type="+type+"&select="+select).forward(request, response);
				}
				else
				{

					request.setAttribute("product", product);
					request.setAttribute("select", select);
					request.setAttribute("type", type);
					request.getRequestDispatcher("/shopping/search_special.jsp?type="+type+"&select="+select).forward(request, response);
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
