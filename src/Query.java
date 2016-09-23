
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Query {
	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	public static void main(String[] args) {
		
	}

	public static ResultSet queryDB(String sql) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public static void displayResultset() {
		int numberOfColumns=0;
		try {
			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				numberOfColumns = rsmd.getColumnCount();
				rsmd.getColumnName(numberOfColumns);
				for (int i = 1; i < numberOfColumns; i++) {
					System.out.print(rsmd.getColumnName(i) + "\t");
				}
				System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------");
			} catch (Exception e) {
			} 
			while (rs.next()) {
				for(int i=1;i<=numberOfColumns;i++)
				System.out.print(rs.getString(i)+"\t");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void runQuery(String input) {
		input = input.split(";")[0];
		queryDB(input);
		displayResultset();
		close();

	}

	public static void close() {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
