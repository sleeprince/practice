package test;

public class App {

	/****************************************************************
	 * 문제) AppController 클래스에 있는 5단계의 메소드 기능을 넣고 요청한 동작이 되도록 코드를 작성하시오.
	 * 단, 각 단계에서 데이터를 제어하시오. (예시 : Scanner를 이용하여 데이터 입력방식도 가능)
	 *
	 * 1단계 : 테이블 정의서를 보고 생성하시오.
	 *
	 * 2단계 : 사용자 정보를 테이블에 추가하시오.
	 *  -> 이름		이메일		 비밀번호	 		탈퇴여부
	 *  -> 홍길동	hong@email.com	 1234			NO
	 *  -> 유관순	yu@email.com	 5678			NO
	 *
	 * 3단계 : 아래의 사용자 데이터를 출력하시오.
	 *  -> 홍길동
	 *
	 * 4단계 : 아래의 사용자 데이터의 번호를 입력 받아 이메일과 비밀번호를 수정하시오.
	 *  -> 유관순 사용자 수정
	 *  -> 이메일 : yuyu@email.com
	 *  -> 비밀번호 : 1212
	 *
	 * 5단계 : 삭제할 데이터의 번호를 입력 받아 삭제하시오.
	 *  -> 유관순 사용자 : 탈퇴여부 (YES)로 변경
	 ***************************************************************/

	// 현재 로컬에 설치 되어 있는 데이터베이스 접속 정보를 입력하세요.
	private final static String URL = "jdbc:mariadb://localhost:3306/study";
	private final static String USER = "root";
	private final static String PASSWORD = "1q2w3e4r";
	/****************************************************************/

	public static void main(String[] args) {
		new AppController().시작(URL, USER, PASSWORD);
	}

}
