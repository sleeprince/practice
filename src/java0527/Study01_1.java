package java0527;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Study01_1 {
	
	String driver = "org.mariadb.jdbc.Driver";
	// 데이터 베이스 접속
	// jdbc > 데어터베이스타입 > 호스트(IP) > 포트 > 데이터베이스명
	//jdbc :oracle:thin: @//[HOST] [:PORT] /SERVICE
	String address = "jdbc:mariadb://localhost:3306/study";
	String user = "root";
	String password = "1q2w3e4r";
	
	
	public void access() {
		System.out.println("접속 시작!");
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(address, user, password);
			
			String sql1 = "create or replace table test (no int, name varchar(50))";
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.execute();
			
			//Insert
			String sql2 = "insert into test (no, name) value (2, 'hi')";
			
			ps = conn.prepareStatement(sql2);
			int result = ps.executeUpdate();
			System.out.println(result);
			
			String sql3 = "select * from test";
			ps = conn.prepareStatement(sql3);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String str = rs.getString("name");
				TestDTO testDto = new TestDTO();
				testDto.setNo(no);
				testDto.setName(str);
				System.out.println(testDto);				
				//System.out.println(no + ", " + str);
			}
			//Update
			String sql4 = "update test set name = 'bye'";
			ps = conn.prepareStatement(sql4);
			result = ps.executeUpdate();
			System.out.println(result);
			
			sql3 = "select * from test";
			ps = conn.prepareStatement(sql3);
			rs = ps.executeQuery();			
			System.out.println("Update");
			while(rs.next()) {
				int no = rs.getInt("no");
				String str = rs.getString("name");
				TestDTO testDto = new TestDTO();
				testDto.setNo(no);
				testDto.setName(str);
				System.out.println(testDto);
				
				//System.out.println(no + ", " + str);
			}
			
			//Delete
			String sql5 = "delete from test";
			ps = conn.prepareStatement(sql5);
			result = ps.executeUpdate();
			System.out.println(result);
			
			sql3 = "select * from test";
			ps = conn.prepareStatement(sql3);
			rs = ps.executeQuery();			
			System.out.println("Delete");
			while(rs.next()) {
				int no = rs.getInt("no");
				String str = rs.getString("name");
				TestDTO testDto = new TestDTO();
				testDto.setNo(no);
				testDto.setName(str);
				System.out.println(testDto);				
				//System.out.println(no + ", " + str);
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
