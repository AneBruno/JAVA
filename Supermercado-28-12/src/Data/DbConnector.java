package Data;

import java.sql.*;

public class DbConnector {

	private static DbConnector instancia;
	
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="root";
	private String db="tp_java";
	private int conectados=0;
	private Connection conn=null;
	
	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				//conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conn=DriverManager.getConnection("jdbc:mysql://" +host+":"+port+"/"+ db, user, password);
				conectados=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}