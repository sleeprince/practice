package java0530;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnector_1 {
	
	private PreparedStatement pdst;
	private ResultSet rs;
	
	public Connection openDB(String _address, String _user, String _password) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("어서 오십시오. 마리아DB입니다.");
			return DriverManager.getConnection(_address, _user, _password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void createTable(String sql, Connection conn) {
		try {
			pdst = conn.prepareStatement(sql);
			pdst.execute();
			pdst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertData(String sql, Connection conn, DBTable_1 data) {
		try {
			pdst = conn.prepareStatement(sql);
			pdst.setInt(1, data.getNo());
			pdst.setString(2, data.getEmail());
			pdst.setString(3, data.getPassword());
			pdst.setBoolean(4, data.isDelYn());
			pdst.executeUpdate();
			pdst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatetData(String sql, Connection conn, DBTable_1 data) {
		try {
			pdst = conn.prepareStatement(sql);
			
			int n = 1;
			if(data.getEmail() != null) {
				pdst.setString(n, data.getEmail());
				n++;
			}
			if(data.getPassword() != null) {
				pdst.setString(n, data.getPassword());
				n++;
			}
			pdst.setInt(n, data.getNo());
			pdst.executeUpdate();
			pdst.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<DBTable_1> readDB(String sql, Connection conn) {
		List<DBTable_1> list = new ArrayList<>();
		try {
			pdst = conn.prepareStatement(sql);
			rs = pdst.executeQuery();
			while(rs.next()){
				DBTable_1 data = new DBTable_1();
				data.setNo(rs.getInt(1));
				data.setEmail(rs.getString(2));
				data.setPassword(rs.getString(3));
				list.add(data);
			}
			rs.close();
			pdst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}
	
}
