package java0530;

public class DBTable_1 {
	private int no;
	private String email;
	private String password;
	private boolean delYn;
	public DBTable_1() {
		this.delYn = false;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDelYn() {
		return delYn;
	}
	public void setDelYn(boolean delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "DBTable_1 [no=" + no + ", email=" + email + ", password=" + password + "]";
	}
	
}
