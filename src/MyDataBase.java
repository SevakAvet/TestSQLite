import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDataBase implements AutoCloseable {
	private static Connection bd;
	private static Statement st;
	private static PreparedStatement prepSt;
	private static ResultSet rs;
	
	static  {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MyDataBase() throws SQLException {
		bd = DriverManager.getConnection("jdbc:sqlite:test.db");
		st = bd.createStatement();
		deleteTable();
		createTable();
	}
	
	private void createTable() throws SQLException {
		st.executeUpdate("create table ЛЮДИ (ИМЯ, ПРОФЕССИЯ);");
		prepSt = bd.prepareStatement("insert into ЛЮДИ values (?, ?);");
	}
	
	public void deleteTable() throws SQLException {
		st.executeUpdate("drop table if exists ЛЮДИ;");
	}

	public void addIntoDB(String name, String occupation) throws SQLException {
		prepSt.setString(1, name);
		prepSt.setString(2, occupation);
		prepSt.addBatch();
		executeBatch();
	}
			
	
	private void executeBatch() throws SQLException {
		bd.setAutoCommit(false);
		prepSt.executeBatch();
		bd.setAutoCommit(true);
	}
	
	public List<String> getAllFromDB() throws SQLException {
		rs = st.executeQuery("select * from ЛЮДИ;");
		
		List<String> sb = new ArrayList<>();
		while (rs.next()) {
			sb.add("name = " + rs.getString("ИМЯ") + "\t\tjob = " + rs.getString("ПРОФЕССИЯ"));
		}
		return sb;
	}
	
	public void close() throws SQLException {
		rs.close();
		bd.close();
	}
}