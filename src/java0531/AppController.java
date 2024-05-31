package java0531;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java0530.DBTable_1;

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
				+ "no int, "
				+ "이름 varchar(50), "
				+ "생일 date, "
				+ "MBTI varchar(4), "
				+ "혈액형 varchar(2), "
				+ "delYN boolean"
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
				data.setNo(Integer.parseInt(str));				
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
		data.setName(scan.nextLine());
		System.out.println("생일을 입력하십시오.");
		data.setBirthday(scan.nextLine());
		System.out.println("MBTI를 입력하십시오.");
		data.setMbti(scan.nextLine().toUpperCase());
		System.out.println("혈액형을 입력하십시오.");
		data.setBloodType(scan.nextLine().toUpperCase());
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터입력하기(conn, sql, data);
	}
	
	private void 입력초기화(Connection conn) {
		sql = "create or replace table 명단 "
				+ "("
				+ "no int, "
				+ "이름 varchar(50), "
				+ "생일 date, "
				+ "MBTI varchar(4), "
				+ "혈액형 varchar(2), "
				+ "delYN boolean"
				+ ")";
		db.테이블생성(conn, sql);
		sql = "insert into 명단 value (?, ?, ?, ?, ?, ?)";
		List<DbTable> list = 초기값();
		for(int i = 0; i < list.size(); i++) {
			db.데이터입력하기(conn, sql, list.get(i));
		}		
	}
	
	private List<DbTable> 초기값(){
		List<DbTable> list = new ArrayList<>();
		String[][] str = new String[][] {
			{"김철수", "2003-04-03", "INFP", "A"},
			{"이영희", "2003-04-14", "ESTP", "O"},
			{"조민수", "1997-07-11", "INTP", "B"},
			{"이시우", "1997-03-22", "ISTJ", "AB"},
			{"공미영", "2002-01-03", "ENFJ", "B"},
			{"이서준", "1999-09-17", "ISTP", "A"},
			{"윤도준", "1998-04-23", "ISFP", "AB"}
		};
		for(int i = 0; i < str.length; i++) {
			DbTable data = new DbTable();
			data.setNo(i+1);
			data.setName(str[i][0]);
			data.setBirthday(str[i][1]);
			data.setMbti(str[i][2]);
			data.setBloodType(str[i][3]);			
			list.add(data);
		}
		return list;
		
	}
	
	// 3단계 생성된 데이터 가져오기 >> 목록 출력 시 화면출력() 메소드를 사용하시오.
	private void 가져오기(Connection conn) {
		sql = "select * from 명단 where delYn = 0 order by "; // DML 추가
		boolean key = true;
		while(key) {
			System.out.println("어떤 순서로 보시겠습니까?(O일련번호/N이름/B생일/M엠비티아이/T혈액형)");
			switch(scan.nextLine()) {
				case "O":
				case "o":
					sql += "no";
					key = false;
					break;
				case "N":
				case "n":
					sql += "이름";
					key = false;
					break;
				case "B":
				case "b":
					sql += "생일";
					key = false;
					break;
				case "M":
				case "m":
					sql += "MBTI";
					key = false;
					break;
				case "T":
				case "t":
					sql += "혈액형";
					key = false;					
					break;
				default:
					System.out.print("잘못 입력하셨습니다.");
			}
		}		
		System.out.println("SQL문 확인 : " + sql);
		화면출력(db.데이터가져오기(conn, sql));
	}
	
	private void 검색하기(Connection conn) {
		List<DbTable> list  = new ArrayList<>();	
		boolean key = true;
		do {
			sql = "select * from 명단 where (delYn = 0) AND (이름 = '";
			if(key == false) System.out.println("잘못 입력하였습니다."); 
			System.out.println("수혈받을 사람의 이름을 입력하십시오.");
			sql += scan.nextLine() + "')";
			list = db.데이터가져오기(conn, sql);
			key = false;
		}while(list.isEmpty());
		
		String receiver = list.get(0).getName();
		String blood = list.get(0).getBloodType();
		
		sql = "select * from 명단 where (delYn = 0) AND (이름 <> '" + receiver + "')";
		switch(blood.toUpperCase()) {
			case "AB":
				break;
			case "A":
				sql += " AND ((혈액형 = 'A') OR (혈액형 = 'O'))";
				break;
			case "B":
				sql += " AND ((혈액형 = 'B') OR (혈액형 = 'O'))";
				break;
			case "O":
				sql += " AND (혈액형 = 'O')";
				break;				
		}
		
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
				data.setNo(Integer.parseInt(str));
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
				System.out.println("무엇을 바꾸시겠습니까?(N이름/B생일/M엠비티아이/T혈액형/C완료)");
			}else {
				System.out.println("또 무엇을 바꾸시겠습니까?(N이름/B생일/M엠비티아이/T혈액형/C완료)");
			}
			switch(scan.nextLine()) {					
				case "N":
				case "n":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setName(scan.nextLine());
					break;
				case "B":
				case "b":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setBirthday(scan.nextLine());
					break;
				case "M":
				case "m":
					System.out.println("어떻게 바꾸시겠습니까?");
					data.setMbti(scan.nextLine().toUpperCase());
					break;
				case "T":
				case "t":
					System.out.println("어떻게 바꾸시겠습니까?");
					switch(str = scan.nextLine().toUpperCase()) {					
						case "A":
						case "B":
						case "O":
						case "AB":
							data.setBloodType(str);
							break;
						default:
							System.out.println("혈액형 입력이 올바르지 않습니다.");
					}
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
		if(data.getName() != null) {
			sql += "이름 = ?";
			cnt++;
		}
		if(data.getBirthday() != null) {
			if(cnt > 0) sql += ", ";
			sql += "생일 = ?";
			cnt++;
		}
		if(data.getMbti() != null) {
			if(cnt > 0) sql += ", ";
			sql += "MBTI = ?";
			cnt++;
		}
		if(data.getBloodType() != null) {
			if(cnt > 0) sql += ", ";
			sql += "혈액형 = ?";
			cnt++;
		}
		sql += " where no = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	
	// 5단계 생성된 데이터 삭제하기
	private void 삭제하기(Connection conn) {
		sql = "update 명단 set delYn = 1 where no = ?"; // DML 추가
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
				data.setNo(Integer.parseInt(str));
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
		db.데이터수정하기(conn, sql, data);
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
						System.out.println("어떤 기능을 실행하시겠습니까?(I입력초기화/C입력/R읽기/U수정/D삭제/B혈액형검색/E종료)");
						switch (scan.nextLine()) {
							case "I":
							case "i":
								입력초기화(conn);
								break;
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
							case "B":
							case "b":
								검색하기(conn);
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