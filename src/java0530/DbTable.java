package test;

public class DbTable {

	private int 번호;
	private String 이름;
	private String 성별;
	private String 특징;
	private String 해적단;
	private String 역할;	
	// 테이블 정의서를 보고 필드(변수)를 선언하시오.
	public int get번호() {
		return 번호;
	}
	public void set번호(int 번호) {
		this.번호 = 번호;
	}
	public String get이름() {
		return 이름;
	}
	public void set이름(String 이름) {
		this.이름 = 이름;
	}
	public String get성별() {
		return 성별;
	}
	public void set성별(String 성별) {
		this.성별 = 성별;
	}
	public String get특징() {
		return 특징;
	}
	public void set특징(String 특징) {
		this.특징 = 특징;
	}
	public String get해적단() {
		return 해적단;
	}
	public void set해적단(String 해적단) {
		this.해적단 = 해적단;
	}
	public String get역할() {
		return 역할;
	}
	public void set역할(String 역할) {
		this.역할 = 역할;
	}
	@Override
	public String toString() {
		return "DbTable [번호=" + 번호 + ", 이름=" + 이름 + ", 성별=" + 성별 + ", 특징=" + 특징 + ", 해적단=" + 해적단 + ", 역할=" + 역할 + "]";
	}
	
}
