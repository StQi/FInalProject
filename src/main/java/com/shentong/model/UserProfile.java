package com.shentong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PUSERS_PROFILE")
@PrimaryKeyJoinColumn(name="username")
public class UserProfile extends User{
	@Column(name="firstname")
	private String FirstName;
	@Column(name="lastname")
	private String LastName;
	@Column(name="phonenum")
	private String PhoneNum;
	@Column(name="email")
	private String Email;
	@Column(name="address")
	private String Address;
	
	public UserProfile(){}

	public UserProfile(String firstName, String lastName, String phoneNum, String email, String address) {
		super();
		FirstName = firstName;
		LastName = lastName;
		PhoneNum = phoneNum;
		Email = email;
		Address = address;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	
	
}
