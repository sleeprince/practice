package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppController {
	
	private Scanner scan;
	private String name;
	private DBConnection db;
	private String sql;
	
	public AppController() {
		this.scan = new Scanner(System.in);
		this.db = new DBConnection();
	}

	/****************************************************************/
	// 1단계 테이블 생성하기
	private void 테이블생성(Connection conn) {
		sql = "create table if not exists USER "
				+ "("
				+ "사용자번호 int"
				+ ", 이름 varchar(100)"
				+ ", 이메일 varchar(200)"
				+ ", 비밀번호 varchar(100)"
				+ ", 탈퇴여부 boolean"
				+ ")"; // DDL 추가
		System.out.println("테이블 생성 SQL문 확인 : " + sql);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.		
		db.테이블생성(conn, sql);
	}
	
	// 2단계 생성된 테이블에 데이터 입력하기
	private void 입력하기(Connection conn) {
		sql = "insert into USER value (?, ?, ?, ?, ?)"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		boolean key = true;
		while(key) {
			System.out.println("사용자번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(scan.nextLine()));
				key = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("번호는 정수형 숫자입니다.");
			}
		}
		System.out.println("이름을 입력하십시오.");
		data.setName(scan.nextLine());
		System.out.println("이메일을 입력하십시오.");
		data.setEmail(scan.nextLine());
		System.out.println("비밀번호를 입력하십시오.");
		data.setPwd(scan.nextLine());
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터입력하기(conn, sql, data);
	}
	
	// 3단계 생성된 데이터 가져오기 >> 목록 출력 시 화면출력() 메소드를 사용하시오.
	private void 가져오기(Connection conn) {
		List<DbTable> list  = new ArrayList<>();	
		boolean key = true;
		String str;
		do {
			sql = "select * from USER where (탈퇴여부 = 0) ";
			if(key == false) System.out.println("잘못 입력하였습니다."); 
			System.out.println("사용자의 이름을 입력하십시오.");
			System.out.println("모두 출력하려면 A를 입력하십시오.");
			str = scan.nextLine();
			if("A".equals(str) || "a".equals(str)) { 
				list = db.데이터가져오기(conn, sql);
				break;
			}
			sql += "AND (이름 = '" + str + "')";
			list = db.데이터가져오기(conn, sql);
			key = false;
		}while(list.isEmpty());
		//System.out.println("SQL문 확인 : " + sql);
		화면출력(list);
	}
	
	// 4단계 생성된 데이터 수정하기
	private void 수정하기(Connection conn) {
		sql = "update USER set "; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		boolean key = true;
		while(key) {
			System.out.println("수정할 데이터의 번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(scan.nextLine()));
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
				System.out.println("무엇을 바꾸시겠습니까?(N이름/E이메일/P패스워드/C완료)");
			}else {
				System.out.println("또 무엇을 바꾸시겠습니까?(N이름/E이메일/P패스워드/C완료)");
			}
			switch(scan.nextLine()) {
				case "N":
				case "n":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setName(scan.nextLine());
					cnt++;
					break;
				case "E":
				case "e":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setEmail(scan.nextLine());
					cnt++;
					break;
				case "P":
				case "p":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setPwd(scan.nextLine());
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
		if(data.getName() != null) {
			sql += "이름 = ?";
			cnt++;
		}		
		if(data.getEmail() != null) {
			if(cnt > 0) sql += ", ";
			sql += "이메일 = ?";
			cnt++;
		}
		if(data.getPwd() != null) {
			if(cnt > 0) sql += ", ";
			sql += "비밀번호 = ?";
		}
		sql += " where 사용자번호 = ?";
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	
	// 5단계 생성된 데이터 삭제하기
	private void 삭제하기(Connection conn) {
		sql = "update USER set 탈퇴여부 = 1 where 사용자번호 = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();

		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		boolean key = true;
		while(key) {
			System.out.println("삭제할 데이터의 번호를 입력하십시오.");
			try {
				data.setNo(Integer.parseInt(scan.nextLine()));
				key = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("번호는 정수형 숫자입니다.");
			}
		}
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	/****************************************************************/
	
	private void 사용자() {
		System.out.println("당신은 누구인가요?");
		this.name = scan.nextLine();
		System.out.println(this.name + "님 환영합니다.");
	}
	
	private void 종료() {
		System.out.println(this.name + "님 다음에 또 찾아주세요.");
		scan.close();
	}
	
	private void 화면출력(List<DbTable> list) {
		for(int i = 0; i < list.size(); i++) {
			DbTable data = list.get(i);
			System.out.println(data);
		}
	}
	
	private boolean 디비접속() {
		boolean result = false;
		boolean key = true;
		System.out.println("데이터베이스에 연결하시겠습니까?(Y/N)");
		while(key) {
			switch (scan.nextLine()) {
				case "Y":
				case "y":
					result = true;
				case "N":
				case "n":
					key = false;
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
		}
		return result;
	}
	
	public void 시작(String URL, String USER, String PASSWORD) {
		사용자();
		if(디비접속()) {
			try {
				Connection conn = db.openDB(URL, USER, PASSWORD);
				if(conn != null) {
					테이블생성(conn);					
					boolean key = true;
					while(key) {
						System.out.println("어떤 기능을 실행하시겠습니까?(C입력/R읽기/U수정/D삭제/E종료)");
						switch (scan.nextLine()) {
							case "C": 
							case "c":
								입력하기(conn);
								break;
							case "R": 
							case "r":
								가져오기(conn);
								break;
							case "U": 
							case "u":
								수정하기(conn);
								break;
							case "D": 
							case "d":
								삭제하기(conn);
								break;
							case "E": 
							case "e":
								key = false;
								break;
							default:
								System.out.println("잘못된 입력입니다. 다시 기능을 입력해주세요.");
						}
					}
					conn.close();
					종료();
				} else {
					System.out.println("데이터 접속 정보를 확인해 주세요.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			종료();
		}
	}

}