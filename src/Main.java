import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		MyDataBase db = new MyDataBase();
		db.addIntoDB("�����", "���������");
		db.addIntoDB("��������", "������");
		
		List<String> all = db.getAllFromDB();
		for(String s : all) {
			System.out.println(s);
		}
	}
}
