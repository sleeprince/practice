package java0530;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;


public class AppController_1 {
	
	private DBConnector_1 db;
	private Scanner sc;
	private String sql = "";
	
	public AppController_1() {
		this.db = new DBConnector_1();
		this.sc = new Scanner(System.in);
	}	
	
	private boolean shallWeBegin(){
		while(true) {
			System.out.println("데이터베이스에 접속하시겠습니까?(Y/N)");
			switch(sc.nextLine()) {
				case "Y":
				case "y":
				case "ㅛ":
					return true;
				case "N":
				case "n":
				case "ㅜ":
					return false;
				default:
					System.out.println("바른 값을 입력해 주십시오.");
			}
		}		
	}
	
	private void bye() {
		System.out.println("안녕히 가십시오. 멀리 안 나갑니다.");
		sc.close();	
	}
	
	private void createTable(Connection conn) {
		sql = "create table if not exists 이메일 "
				+ "("
				+ "no int"
				+ ", email varchar(100)"
				+ ", password varchar(60)"
				+ ", delYn boolean"
				+ ")";
		//System.out.println(sql);
		db.createTable(sql, conn);
	}
	
	private void initiateData(Connection conn) {
		sql = "create or replace table 이메일 "
				+ "("
				+ "no int"
				+ ", email varchar(100)"
				+ ", password varchar(60)"
				+ ", delYn boolean"
				+ ")";
		db.createTable(sql, conn);
		List<DBTable_1> list = basicData();
		DBTable_1 data = new DBTable_1();
		sql = "insert into 이메일 value (?,?,?,?)";
		for(int i = 0; i < list.size(); i++) {
			data = list.get(i);
			db.insertData(sql, conn, data);
		}
		System.out.println("초기화 완료!");
	}
	
	private List<DBTable_1> basicData(){
		List<DBTable_1> list = new ArrayList<>();
		
		String[][] str = new String[][] {
			{"홍길동@이메일.컴", "pAssWorD"},
			{"유관순@이메일.컴", "1@34%"},
			{"이순신@이메일.컴", "!!9!2#"},
			{"화이슬@이메일.컴", "ghkdltmf"}
		};
		for(int i = 0; i < str.length; i++) {
			DBTable_1 data = new DBTable_1();
			data.setNo(i+1);
			data.setEmail(str[i][0]);
			data.setPassword(str[i][1]);
			list.add(data);
		}
		return list;
	}
	
	private void insertData(Connection conn) {
		sql = "insert into 이메일 value (?, ?, ?, ?)";
		DBTable_1 data = new DBTable_1();
		boolean key = true;
		while(key) {
			System.out.println("입력할 데이터의 번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(sc.nextLine()));
				key = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("번호는 정수형 숫자입니다.");
			}
		}
		System.out.println("이메일 주소를 입력하십시오.");
		data.setEmail(sc.nextLine());
		System.out.println("비밀번호를 입력하십시오.");
		data.setPassword(sc.nextLine());
		
		db.insertData(sql, conn, data);
		System.out.println("입력 완료!");
	}
	
	private List<DBTable_1> readData(Connection conn) {
		List<DBTable_1> list;
		sql = "select no, email, password from 이메일 where delYn = 0";		
		return list = db.readDB(sql, conn);
	}
	
	private void printData(List<DBTable_1> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private void updateData(Connection conn) {
		sql = "update 이메일 set ";
		DBTable_1 data = new DBTable_1();		
		boolean key = true;
		while(key) {
			System.out.println("수정할 데이터의 번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(sc.nextLine()));
				key = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("번호는 정수형 숫자입니다.");
			}
		}
		key = true;
		int cnt = 0;
		while(key) {
			if(cnt == 0) {
				System.out.println("무엇을 바꾸시겠습니까?(E이메일/P패스워드/C완료)");
			}else {
				System.out.println("또 무엇을 바꾸시겠습니까?(E이메일/P패스워드/C완료)");
			}
			switch(sc.nextLine()) {
				case "E":
				case "e":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setEmail(sc.nextLine());
					cnt++;
					break;
				case "P":
				case "p":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setPassword(sc.nextLine());
					cnt++;
					break;
				case "C":
				case "c":
					key = false;
					cnt = 0;
					break;
				default:
					System.out.println("잘못 입력하였습니다.");	
			}
		}
		if(data.getEmail() != null) {
			sql += "email = ?";
			cnt++;
		}
		if(data.getPassword() != null) {
			if(cnt > 0) sql += ", ";
			sql += "password = ?";
		}
		sql += " where no = ?";
		//System.out.println(sql);
		db.updatetData(sql, conn, data);
		System.out.println("수정 완료!");
	}
	
	private void deleteData(Connection conn) {
		sql = "update 이메일 set delYn = 1 where no = ?";
		DBTable_1 data = new DBTable_1();		
		boolean key = true;
		while(key) {
			System.out.println("삭제할 데이터의 번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(sc.nextLine()));
				key = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("번호는 정수형 숫자입니다.");
			}
		}
		//System.out.println(sql);
		db.updatetData(sql, conn, data);
		System.out.println("삭제 완료!");
	}
	
	public void go(String _address, String _user, String _password) {
		System.out.println("반갑습니다!");
		try {
			if(shallWeBegin()) {
				Connection conn = db.openDB(_address, _user, _password);
				if(conn != null) {
					createTable(conn);
					boolean key = true;
					while(key) {
						System.out.println("어떤 기능을 실행하시겠습니까?(I초기화/C입력/R읽기/U수정/D삭제/E종료)");
						switch(sc.nextLine()) {						
							case "I":
							case "i":
								initiateData(conn);
								break;
							case "C":
							case "c":
								insertData(conn);
								break;
							case "R":
							case "r":
								printData(readData(conn));
								break;
							case "U":
							case "u":
								updateData(conn);
								break;
							case "D":
							case "d":
								deleteData(conn);
								break;
							case "E":
							case "e":
								key = false;
								break;
							default:
								System.out.println("잘못 입력하였습니다.");								
						}
					}
				}				
				conn.close();
				bye();
			}else {
				bye();
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
}
