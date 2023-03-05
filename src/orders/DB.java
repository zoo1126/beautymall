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
	/////////////////////�������ݿ�////////////////
	public Connection lianjie() {
		DB db=new DB();//ʵ����һ������	
		Connection conn=null;
		try {		
		conn=db.getConnection();//�������ݿ�		
		System.out.println("�������ݿ�ɹ���");		
		} catch (SQLException e) {		
				System.out.println("�������ݿⲻ�ɹ�");
		e.printStackTrace();		
		}	
		return conn;
		}
	///////////////////�������ݿ����//////////////////
	
	
	/////////////////////////�ر����ݿ���Դ//////////////////////
		public void close(Statement stat,Connection conn) throws SQLException
		{
			if(stat!=null){
		    	   stat.close();
		    }
		    if(conn!=null){
		    	   conn.close();
		    }
		}
		/////////////////////////�ر����ݿ���Դ����//////////////////////
		
		
		/////////////��ȡϵͳʱ��////////////
		public SimpleDateFormat time() {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df;
		} 
		/////////////////��ȡϵͳʱ�����///////////////////////////////////
		
		
		/////////////////////////�û���¼/////////////////
		
		public String u_log(String id,String password) throws SQLException {
			lianjie();
			Statement  stmt1=null;
			ResultSet  rs1=null;
			stmt1=lianjie().createStatement();
			String sql1="select upassword from users where uid='"+id+"'";
			rs1=stmt1.executeQuery(sql1);	   //��ѯ�˺��Ƿ����
			if(rs1.next()) {
				if(password.contentEquals(rs1.getString(1))){
					//��ת����Ʒ����
					System.out.println("��ҳ��");
					return "main_page";
				}
				else {
					//��ת�����������棨ҳ�������������룩
					System.out.println("��������");
					return "wrong_password";
				}
			}
			else {
				//��ת���˺Ų�����ҳ�棨ҳ�����з��غ�ע�ᣩ
				System.out.println("�˺Ų�����");
				return "no_page";
			}
		}
		
		/////////////////////�û���¼����////////////////////
		
		
		///////////////����Ա��¼//////////////////
		public String a_log(String id,String password) throws SQLException {
			lianjie();
			Statement  stmt2=null;
			ResultSet  rs2=null;
			stmt2=lianjie().createStatement();
			String sql2="select apassword from admins where aid='"+id+"'";
			rs2=stmt2.executeQuery(sql2);	   //��ѯ�˺��Ƿ����
			if(rs2.next()) {
				if(password.contentEquals(rs2.getString(1))){
					//��ת����Ʒ����
					System.out.println("��ҳ��");
					return "a_main_page";
				}
				else {
					//��ת�����������棨ҳ�������������룩
					System.out.println("��������");
					return "wrong_password";
				}
			}
			else {
				//��ת���˺Ų�����ҳ�棨ҳ�����з��غ�ע�ᣩ
				System.out.println("�˺Ų�����");
				return "no_page";
			}
		}        		
		////////////////����Ա��¼����/////////////////////
		
		///////////////�û�ע��//////////////////
		public String u_reg(String id,String name,String num,String password) throws SQLException              //��Ҫ����ȦȦ�����������Ĳ���
		                        //�û�ע��
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
				//��ת���˺��Ѵ��ڽ���
				System.out.println("�˺Ŵ��ڣ�id�ظ���");
				return "id_already_have";
				
			}
			else {
				if(rs11.next()||rs1.next())
				{
					//�˺��Ѵ���
					System.out.println("�˺Ŵ��ڣ��绰���ظ���");
					return "tel_already_have";
				}
				else {
				Statement stmt4=null;
				//ResultSet rs4=null;
				stmt4=lianjie().createStatement();
				String sql4="insert into users value('"+id+"','"+name+"','"+num+"','"+password+"',0)";
				stmt4.execute(sql4);
				//��ת��ע��ɹ����� ��ť ��¼����
				System.out.println("ע��ɹ�");
				return "reg_ok";
				}
			} 
		}
		
		///////////////�û�ע�����//////////////////
		
		
		
		///////////////����Աע��///////////////
		public String a_reg(String id,String name,String num,String password,String state) throws SQLException              //��Ҫ����ȦȦ�����������Ĳ���
	    //�û�ע��
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
	//��ת���˺��Ѵ��ڽ���
				System.out.println("�˺��Ѵ��ڣ�id�ظ���");
				return "id_already_have";
				}
			else {		
				if(rs12.next()||rs.next()) {
					//�Ѵ���
					System.out.println("�˺��Ѵ��ڣ��绰���ظ���");
					return "tel_already_have";
				}
				Statement stmt6=null;
				ResultSet rs6=null;
				String sql6="insert into admins values('"+id+"'"+"'"+name+"'"+"'"+num+"'"+"'"+password+"'"+"'"+state+"')";
				rs6=stmt6.executeQuery(sql6);
				//��ת��ע��ɹ����� ��ť ��¼����
				System.out.println("ע��ɹ�");
				return "reg_ok";

				}
			}
		
		////////////////����Աע�����//////////////

		
		
		
			
			
			////////////�û���ѯ����/////////////////////
			
			public ArrayList<order> u_search (String o_uids) throws SQLException{   //��ѯ����
				lianjie();
				Statement  stmt7=null;
				ResultSet  rs7=null;
				stmt7=lianjie().createStatement();
				String sql7="select * from orders where o_uid='"+o_uids+"'"+" order by est_time";
				//����id���û������ж�����Ϣ���
				//System.out.println("������Ϣ");
				ArrayList<order> orders=new ArrayList<order>();
				rs7=stmt7.executeQuery(sql7);
				if(rs7!=null)
				{while(rs7.next()) {
				    //��¼������
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
					//ÿһ�к����һ�������˿ť
				return orders;
				}
				else
					return null;
			}
				
			//////////////�û���ѯ��������/////////////////

			
			/////////////////�û������˿�///////////////
			public String u_up (String o_ids) throws SQLException{   
				lianjie();
				
				//�����ť ��ȡ������Ϣ ��o_id���س���
				
				Statement  stmt8=null;
				ResultSet  rs8=null;
				stmt8=lianjie().createStatement();
				SimpleDateFormat est_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				Date date = new Date(System.currentTimeMillis());
				String sql8="update orders set state='�����˿�',ret_time='"+est_time.format(date)+"' where oid='"+o_ids+"'";
				//���¶���״̬��res time
				int i=stmt8.executeUpdate(sql8);
				if(i>0) {
					System.out.println("�����˿�ɹ�");
					return"app_succ";
				}
				else
					return "fail";
				
			}
			
		
		//////////////////////�û������˿����///////////////////
			
			
			//////////////////����Ա��ѯ����//////////////////
		
			public ArrayList<order> a_search () throws SQLException{   //��ѯ����
				lianjie();
				Statement  stmt7=null;
				ResultSet  rs7=null;
				stmt7=lianjie().createStatement();
				String sql7="select * from orders where state='�����˿�' order by est_time";
				//����id���û������ж�����Ϣ���
				//System.out.println("������Ϣ");
				ArrayList<order> orders=new ArrayList<order>();
				rs7=stmt7.executeQuery(sql7);
				if(rs7!=null)
				{while(rs7.next()) {
				    //��¼������
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
					//ÿһ�к����һ�������˿ť
				return orders;
				}
				else
					return null;
			}
			/////////////����Ա��ѯ��������//////////////
			
			
			///////////////����Աͬ���˿�/////////////////
			public String a_up (String o_ids) throws SQLException{
				lianjie();                                         //�����ť ���ض�Ӧ������
				Statement  stmt10=null;
				ResultSet  rs10=null;
				stmt10=lianjie().createStatement();
				SimpleDateFormat fin_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				Date date = new Date(System.currentTimeMillis());
				String sql10="update orders set state='���˿�',fin_time='"+fin_time.format(date)+"' where oid='"+o_ids+"'";
				//���¶���״̬��fin time
				int i=stmt10.executeUpdate(sql10);
				if(i>0)
					{System.out.println("ͬ���˿�");
				return "refund_ok";}
				else
					return "fail";
			}
		/////////////����Աͬ���˿����//////////////////
			
			
		/////////////�û��޸���Ϣ/////////////////////
			public void u_change(String uid,String name,String num,String password) throws SQLException {
				lianjie();
				Statement  stmt13=null;
				ResultSet  rs13=null;
				stmt13=lianjie().createStatement();
				String sql13="update users set uname='"+name+"',utel='"+num+"',upassword='"+password+"' where uid='"+uid+"'";
				//���¶���״̬��res time
			    stmt13.execute(sql13);
				System.out.println("�޸ĳɹ�aaa");
			}
			/////////////////�û��޸���Ϣ����///////////////
		

			
		/////////////����Ա�޸���Ϣ/////////////////////
			public void a_change(String aid,String name,String num,String password) throws SQLException {
				lianjie();
				Statement  stmt14=null;
				ResultSet  rs14=null;
				stmt14=lianjie().createStatement();
				String sql14="update admins set aname='"+name+"',atel='"+num+"',apassword='"+password+"' where aid='"+aid+"'";
				//���¶���״̬��res time
				stmt14.execute(sql14);
				System.out.println("�޸ĳɹ�");
			}
		/////////////////����Ա�޸���Ϣ����///////////////
			
			/////////////����Ψһ//////////////
			public String weiyi_users(String num) throws SQLException 
			{
			
				lianjie();
				Statement  stmt15=null;
				ResultSet  rs15=null;
				stmt15=lianjie().createStatement();
				String sql15="select utel from users where utel='"+num+"'";
				//���¶���״̬��res time
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
				//���¶���״̬��res time
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
			///////////����Ψһ����////////////
			
			
			
		}

		
		
		

