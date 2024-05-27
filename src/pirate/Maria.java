package pirate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Maria {
	
	String driver = "org.mariadb.jdbc.Driver";
	String address = "jdbc:mariadb://localhost:3306/study";
	String user = "root";
	String password = "1q2w3e4r";
	
	public void access() {
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(address, user, password);
			
			String sql1 = "create or replace table onepiece "
					+ "("
					+ "no int, "
					+ "name varchar(50), "
					+ "sex varchar(10), "
					+ "charactericstic varchar(50), "
					+ "party varchar(50), "
					+ "role varchar(50)"
					+ ")";
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.execute();
			
			String[] name = new String[]{ "몽키 D 루피", "롤로노아 조로", "상디", "나미", "우솝", "토니토니 쵸파", "니코 로빈", "프랑키", "브룩"};
			String[] sex = new String[] {"남자", "남자", "남자", "여자", "남자", "미정", "여자", "남자", "남자"};
			String[] characteristic = new String[] {"고무고무 열매", "삼도류", "요리사", "항해사", "사격", "사람사람 열매", "꽃꽃 열매", "사이보그", "연주자"};
			String[] party = new String[] {"밀집모자","밀집모자","밀집모자","밀집모자","밀집모자","밀집모자","밀집모자","밀집모자","밀집모자"};
			String[] role = new String[] {"선장", "부선장", "요리사", "항해사", "저격수", "의사", "고고학자", "조선공", "선원"};
			
			String sql2 = "";
			for(int i = 0; i < name.length; i++) {
				sql2 = "insert into onepiece value "
						+ "("
						+ (i + 1) + ", '"
						+ name[i] + "', '"
						+ sex[i] + "', '"
						+ characteristic[i] + "', '"
						+ party[i] + "', '"
						+ role[i]
						+ "')";
				ps = conn.prepareStatement(sql2);
				ps.executeUpdate();
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
