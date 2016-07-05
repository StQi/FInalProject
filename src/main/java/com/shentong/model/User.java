package com.shentong.model;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "PUSERS")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	@Id
	@Column(name="username")
	private String UserName;
	@Column(name="password")
	private String Password;
	@Column(name="role")
	private String Role;
	
	public User(){}

	public User(String userName, String password, String role) {
		super();
		UserName = userName;
		Password = password;
		Role = role;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User))
			return false;
		else{
			User tu = (User)obj;
			return UserName.equals(tu.getUserName())&&Password.equals(tu.getPassword())&&Role.equals(tu.getRole());
		}
	}
	
	
	
	
}
