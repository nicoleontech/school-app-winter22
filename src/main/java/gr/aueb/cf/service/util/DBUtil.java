package gr.aueb.cf.service.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil {

	private static BasicDataSource ds = new BasicDataSource();
	private static Connection conn;
	
	/**
	 * No instances of this class should be available
	 */
	private DBUtil() {
		
	}
	
	static {
		ds.setUrl("jdbc:mysql://<host>:<port>/<database>");
		ds.setUsername("root");
		ds.setPassword(System.getenv("TS_USER_PASSWORD"));
		ds.setInitialSize(8);
		ds.setMaxTotal(32);
        ds.setMinIdle(8);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
	}
	
	public static Connection getConnection() throws SQLException{
		conn = ds.getConnection();
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if(conn!= null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
