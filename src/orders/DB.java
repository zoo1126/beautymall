package orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DB {
 	public final static String URL="jdbc:mysql://localhost:3306/facemall";
	public final static String Driver="com.mysql.jdbc.Driver";
	public final static String USERNAME="root";
	public final static String PWD="root";
	static {
		try {
			Class.forName(Driver);
			System.out.println("success");
		}catch(ClassNotFoundException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		conn=DriverManager.getConnection(URL,USERNAME,PWD);
		return conn;
	}
	public void closeAll(ResultSet rst,Statement stmt,Connection conn){	
		if(rst!=null){		
			try {			
				rst.close();			
				} catch (SQLException e) {			
					e.printStackTrace();		
					}	
			}		
		if(stmt!=null){			
			try {			
				stmt.close();	
				} catch (SQLException e) {			
					e.printStackTrace();		
					}	
			}		
		if(conn!=null){		
			try {			
				conn.close();	
				} catch (SQLException e) {	
					e.printStackTrace();		
					}	
			}		
		}
	/////////////////////连接数据库////////////////
	public Connection lianjie() {
		DB db=new DB();//实例化一个对象	
		Connection conn=null;
		try {		
		conn=db.getConnection();//连接数据库		
		System.out.println("连接数据库成功！");		
		} catch (SQLException e) {		
				System.out.println("连接数据库不成功");
		e.printStackTrace();		
		}	
		return conn;
		}
	///////////////////连接数据库结束//////////////////
	
	
	/////////////////////////关闭数据库资源//////////////////////
		public void close(Statement stat,Connection conn) throws SQLException
		{
			if(stat!=null){
		    	   stat.close();
		    }
		    if(conn!=null){
		    	   conn.close();
		    }
		}
		/////////////////////////关闭数据库资源结束//////////////////////
		
		
		/////////////获取系统时间////////////
		public SimpleDateFormat time() {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df;
		} 
		/////////////////获取系统时间结束///////////////////////////////////
		
		
		/////////////////////////用户登录/////////////////
		
		public String u_log(String id,String password) throws SQLException {
			lianjie();
			Statement  stmt1=null;
			ResultSet  rs1=null;
			stmt1=lianjie().createStatement();
			String sql1="select upassword from users where uid='"+id+"'";
			rs1=stmt1.executeQuery(sql1);	   //查询账号是否存在
			if(rs1.next()) {
				if(password.contentEquals(rs1.getString(1))){
					//跳转到商品界面
					System.out.println("主页辽");
					return "main_page";
				}
				else {
					//跳转到密码错误界面（页面中有重新输入）
					System.out.println("密码有误");
					return "wrong_password";
				}
			}
			else {
				//跳转到账号不存在页面（页面中有返回和注册）
				System.out.println("账号不存在");
				return "no_page";
			}
		}
		
		/////////////////////用户登录结束////////////////////
		
		
		///////////////管理员登录//////////////////
		public String a_log(String id,String password) throws SQLException {
			lianjie();
			Statement  stmt2=null;
			ResultSet  rs2=null;
			stmt2=lianjie().createStatement();
			String sql2="select apassword from admins where aid='"+id+"'";
			rs2=stmt2.executeQuery(sql2);	   //查询账号是否存在
			if(rs2.next()) {
				if(password.contentEquals(rs2.getString(1))){
					//跳转到商品界面
					System.out.println("主页辽");
					return "a_main_page";
				}
				else {
					//跳转到密码错误界面（页面中有重新输入）
					System.out.println("密码有误");
					return "wrong_password";
				}
			}
			else {
				//跳转到账号不存在页面（页面中有返回和注册）
				System.out.println("账号不存在");
				return "no_page";
			}
		}        		
		////////////////管理员登录结束/////////////////////
		
		///////////////用户注册//////////////////
		public String u_reg(String id,String name,String num,String password) throws SQLException              //需要对照圈圈函数传出来的参数
		                        //用户注册
		{
			lianjie();
			Statement  stmt3=null;
			ResultSet  rs3=null;
			stmt3=lianjie().createStatement();
			String sql3="select * from users where uid='"+id+"'";
			rs3=stmt3.executeQuery(sql3);	
			Statement  stmt11=null;
			ResultSet  rs11=null;
			stmt11=lianjie().createStatement();
			String sql11="select * from users where utel='"+num+"'";
			rs11=stmt11.executeQuery(sql11);
			
			Statement  stmt=null;
			ResultSet  rs=null;
			stmt=lianjie().createStatement();
			String sql="select * from admins where aid='"+id+"'";
			rs=stmt.executeQuery(sql);				
			Statement  stmt1=null;
			ResultSet  rs1=null;
			stmt1=lianjie().createStatement();
			String sql1="select * from admins where atel='"+num+"'";
			rs1=stmt1.executeQuery(sql1);
			if(rs3.next()||rs.next()) {
				//跳转到账号已存在界面
				System.out.println("账号存在（id重复）");
				return "id_already_have";
				
			}
			else {
				if(rs11.next()||rs1.next())
				{
					//账号已存在
					System.out.println("账号存在（电话号重复）");
					return "tel_already_have";
				}
				else {
				Statement stmt4=null;
				//ResultSet rs4=null;
				stmt4=lianjie().createStatement();
				String sql4="insert into users value('"+id+"','"+name+"','"+num+"','"+password+"',0)";
				stmt4.execute(sql4);
				//跳转到注册成功界面 按钮 登录界面
				System.out.println("注册成功");
				return "reg_ok";
				}
			} 
		}
		
		///////////////用户注册结束//////////////////
		
		
		
		///////////////管理员注册///////////////
		public String a_reg(String id,String name,String num,String password,String state) throws SQLException              //需要对照圈圈函数传出来的参数
	    //用户注册
	{

			lianjie();
			Statement  stmt5=null;
			ResultSet  rs5=null;
			stmt5=lianjie().createStatement();
			String sql5="select * from admins where aid='"+id+"'";
			rs5=stmt5.executeQuery(sql5);	
			Statement  stmt12=null;
			ResultSet  rs12=null;
			stmt12=lianjie().createStatement();
			String sql12="select * from admins where atel='"+num+"'";
			rs12=stmt12.executeQuery(sql12);
			Statement  stmt=null;
			ResultSet  rs=null;
			stmt=lianjie().createStatement();
			String sql="select * from users where uid='"+id+"'";
			rs=stmt.executeQuery(sql);				
			Statement  stmt1=null;
			ResultSet  rs1=null;
			stmt1=lianjie().createStatement();
			String sql1="select * from users where utel='"+num+"'";
			rs1=stmt1.executeQuery(sql1);

			if(rs5.next()||rs1.next()) {
	//跳转到账号已存在界面
				System.out.println("账号已存在（id重复）");
				return "id_already_have";
				}
			else {		
				if(rs12.next()||rs.next()) {
					//已存在
					System.out.println("账号已存在（电话号重复）");
					return "tel_already_have";
				}
				Statement stmt6=null;
				ResultSet rs6=null;
				String sql6="insert into admins values('"+id+"'"+"'"+name+"'"+"'"+num+"'"+"'"+password+"'"+"'"+state+"')";
				rs6=stmt6.executeQuery(sql6);
				//跳转到注册成功界面 按钮 登录界面
				System.out.println("注册成功");
				return "reg_ok";

				}
			}
		
		////////////////管理员注册结束//////////////

		
		
		
			
			
			////////////用户查询订单/////////////////////
			
			public ArrayList<order> u_search (String o_uids) throws SQLException{   //查询订单
				lianjie();
				Statement  stmt7=null;
				ResultSet  rs7=null;
				stmt7=lianjie().createStatement();
				String sql7="select * from orders where o_uid='"+o_uids+"'"+" order by est_time";
				//将该id的用户的所有订单信息输出
				//System.out.println("订单信息");
				ArrayList<order> orders=new ArrayList<order>();
				rs7=stmt7.executeQuery(sql7);
				if(rs7!=null)
				{while(rs7.next()) {
				    //记录订单数
					order o=new order();
					o.setO_pid(rs7.getString(1));
					o.setO_uid(rs7.getString(2));
					o.setOid(rs7.getString(3));
					o.setPrice(rs7.getFloat(4));
					o.setPcount(rs7.getInt(5));
					o.setDiscount(rs7.getFloat(6));
					o.setEst_time(rs7.getString(7));
					o.setFin_time(rs7.getString(8));
					o.setRet_time(rs7.getString(9));
					o.setState(rs7.getString(10));
					orders.add(o);
				   }
					//每一行后面加一个申请退款按钮
				return orders;
				}
				else
					return null;
			}
				
			//////////////用户查询订单结束/////////////////

			
			/////////////////用户申请退款///////////////
			public String u_up (String o_ids) throws SQLException{   
				lianjie();
				
				//点击按钮 获取该行信息 将o_id返回出来
				
				Statement  stmt8=null;
				ResultSet  rs8=null;
				stmt8=lianjie().createStatement();
				SimpleDateFormat est_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				Date date = new Date(System.currentTimeMillis());
				String sql8="update orders set state='申请退款',ret_time='"+est_time.format(date)+"' where oid='"+o_ids+"'";
				//更新订单状态和res time
				int i=stmt8.executeUpdate(sql8);
				if(i>0) {
					System.out.println("申请退款成功");
					return"app_succ";
				}
				else
					return "fail";
				
			}
			
		
		//////////////////////用户申请退款结束///////////////////
			
			
			//////////////////管理员查询订单//////////////////
		
			public ArrayList<order> a_search () throws SQLException{   //查询订单
				lianjie();
				Statement  stmt7=null;
				ResultSet  rs7=null;
				stmt7=lianjie().createStatement();
				String sql7="select * from orders where state='申请退款' order by est_time";
				//将该id的用户的所有订单信息输出
				//System.out.println("订单信息");
				ArrayList<order> orders=new ArrayList<order>();
				rs7=stmt7.executeQuery(sql7);
				if(rs7!=null)
				{while(rs7.next()) {
				    //记录订单数
					order o=new order();
					o.setO_pid(rs7.getString(1));
					o.setO_uid(rs7.getString(2));
					o.setOid(rs7.getString(3));

					o.setPrice(rs7.getFloat(4));
					o.setPcount(rs7.getInt(5));
					o.setDiscount(rs7.getFloat(6));
					o.setEst_time(rs7.getString(7));
					o.setFin_time(rs7.getString(8));
					o.setRet_time(rs7.getString(9));
					o.setState(rs7.getString(10));
					orders.add(o);
				   }
					//每一行后面加一个申请退款按钮
				return orders;
				}
				else
					return null;
			}
			/////////////管理员查询订单结束//////////////
			
			
			///////////////管理员同意退款/////////////////
			public String a_up (String o_ids) throws SQLException{
				lianjie();                                         //点击按钮 返回对应订单号
				Statement  stmt10=null;
				ResultSet  rs10=null;
				stmt10=lianjie().createStatement();
				SimpleDateFormat fin_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				Date date = new Date(System.currentTimeMillis());
				String sql10="update orders set state='已退款',fin_time='"+fin_time.format(date)+"' where oid='"+o_ids+"'";
				//更新订单状态和fin time
				int i=stmt10.executeUpdate(sql10);
				if(i>0)
					{System.out.println("同意退款");
				return "refund_ok";}
				else
					return "fail";
			}
		/////////////管理员同意退款结束//////////////////
			
			
		/////////////用户修改信息/////////////////////
			public void u_change(String uid,String name,String num,String password) throws SQLException {
				lianjie();
				Statement  stmt13=null;
				ResultSet  rs13=null;
				stmt13=lianjie().createStatement();
				String sql13="update users set uname='"+name+"',utel='"+num+"',upassword='"+password+"' where uid='"+uid+"'";
				//更新订单状态和res time
			    stmt13.execute(sql13);
				System.out.println("修改成功aaa");
			}
			/////////////////用户修改信息结束///////////////
		

			
		/////////////管理员修改信息/////////////////////
			public void a_change(String aid,String name,String num,String password) throws SQLException {
				lianjie();
				Statement  stmt14=null;
				ResultSet  rs14=null;
				stmt14=lianjie().createStatement();
				String sql14="update admins set aname='"+name+"',atel='"+num+"',apassword='"+password+"' where aid='"+aid+"'";
				//更新订单状态和res time
				stmt14.execute(sql14);
				System.out.println("修改成功");
			}
		/////////////////管理员修改信息结束///////////////
			
			/////////////号码唯一//////////////
			public String weiyi_users(String num) throws SQLException 
			{
			
				lianjie();
				Statement  stmt15=null;
				ResultSet  rs15=null;
				stmt15=lianjie().createStatement();
				String sql15="select utel from users where utel='"+num+"'";
				//更新订单状态和res time
				rs15=stmt15.executeQuery(sql15);
				if(rs15.next())
				{
					System.out.println("weiyi_user");
					return"fail";
				}
				else {
					return"success";
				}
			}
			public String weiyi_admins(String num) throws SQLException 
			{
				lianjie();
				Statement  stmt16=null;
				ResultSet  rs16=null;
				stmt16=lianjie().createStatement();
				String sql16="select atel from admins where atel='"+num+"'";
				//更新订单状态和res time
				rs16=stmt16.executeQuery(sql16);
				if(rs16.next())
				{
					System.out.println("weiyi_admin");
					return"fail";
				}
				else {
					return"success";
				}
				
				
			}
			///////////号码唯一结束////////////
			
			
			
		}

		
		
		

