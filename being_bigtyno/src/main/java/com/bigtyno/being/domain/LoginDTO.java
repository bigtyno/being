package com.bigtyno.being.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

public class LoginDTO {
	private String email;
	private String password;
	private boolean useCookie;
	//private String name;
	//private int lvl;

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
	
	public boolean isUseCookie() {
		return useCookie;
	}

	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
/*
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + ", useCookie=" + useCookie + ", name=" + name
				+ ", lvl=" + lvl + "]";
	}
*/

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + ", useCookie=" + useCookie + "]";
	}
	
}