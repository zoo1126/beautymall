package JDBC;

import java.sql.*;


public class connectDatabase {
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
			conn=(Connection) DriverManager.getConnection(URL,USERNAME,PWD);
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
		

}