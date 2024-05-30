package test;

import java.sql.Connection;
import java.sql.SQLException;
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
		sql = "create table if not exists 명단 "
				+ "("
				+ "번호 int, "
				+ "이름 varchar(50), "
				+ "성별 varchar(10), "
				+ "특징 varchar(50), "
				+ "해적단 varchar(50), "
				+ "역할 varchar(50)"
				+ ")"; // DDL 추가
		System.out.println("테이블 생성 SQL문 확인 : " + sql);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.		
		db.테이블생성(conn, sql);
	}
	
	// 2단계 생성된 테이블에 데이터 입력하기
	private void 입력하기(Connection conn) {
		sql = "insert into 명단 value (?,?,?,?,?,?)"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();		
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		boolean key = true;
		String str = "";
		int cnt = 0;
		System.out.println("번호를 입력하십시오.");
		while(key) {
			str = scan.nextLine();
			try {
				data.set번호(Integer.parseInt(str));
				key = false;
			}
			catch(NumberFormatException ex){
				if(cnt < 1) {
					System.out.println("숫자를 입력하십시오.");
				}else {
					System.out.println("글씨를 못 읽습니까? 숫자를 적으십시오.");
				}
				cnt++;
			}
		}
		
		System.out.println("이름을 입력하십시오.");
		data.set이름(scan.nextLine());
		System.out.println("성별을 입력하십시오.");
		data.set성별(scan.nextLine());
		System.out.println("특징을 입력하십시오.");
		data.set특징(scan.nextLine());
		System.out.println("소속 해적단을 입력하십시오.");
		data.set해적단(scan.nextLine());
		System.out.println("역할을 입력하십시오.");
		data.set역할(scan.nextLine());
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터입력하기(conn, sql, data);
	}
	
	// 3단계 생성된 데이터 가져오기 >> 목록 출력 시 화면출력() 메소드를 사용하시오.
	private void 가져오기(Connection conn) {
		sql = "select * from 명단"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		화면출력(db.데이터가져오기(conn, sql));
	}
	
	// 4단계 생성된 데이터 수정하기
	private void 수정하기(Connection conn) {
		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		
		sql = "update 명단 set ";
		
		System.out.println("수정할 인물의 번호를 입력하십시오.");
		boolean key = true;
		String str = "";
		int cnt = 0;
		while(key) {
			str = scan.nextLine();
			try {
				data.set번호(Integer.parseInt(str));
				key = false;
			}
			catch(NumberFormatException ex){
				if(cnt < 1) {
					System.out.println("숫자를 입력하십시오.");
				}else {
					System.out.println("글씨를 못 읽습니까? 숫자를 적으십시오.");
				}
				cnt++;
			}
		}
		key = true;
		cnt = 0;
		while(key) {
			if(cnt == 0) {
				System.out.println("무엇을 바꾸시겠습니까?(N이름/G성별/F특징/P해적단/R역할/C완료)");
			}else {
				System.out.println("또 무엇을 바꾸시겠습니까?(N이름/G성별/F특징/P해적단/R역할/C완료)");
			}
			switch(scan.nextLine()) {					
				case "N":
				case "n":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.set이름(scan.nextLine());
					break;
				case "G":
				case "g":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.set성별(scan.nextLine());
					break;
				case "F":
				case "f":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.set특징(scan.nextLine());
					break;
				case "P":
				case "p":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.set해적단(scan.nextLine());
					break;
				case "R":
				case "r":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.set역할(scan.nextLine());
					break;
				case "C":
				case "c":
					key = false;
					break;
				default:
					System.out.print("잘못 입력하셨습니다.");
			}
		}
		cnt = 0;
		if(data.get이름() != null) {
			sql += "이름 = ?";
			cnt++;
		}
		if(data.get성별() != null) {
			if(cnt > 0) sql += ", ";
			sql += "성별 = ?";
			cnt++;
		}
		if(data.get특징() != null) {
			if(cnt > 0) sql += ", ";
			sql += "특징 = ?";
			cnt++;
		}
		if(data.get해적단() != null) {
			if(cnt > 0) sql += ", ";
			sql += "해적단 = ?";
			cnt++;
		}
		if(data.get역할() != null) {
			if(cnt > 0) sql += ", ";
			sql += "역할 = ?";
		}		
		sql += " where 번호 = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	
	// 5단계 생성된 데이터 삭제하기
	private void 삭제하기(Connection conn) {
		sql = "delete from 명단 where 번호 = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		System.out.println("삭제할 인물의 번호를 입력하십시오.");
		boolean key = true;
		String str = "";
		int cnt = 0;
		while(key) {
			str = scan.nextLine();
			try {
				data.set번호(Integer.parseInt(str));
				key = false;
			}
			catch(NumberFormatException ex){
				if(cnt < 1) {
					System.out.println("숫자를 입력하십시오.");
				}else {
					System.out.println("글씨를 못 읽습니까? 숫자를 적으십시오.");
				}
				cnt++;
			}
		}
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터삭제하기(conn, sql, data);
	}
	/****************************************************************/
	
	private void 사용자() {
		System.out.println("당신은 누구인가요?");
		this.name = scan.nextLine();
		System.out.println(this.name + " 님 환영합니다.");
	}
	
	private void 종료() {
		System.out.println(this.name + " 님 다음에 또 찾아주세요.");
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