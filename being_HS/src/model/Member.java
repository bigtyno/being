package model;

import java.util.Date;

public class Member {

	private String id;
	private String name;
	private String password;
	private Date regDate;
	private int lvl;
	
	
	public Member(String id, String name, String password, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}
//관리자
	public Member(String id, String name, String password, Date regDate, int lvl) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
		this.lvl = lvl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

}
