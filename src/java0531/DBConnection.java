package java0531;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection openDB(String URL, String USER, String PASSWORD) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("JDBC Connection Open!");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean 테이블생성(Connection conn, String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public List<DbTable> 데이터가져오기(Connection conn, String sql) {
		List<DbTable> list = new ArrayList<DbTable>();
		DbTable data;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				data = new DbTable();
				
				// 데이터를 매핑하시오.
				data.setNo(rs.getInt(1));
				data.setName(rs.getString(2));
				data.setBirthday(rs.getString(3));
				data.setMbti(rs.getString(4));
				data.setBloodType(rs.getString(5));
				data.setDelYn(rs.getBoolean(6));

				list.add(data);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean 데이터입력하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);

			// 데이터를 매핑하시오.
			pstmt.setInt(1, data.getNo());
			pstmt.setString(2, data.getName());
			pstmt.setString(3, data.getBirthday());
			pstmt.setString(4, data.getMbti());
			pstmt.setString(5, data.getBloodType());
			pstmt.setBoolean(6, data.isDelYn());			
			
			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean 데이터수정하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 데이터를 매핑하시오.
			int n = 1;
			if(data.getName() != null) {
				pstmt.setString(n, data.getName());
				n++;
			}
			if(data.getBirthday() != null) {
				pstmt.setString(n, data.getBirthday());
				n++;
			}
			if(data.getMbti() != null) {
				pstmt.setString(n, data.getMbti());
				n++;
			}
			if(data.getBloodType() != null) {
				pstmt.setString(n, data.getBloodType());
				n++;
			}			
			pstmt.setInt(n, data.getNo());

			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean 데이터삭제하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);

			// 데이터를 매핑하시오.
			pstmt.setInt(1, data.getNo());
			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
