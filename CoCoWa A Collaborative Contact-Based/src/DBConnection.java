import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	static Connection con;

	static Connection getConnection() {

		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};Dbq=src\\Selfish.mdb");
			

		} catch (Exception ex) {

			System.out.println(ex);
		}

		return con;

	}

}
