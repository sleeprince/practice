package java0528;

public class DataDto {
	
	private int no;
	private String name;
	private String sex;
	private String feature;
	private String party;
	private String role;
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "DataDto [no=" + no + ", name=" + name + ", sex=" + sex + ", feature=" + feature + ", party=" + party
				+ ", role=" + role + "]";
	}	
	
	
}
