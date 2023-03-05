package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import product.product;

import java.sql.*;

public class DB_Operation {

	DB_Con db;
	Connection con=null;
	
	//登录操作-----管理员  start
	public boolean isAdmin(String aid,String apassword) throws SQLException {
		db=new DB_Con();
		con=db.getCon();
		String url_admin="select apassword from admins where aid=?";
		PreparedStatement pt=con.prepareStatement(url_admin);
		pt.setString(1, aid);
		ResultSet res=pt.executeQuery();
		if(res!=null)
		{
			if(res.next())
			{
				if(apassword.contentEquals(res.getString(1)))
				{
					System.out.print("是");
					return true;
				}
					
			}
		}
		System.out.print("不是");
		return false;
	}
	//登录操作-----管理员  end
	
	
	//登录操作-----用户  start
		public boolean isUser(String uid,String upassword) throws SQLException {
			db=new DB_Con();
			con=db.getCon();
			String url_user="select upassword from users where uid=?";
			PreparedStatement pt=con.prepareStatement(url_user);
			pt.setString(1, uid);
			ResultSet res=pt.executeQuery();
			if(res!=null)
			{
				if(res.next())
				{
					if(upassword.contentEquals(res.getString(1)))
					{
						System.out.print("是");
						return true;
					}
						
				}
			}
			System.out.print("不是");
			return false;
		}
	//登录操作-----用户  end
		
		
	//注册操作-----用户 start
		public boolean adduser(String []arg) throws SQLException{
			db=new DB_Con();
			con=db.getCon();
			String addurl="insert into users values(?,?,?,?,0)";
			PreparedStatement pt=con.prepareStatement(addurl);
			pt.setString(1, arg[0]);//id
			pt.setString(2, arg[1]);//name
			pt.setString(3, arg[2]);//tel
			pt.setString(4, arg[3]);//password
			
			int row=pt.executeUpdate();
			if(row>0) {
				return true;
			}
			return false;
		}
	//注册操作-----用户 end
		
		//注册操作-----用户 start
		public boolean addadmin(String []arg) throws SQLException{
			db=new DB_Con();
			con=db.getCon();
			String addurl="insert into admins values(?,?,?,?,0)";
			PreparedStatement pt=con.prepareStatement(addurl);
			pt.setString(1, arg[0]);//id
			pt.setString(2, arg[1]);//name
			pt.setString(3, arg[2]);//tel
			pt.setString(4, arg[3]);//password
			
			int row=pt.executeUpdate();
			if(row>0) {
				return true;
			}
			return false;
		}
	//注册操作-----用户 end
		//查重操作-----用户  start
 		public boolean isUserexist(String uid) throws SQLException {
			db=new DB_Con();
			con=db.getCon();
			String url_user="select * from users where uid=?";
			PreparedStatement pt=con.prepareStatement(url_user);
			pt.setString(1, uid);
			ResultSet res=pt.executeQuery();
			if(res!=null)
			{
				if(res.next())
				{
						return true;
				}
			}
			return false;
		}
		//查重操作-----用户  end

 		//查重操作-----用户  start
 		public boolean isadminexist(String uid) throws SQLException {
			db=new DB_Con();
			con=db.getCon();
			String url_user="select * from admins where aid=?";
			PreparedStatement pt=con.prepareStatement(url_user);
			pt.setString(1, uid);
			ResultSet res=pt.executeQuery();
			if(res!=null)
			{
				if(res.next())
				{
						return true;
				}
			}
			return false;
		}
		//查重操作-----用户  end
		//查重操作-----用户电话  start
				public boolean isTelexist(String utel) throws SQLException {
					db=new DB_Con();
					con=db.getCon();
					String url_user="select * from users where utel=?";
					PreparedStatement pt=con.prepareStatement(url_user);
					pt.setString(1, utel);
					ResultSet res=pt.executeQuery();
					if(res!=null)
					{
						if(res.next())
						{
					
								return true;
						}
					}
				
					return false;
				}
		//查重操作-----用户电话  end

	//查询产品-----start
	public ArrayList<product> queryproduct(String type,String content,String orderby,String d_or_u) throws SQLException//type是查询的类型，包括分类查询 折扣查询 彩妆查询 工具查询 口红查询，content是具体查询的子类
	{
		db=new DB_Con();
		con=db.getCon();
		PreparedStatement pt;
		Statement st;
		ResultSet res;
		ArrayList<product> products=new ArrayList<product>();
		if("分类".contentEquals(type))
		{
			System.out.println(content);
			String sorturl="select * from products where sort=?";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						sorturl=sorturl+" order by price desc";
					else
						sorturl=sorturl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						sorturl=sorturl+" order by liked desc";
					else
						sorturl=sorturl+" order by liked asc";
				}
			}	
			pt=con.prepareStatement(sorturl);
			pt.setString(1, content);
			res=pt.executeQuery();	
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
				}
			}
			return products;
		}
		else if("折扣".contentEquals(type))//0
		{
			
			String discurl="select * from products where discount is not null";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by price desc";
					else
						discurl=discurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by liked desc";
					else
						discurl=discurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(discurl);
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
				}
			}
			return products;
		}
		else if("彩妆".contentEquals(type))//1
		{
			String discurl="select * from products where sort='脸部' or sort='唇部' or sort='眉部' or sort='眼部' or sort='修容'";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by price desc";
					else
						discurl=discurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by liked desc";
					else
						discurl=discurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(discurl);
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
				}
			}
			return products;
		}
		else if("工具".contentEquals(type))//2
		{
			String discurl="select * from products where sort='美妆工具'";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by price desc";
					else
						discurl=discurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by liked desc";
					else
						discurl=discurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(discurl);
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
				}
			}
			return products;
		}
		else if("口红".contentEquals(type))//3
		{
			String discurl="select * from products where sort='唇部'";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by price desc";
					else
						discurl=discurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						discurl=discurl+" order by liked desc";
					else
						discurl=discurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(discurl);
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
				}
			}
			return products;
		}
		else if("名字".contentEquals(type))
		{
			String nameurl="select * from products where pname like '%"+content+"%'";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						nameurl=nameurl+" order by price desc";
					else
						nameurl=nameurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						nameurl=nameurl+" order by liked desc";
					else
						nameurl=nameurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(nameurl);	
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);
					System.out.println(pro.getPname());
				}
			}
			return products;
		}
		else if("all".contentEquals(type))
		{
			String allurl="select * from products";
			if(orderby!=null)
			{
				if("价格".contentEquals(orderby))
				{
					if("降序".contentEquals(d_or_u))
						allurl=allurl+" order by price desc";
					else
						allurl=allurl+" order by price asc";
				}
				else
				{
					if("降序".contentEquals(d_or_u))
						allurl=allurl+" order by liked desc";
					else
						allurl=allurl+" order by liked asc";
				}
			}	
			st=con.createStatement();
			res=st.executeQuery(allurl);
			System.out.println("here");
			if(res!=null)
			{
				while(res.next())
				{
					product pro=new product();
					pro.setPid(res.getString(1));//id
					pro.setPname(res.getString(2));//name
					pro.setPsort(res.getString(3));//sort
					pro.setBrand(res.getString(4));//brand
					pro.setPrice(res.getFloat(5));//price
					pro.setLiked(res.getInt(6));//liked
					pro.setDiscount(res.getFloat(7));//dis
					products.add(pro);

				}
			}
			return products;
		}
		else
			return null;
	}
	//查询产品-----end
	
	public product idproduct(String pid) throws SQLException
	{
		String inserturl="select * from products where pid=?";
		db=new DB_Con();
		con=db.getCon();
		PreparedStatement st=con.prepareStatement(inserturl);
		st.setString(1, pid);
		ResultSet res=st.executeQuery();
		if(res!=null)
		{
			if(res.next())
		
			{
				product p=new product();
				p.setPid(res.getString(1));
				p.setPname(res.getString(2));
				p.setPsort(res.getString(3));
				p.setBrand(res.getString(4));
				p.setPrice(res.getFloat(5));
				p.setLiked(res.getInt(6));
				p.setDiscount(res.getFloat(7));
				return p;
			}
			else
				return null;
		}
		else
			return null;
	}
	//增加产品-----start
	public boolean addproduct(product pro) throws SQLException
	{
		String inserturl="insert into products values(?,?,?,?,?,0,null)";
		db=new DB_Con();
		con=db.getCon();
		PreparedStatement pt=con.prepareStatement(inserturl);
		pt.setString(1, pro.getPid());
		pt.setString(2, pro.getPname());
		pt.setString(3, pro.getPsort());
		pt.setString(4, pro.getBrand());
		pt.setFloat(5, pro.getPrice());
		int res=pt.executeUpdate();
		if(res<=0)
		{
			
			return false;
		}
		else {
		System.out.println("成功");
		return true;
		}
	}
	//增加产品-----end
	
	
	//删除产品-----start
	public boolean deleteproduct(String pid) throws SQLException
	{
		db=new DB_Con();
		con=db.getCon();
		String delurl="delete from products where pid=?";
		PreparedStatement pt=con.prepareStatement(delurl);
		pt.setString(1, pid);
		int i=pt.executeUpdate();
		if(i<=0)
		{
			return false;
		}
		else {
			System.out.println("成功");
			return true;
		}
		
	}
	//删除产品-----end
	
	
	//修改产品-----start
	public boolean changeproduct(String pid,String pname,String sort,String brand,String price,String discount,String oldpid) throws SQLException
	{
		db=new DB_Con();
		con=db.getCon();
		String changeurl="update products set pid=?,pname=?,sort=?,brand=?,price=?,discount=? where pid=?";
		PreparedStatement pt=con.prepareStatement(changeurl);
		pt.setString(1, pid);
		pt.setString(2, pname);
		pt.setString(3, sort);
		pt.setString(4, brand);
		pt.setString(5, price);
		pt.setString(6,discount);
		pt.setString(7, oldpid);
		int i=pt.executeUpdate();
		if(i<=0)
		{
			System.out.println("失败");
			return false;
		}
		else {
			System.out.println("成功");
			return true;
		}
	}
	//修改产品-----end
	
	//pid查重
	public boolean ispidexist(String pid) throws SQLException
	{
		db=new DB_Con();
		con=db.getCon();
		String url="select * from products where pid=?";
		PreparedStatement st=con.prepareStatement(url);
		st.setString(1, pid);
		ResultSet res=st.executeQuery();
		if(res!=null)
			{
				if(res.next())
					{return true;}
			}
		
		return false;
		
	}
	//pid查重
	
}
