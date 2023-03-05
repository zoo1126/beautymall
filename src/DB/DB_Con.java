package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Con {

	private static final String driver="com.mysql.jdbc.Driver";
	private static final String DBurl="jdbc:mysql://localhost:3306/facemall";
	private static final String user="root";
	private static final String passwd="root";
	private Connection conn=null;

	public DB_Con() {
		// TODO Auto-generated constructor stub
		try{		
			Class.forName(driver);
			conn = DriverManager.getConnection(DBurl,user,passwd);		
		
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("无法同数据库建立连接！");
		}
	}
	public void releasecon() throws SQLException
	{
		conn.close();
	}
	public Connection getCon()
	{
		return conn;
	}
}
