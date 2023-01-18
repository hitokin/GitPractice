package dto;

public class KadaiAccount {
	private int id;
	private String mail;
	private String password;
	private String name;
	private int grade;
	private String department;
	private String salt;
	private String hashedPw;
	
	public KadaiAccount(int id,String mail,String password, String name, int grade,String department, String salt, String hashedPw) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.name = name;
		this.grade = grade;
		this.department = department;
		this.salt = salt;
		this.hashedPw = hashedPw;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashedPw() {
		return hashedPw;
	}

	public void setHashedPw(String hashedPw) {
		this.hashedPw = hashedPw;
	}

}
