package test;

public class DbTable {

	private int no;
	private String name;
	private String email;
	private String pwd;
	private boolean del;
	public DbTable() {
		del = false;
	}
	// 테이블 정의서를 보고 필드(변수)를 선언하시오.
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "DbTable [no=" + no + ", name=" + name + ", email=" + email + ", pwd=" + pwd + "]";
	}
	
}
