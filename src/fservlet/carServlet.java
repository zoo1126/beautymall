package fservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.liked;
import bean.shopcarBean;
import bean.product;
import JDBC.connectDatabase;

/**
 * Servlet implementation class carServlet
 */
@WebServlet("/carServlet")
public class carServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName=request.getParameter("methodName");
		String uid = request.getParameter("id");
		String pid=request.getParameter("pid");
		String pnum=request.getParameter("pnum");
		System.out.println(uid+" "+pid+" "+pnum);
		try 
		{  
			switch(methodName)
		       {
		    	case "0":
		    		{
		    		dispatchcar(request,response,uid);//��ѯȫ�����ﳵ
		    		break;
		    		}
		    	case "1":
		    		{
		    		deletecar(request,response);//
		    		break;
		    		}
		    	case "2":
	    		{	    			
	    		insertIntoOrders(request,response);//������Ӷ���
	    		break;
	    		}
		    	case "3":
	    		{
	    		updateShopCar(request,response);//�޸Ĺ��ﳵ����	
	    		break;
	    		}
		    	case "4":
	    		{
	    		addShopCar(request,response,uid);//	��ӹ��ﳵ
	    		break;
	    		}
		    	case "5":
	    		{
	    			
	    			buyProducts(request,response,uid);
	    		break;
	    		}
		       }
			} catch (ClassNotFoundException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}
		// TODO Auto-generated method stub
		public ArrayList<shopcarBean> SearchCar(String uid) throws Exception			
		{ 	
			connectDatabase con=new connectDatabase();
	    	Statement stat=null;
		    ResultSet rs=null;
			stat=con.getConnection().createStatement();
	    	ArrayList<shopcarBean> result=new ArrayList<shopcarBean>();
	    	 rs=stat.executeQuery("select * from shopcar where uid="+uid); 
	    	while(rs.next())
	        {
	    		shopcarBean car=new shopcarBean();
	        	car.setPid(rs.getString("pid"));
	        	car.setUid(rs.getString("uid"));
	        	car.setPname(rs.getString("pname"));
	        	car.setPnum(rs.getString("pnum"));
	        	result.add(car);
	        }
		    if(rs!=null){
		    	  rs.close();
		       }
		    con.closeAll(rs, stat, con.getConnection());
	    	return result;
		}
		public void dispatchcar(HttpServletRequest request, HttpServletResponse response,String uid) throws Exception, IOException, Exception
		{
			  if(!SearchCar(uid).isEmpty()){
				 request.setAttribute("result", SearchCar(uid));
				request.getRequestDispatcher("/shopping/shopcarSelect.jsp?id="+uid).forward(request, response);
			  }
			  else
				  request.getRequestDispatcher("/shopping/carIsEmpty.jsp?id="+request.getParameter("id")).forward(request, response);
		}
		//��Ʒ��Ŷ�λ��ѯ
		public ArrayList<shopcarBean> selectBypid(String pid) throws IOException, SQLException, ClassNotFoundException    			
		{ 	
			connectDatabase con=new connectDatabase();
	    	Statement stat=null;
		    ResultSet rs=null;
			stat=con.getConnection().createStatement();
	    	ArrayList<shopcarBean> result=new ArrayList<shopcarBean>();
	    	 rs=stat.executeQuery("select * from shopcar where pid='"+pid+"'"); 
		    	while(rs.next())
		        {
		    		shopcarBean car=new shopcarBean();
		        	car.setPid(rs.getString("pid"));
		        	System.out.println(car.getPid());
		        	car.setUid(rs.getString("uid"));
		        	System.out.println(car.getUid());
		        	car.setPname(rs.getString("pname"));
		        	System.out.println(car.getPname());
		        	car.setPnum(rs.getString("pnum"));
		        	System.out.println(car.getPnum());
		        	result.add(car);
		        }
			    if(rs!=null){
			    	  rs.close();
			       }
			con.closeAll(rs, stat, con.getConnection());
	    	return result;
		}
		//ɾ�����ﳵ
	    public void deletecar(HttpServletRequest request, HttpServletResponse response)throws ClassNotFoundException, SQLException, ServletException, IOException
	    {
	      if(!selectBypid(request.getParameter("pid")).isEmpty()) 
	       {
	    	  connectDatabase con=new connectDatabase();
		    	Statement stat=null;
			    ResultSet rs=null;
				stat=con.getConnection().createStatement();
				stat.execute("delete from shopcar where pid='"+request.getParameter("pid")+"'");
	    		request.setAttribute("result", selectBypid(request.getParameter("pid")));
	            request.getRequestDispatcher("/shopping/delete_ok.jsp?id="+request.getParameter("id")).forward(request, response);	
	        }
	    }
	    //���ﳵ������Ӷ���
	    public void insertIntoOrders(HttpServletRequest request, HttpServletResponse response)throws ClassNotFoundException, SQLException, ServletException, IOException
	    {
	    	   shopcarBean result0=new shopcarBean();
	    	   connectDatabase con=new connectDatabase();
		    	Statement stat=null;
			    ResultSet rs=null;
			    ResultSet rs1=null;
			    float price = 0;
				stat=con.getConnection().createStatement();
	    	if(!selectBypid(request.getParameter("pid")).isEmpty()) 
		       {
					result0=selectBypid(request.getParameter("pid")).get(0);
					rs1=stat.executeQuery("select price from products where pid='"+request.getParameter("pid")+"'");					
					String o_pid=result0.getPid();
					String o_uid=result0.getUid();
					String pcount=result0.getPnum();
			    	while(rs1.next())
					price=rs1.getFloat("price");
					SimpleDateFormat est_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					Date date = new Date(System.currentTimeMillis());
			        System.out.println(est_time.format(date));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			        Random random=new Random();
			        StringBuffer sb=new StringBuffer();
			        for(int i=0;i<17;i++)
			        {
			          int number=random.nextInt(62);
			          sb.append(str.charAt(number));
			        }//��������ַ�����Ϊ�������
					stat.execute("insert into orders (o_pid,o_uid,oid,price,pcount,discount,est_time,fin_time,ret_time,state) value ('"+o_pid+"','"+o_uid+"','"+sb.toString()+"','"+price+"','"+pcount+"',null,'"+est_time.format(date)+"',null,null,'���µ�')");
		    		//��Ӷ���
						stat.execute("delete from shopcar where pid='"+request.getParameter("pid")+"'");//ɾ�����ﳵ
			    		request.setAttribute("result", selectBypid(request.getParameter("pid")));
		            request.getRequestDispatcher("/shopping/buy_ok.jsp?id="+request.getParameter("id")).forward(request, response);	
		        }
	    }
	    public void updateShopCar(HttpServletRequest request, HttpServletResponse response)throws ClassNotFoundException, SQLException, ServletException, IOException
	    {
	    	if(request.getParameter("pid")!=""&&request.getParameter("pnum")!=""&&!selectBypid(request.getParameter("pid")).isEmpty()) 
		       {
		    	    connectDatabase con=new connectDatabase();
			    	Statement stat=null;
				    ResultSet rs=null;
					stat=con.getConnection().createStatement();
					stat.execute("update shopcar set pnum='"+request.getParameter("pnum")+"' where pid='"+request.getParameter("pid")+"'");
					//stat.execute("delete from shopcar where pid='"+request.getParameter("pid")+"'");
		    		request.setAttribute("result", selectBypid(request.getParameter("pid")));
		            request.getRequestDispatcher("/shopping/updateNum_ok.jsp?id="+request.getParameter("id")).forward(request, response);	
		        }
	    	else{
	    		request.getRequestDispatcher("/shopping/updateNum_error.jsp?id="+request.getParameter("id")).forward(request, response);
	    	}
	    }
	    //����Ʒ���ݿⰴ�ձ�Ų���
	    public ArrayList<product> selectProductsBypid(String pid) throws IOException, SQLException, ClassNotFoundException    			
		{ 
	    	connectDatabase con=new connectDatabase();
	    	Statement stat=null;
		    ResultSet rs=null;
			stat=con.getConnection().createStatement();
	    	ArrayList<product> result=new ArrayList<product>();
	    	 rs=stat.executeQuery("select * from products where pid='"+pid+"'"); 
		    	while(rs.next())
		        {
		    		product pro=new product();
		        	pro.setPid(rs.getString("pid"));
		        	pro.setPname(rs.getString("pname"));
		        	pro.setPsort(rs.getString("sort"));
		        	pro.setBrand(rs.getString("brand"));
		        	pro.setPrice(rs.getFloat("price"));
		        	pro.setLiked(rs.getInt("liked"));
		        	pro.setDiscount(rs.getFloat("discount"));		        
		        	result.add(pro);
		        }
			    if(rs!=null){
			    	  rs.close();
			       }
			con.closeAll(rs, stat, con.getConnection());
	    	return result;
		}
	    //��ӹ��ﳵ����Ҫ�����û���id
	    public void addShopCar(HttpServletRequest request, HttpServletResponse response,String uid) throws Exception, IOException, Exception
		{
	    	   product result0=new product();
	    	   connectDatabase con=new connectDatabase();
		    	Statement stat=null;
				stat=con.getConnection().createStatement();
	    	if(!selectProductsBypid(request.getParameter("pid")).isEmpty()) 
		       {
					result0=selectProductsBypid(request.getParameter("pid")).get(0);					
					String pname=result0.getPname();
					String pnum=request.getParameter("pnum");
			    	if(result0!=null)
					stat.execute("insert into shopcar value ('"+request.getParameter("pid")+"','"+uid+"','"+pname+"','"+pnum+"')");
			    	//request.setAttribute("result", selectBypid(request.getParameter("pid")));
		            request.getRequestDispatcher("/shopping/index.jsp?id="+request.getParameter("id")).forward(request, response);//	����������ת���ĸ�����ȥ
		        }		       
		}
	    //ֱ����Ʒ���湺��
	    public void buyProducts(HttpServletRequest request, HttpServletResponse response,String uid) throws Exception, IOException, Exception
		{
	    	   product result0=new product();
	    	   connectDatabase con=new connectDatabase();
		    	Statement stat=null;
			    ResultSet rs1=null;
			    float price = 0;
				stat=con.getConnection().createStatement();
	    	if(!selectProductsBypid(request.getParameter("pid")).isEmpty()) 
		       {
					result0=selectProductsBypid(request.getParameter("pid")).get(0);
					rs1=stat.executeQuery("select price from products where pid='"+request.getParameter("pid")+"'");
					while(rs1.next())
					price=rs1.getFloat("price");
					String o_pid=result0.getPid();
					String o_uid=uid;
					String pcount=request.getParameter("pnum");
					SimpleDateFormat est_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					Date date = new Date(System.currentTimeMillis());
			        System.out.println(est_time.format(date));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			        Random random=new Random();
			        StringBuffer sb=new StringBuffer();
			        for(int i=0;i<17;i++)
			        {
			          int number=random.nextInt(62);
			          sb.append(str.charAt(number));
			        }//��������ַ�����Ϊ�������
					stat.execute("insert into orders (o_pid,o_uid,oid,price,pcount,discount,est_time,fin_time,ret_time,state) value ('"+o_pid+"','"+o_uid+"','"+sb.toString()+"','"+price+"','"+pcount+"',null,'"+est_time.format(date)+"',null,null,'���µ�')");
		    		//��Ӷ���
			    	//request.setAttribute("result", selectBypid(request.getParameter("pid")));
		            request.getRequestDispatcher("/shopping/buy_ok.jsp?id="+request.getParameter("id")).forward(request, response);	//	����������ת���ĸ�����ȥ
		        }
		}
	    //�鿴�ղؼ�	    
		public ArrayList<liked> SearchLiked(String uid) throws Exception			
		{ 	
			connectDatabase con=new connectDatabase();
	    	Statement stat=null;
		    ResultSet rs=null;
			stat=con.getConnection().createStatement();
	    	ArrayList<liked> result=new ArrayList<liked>();
	    	 rs=stat.executeQuery("select * from liked where uid="+uid); 
	    	while(rs.next())
	        {
	    		liked lik=new liked();
	        	lik.setPid_I(rs.getString("pid_I"));
	        	lik.setUid_I(rs.getString("uid_I"));
	        	lik.setPname_I(rs.getString("pname_I"));
	        	result.add(lik);
	        }
		    if(rs!=null){
		    	  rs.close();
		       }
		    con.closeAll(rs, stat, con.getConnection());
	    	return result;
		}
		//��ת����һҳ��ʾ���ҵ����ղ���Ϣ
		public void dispatchLiked(HttpServletRequest request, HttpServletResponse response,String uid) throws Exception, IOException, Exception
		{
			  if(!SearchLiked(uid).isEmpty()){
				 request.setAttribute("result", SearchLiked(uid));
				request.getRequestDispatcher("/shopping/LikedSelect.jsp?id="+request.getParameter("id")).forward(request, response);
			  }
			  else
				  request.getRequestDispatcher("/shopping/LikedIsEmpty.jsp?id="+request.getParameter("id")).forward(request, response);
		}
	    //����pid_I��λ���ղؼ���ĳ����Ʒ
		public ArrayList<liked> selectLikedBypid(String pid_I) throws IOException, SQLException, ClassNotFoundException    			
		{ 	
			connectDatabase con=new connectDatabase();
	    	Statement stat=null;
		    ResultSet rs=null;
			stat=con.getConnection().createStatement();
	    	ArrayList<liked> result=new ArrayList<liked>();
	    	 rs=stat.executeQuery("select * from liked where pid='"+pid_I+"'"); 
		    	while(rs.next())
		        {
		    		liked lik=new liked();
		        	lik.setPid_I(rs.getString("pid_I"));
		        	lik.setUid_I(rs.getString("uid_I"));
		        	lik.setPname_I(rs.getString("pname_I"));
		        	result.add(lik);
		        }
			    if(rs!=null){
			    	  rs.close();
			       }
			con.closeAll(rs, stat, con.getConnection());
	    	return result;
		}	    
	    //����ղ�
	    public void insertIntoLiked(HttpServletRequest request, HttpServletResponse response)throws Exception, IOException, Exception
	    {
	    	   product result0=new product();
	    	   connectDatabase con=new connectDatabase();
	    	   String uid=request.getParameter("uid");
		    	Statement stat=null;
				stat=con.getConnection().createStatement();
	    	if(!selectProductsBypid(request.getParameter("pid")).isEmpty()) 
		       {
					result0=selectProductsBypid(request.getParameter("pid_I"+ "")).get(0);					
					String pname=result0.getPname();
			    	while(result0!=null)
					stat.execute("insert into liked value ('"+request.getParameter("pid_I")+"','"+uid+"','"+pname+"')");
			    	request.setAttribute("result", selectLikedBypid(request.getParameter("pid_I")));
		            request.getRequestDispatcher("/shopping/insertIntoLiked_ok.jsp").forward(request, response);//	����������ת���ĸ�����ȥ
		        }	
	    	
	    }
	    //ȡ���ղ�
	    public void deleteLiked(HttpServletRequest request, HttpServletResponse response)throws ClassNotFoundException, SQLException, ServletException, IOException
	    {
	      if(!selectLikedBypid(request.getParameter("pid_I")).isEmpty()) 
	       {
	    	  connectDatabase con=new connectDatabase();
		    	Statement stat=null;
				stat=con.getConnection().createStatement();
				stat.execute("delete from liked where pid_I='"+request.getParameter("pid_I")+"'");
	    		request.setAttribute("result", selectLikedBypid(request.getParameter("pid_I")));
	            request.getRequestDispatcher("/shopping/deleteLiked_ok.jsp").forward(request, response);	
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	}
