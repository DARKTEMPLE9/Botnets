import java.sql.Connection;
import java.sql.DriverManager;
/**
 * db connection class
 * @author darktemple9
 *
 */
public class SQLConn {
	public static Connection getSQLConn(String dbName){
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/"+dbName;
		String user = "root";
		String password = "";
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
