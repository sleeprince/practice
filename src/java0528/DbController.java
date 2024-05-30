package java0528;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbController {
	
	private String driver;
	private String address;
	private String user;
	private String password;
	private String table = "create or replace table 명단 "
							+ "("
							+ "번호 int, "
							+ "이름 varchar(50), "
							+ "성별 varchar(10), "
							+ "특징 varchar(50), "
							+ "해적단 varchar(50), "
							+ "역할 varchar(50)"
							+ ")";
	private String input = "insert into 명단 value (?,?,?,?,?,?)";
	private String update = "update 명단 set 특징 = ?, 역할 = ? where 번호 = ?";
	private String del = "delete from 명단 where 번호 = ?";
	
	public DbController(String _driver, String _address, String _user, String _password){
		this.driver = _driver;
		this.address = _address;
		this.user = _user;
		this.password = _password;
		initiate();
	}
	
	private void initiate() {
		System.out.println("시작 + " + this.driver);
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(address, user, password);
			
			controlFuc(conn);
		
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void controlFuc(Connection conn) {
		System.out.println("테이블을 생성합니다.");
		
		if(createTable(table, conn)) {
			//DML >> CRUD;
//			sql = createData();
//			cud(sql, conn);
			List<DataDto> list = createData();	
						
			for(int i = 0; i < list.size(); i++) {
				cud(input, conn, (DataDto)list.get(i), "I");
			}
			
			// 읽기
			readData("select * from 명단", conn);
			
			//수정			
			DataDto data = new DataDto();
			data.setFeature("사황");
			data.setRole("광대");
			data.setNo(10);
			cud(update, conn, data, "U");
			readData("select * from 명단", conn);
			
			//삭제
			DataDto data1 = new DataDto();
			data1.setNo(10);
			cud(del, conn, data1, "D");
			readData("select * from 명단", conn);
			
		}else {
			System.out.println("테이블 생성에 실패했습니다.");
		}
		
	}

	private List<DataDto> createData() {
		List<DataDto> list = new ArrayList<DataDto>();
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
				{"브룩", "남자", "연주자", "밀짚모자", "선원"},
				{"버기", "남자", "동강동강 열매", "버기", "선장"}
				};
		
		for(int i = 0; i < crew.length; i++) {
			DataDto data = new DataDto();
			data.setNo(i+1);
			data.setName(crew[i][0]);
			data.setSex(crew[i][1]);
			data.setFeature(crew[i][2]);
			data.setParty(crew[i][3]);
			data.setRole(crew[i][4]);
			list.add(data);
		}
		
		return list;
//		String sql = "insert into 명단 value ("
//					+ data.getNo()
//					+ ", '" + data.getName() + "'"
//					+ ", '" + data.getSex() + "'"
//					+ ", '" + data.getFeature() + "'"
//					+ ", '" + data.getParty() + "'"
//					+ ", '" + data.getRole() + "'"
//					+ ")";
//		
//		System.out.println(sql);
//		return sql;
	}
	
	private boolean createTable(String sql, Connection conn) {
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
	
	private void readData(String sql, Connection conn) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				DataDto data = new DataDto();
				data.setNo(rs.getInt(1));
				data.setName(rs.getString(2));
				data.setSex(rs.getString(3));
				data.setFeature(rs.getString(4));
				data.setParty(rs.getString(5));
				data.setRole(rs.getString(6));				
				System.out.println(data);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cud(String sql, Connection conn, DataDto data, String type) {
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
//			if("U".equals(type)) {
//				ps.setInt(1, data.getNo());
//				ps.setString(2, data.getName());
//				ps.setString(3, data.getSex());
//				ps.setString(4, data.getFeature());
//				ps.setString(5, data.getParty());
//				ps.setString(6, data.getRole());
//			}
			switch(type) {
				case "I":
					ps.setInt(1, data.getNo());
					ps.setString(2, data.getName());
					ps.setString(3, data.getSex());
					ps.setString(4, data.getFeature());
					ps.setString(5, data.getParty());
					ps.setString(6, data.getRole());
					break;
				case "U":
					ps.setString(1, data.getFeature());
					ps.setString(2, data.getRole());
					ps.setInt(3, data.getNo());
					break;
				case "D":
					ps.setInt(1, data.getNo());
					break;
//				default:
			}			
			int result = ps.executeUpdate();
			System.out.println(result);
			ps.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
