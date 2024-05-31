package java0531;

public class DbTable {

	private int no;
	private String name;
	private String birthday;
	private String mbti;
	private String bloodType;
	private boolean delYn;
	
	public DbTable() {
		delYn = false;
	}
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public boolean isDelYn() {
		return delYn;
	}
	public void setDelYn(boolean delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "DbTable [no=" + no + ", name=" + name + ", birthday=" + birthday + ", mbti=" + mbti + ", bloodType="
				+ bloodType + "]";
	}

}
