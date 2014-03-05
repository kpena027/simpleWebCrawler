package simplewebcrawler;
import java.sql.*;

 
public class DB {
 
	public Connection conn = null;
 
	public DB() {
		try {
                        // Load driver
			Class.forName("com.mysql.jdbc.Driver");
                        System.out.println("Driver loaded.");
                        // Establish connection, set user/pw
			String url = "jdbc:mysql://localhost:3306/Crawler";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Connection established.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
        // Run 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
        // Closes connection
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}