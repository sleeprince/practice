package pirate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java0527.TestDTO;

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
			
			String[][] crew = new String[][]
				{ 
				{"몽키 D. 루피", "남자", "고무고무 열매", "밀짚모자", "선장"},
				{"롤로노아 조로", "남자", "삼도류", "밀짚모자", "부선장"},
				{"상디", "남자", "요리사", "밀짚모자", "요리사"},
				{"나미", "여자", "항해사", "밀짚모자", "항해사"},
				{"우솝", "남자", "사격", "밀짚모자", "저격수"},
				{"토니토니 쵸파", "미정", "사람사람 열매", "밀짚모자", "의사"},
				{"니코 로빈", "여자", "꽃꽃 열매", "밀짚모자", "고고학자"},
				{"프랑키", "남자", "사이보그", "밀짚모자", "조선공"},
				{"브룩", "남자", "연주자", "밀짚모자", "선원"}
				};
							
			String sql2 = "";
			for(int i = 0; i < crew.length; i++) {
				sql2 = "insert into onepiece value (" + (i + 1) + ", ";
				for(int j = 0; j < crew[i].length; j++) {
					sql2 += "\'" + crew[i][j] + "\'";
					if(j != crew[i].length - 1) {
						sql2 += ", ";
					}else {
						sql2 += ")";
					}					
				}
				ps = conn.prepareStatement(sql2);
				ps.executeUpdate();
			}
			
			String sql3 = "select * from onepiece";
			ps = conn.prepareStatement(sql3);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				for(int i = 2; i <= 6; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
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
