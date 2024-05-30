package java0530;

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
				data.set번호(rs.getInt(1));
				data.set이름(rs.getString(2));
				data.set성별(rs.getString(3));
				data.set특징(rs.getString(4));
				data.set해적단(rs.getString(5));
				data.set역할(rs.getString(6));

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
			pstmt.setInt(1, data.get번호());
			pstmt.setString(2, data.get이름());
			pstmt.setString(3, data.get성별());
			pstmt.setString(4, data.get특징());
			pstmt.setString(5, data.get해적단());
			pstmt.setString(6, data.get역할());			
			
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
			if(data.get이름() != null) {
				pstmt.setString(n, data.get이름());
				n++;
			}
			if(data.get성별() != null) {
				pstmt.setString(n, data.get성별());
				n++;
			}
			if(data.get특징() != null) {
				pstmt.setString(n, data.get특징());
				n++;
			}
			if(data.get해적단() != null) {
				pstmt.setString(n, data.get해적단());
				n++;
			}
			if(data.get역할() != null) {
				pstmt.setString(n, data.get역할());
				n++;
			}
			pstmt.setInt(n, data.get번호());

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
			pstmt.setInt(1, data.get번호());
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
