package com.bridgelabz.fundoo.user.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private long userid;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "emailId")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "verify")
	private String verify = "unverified";

	@Column(name = "registrationDate")
	private LocalDateTime registeredDate;

	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;
	
	public User(long userid, String firstName, String lastName, String email, String password, String verify,
					LocalDateTime registeredDate, LocalDateTime updatedDate) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.verify = verify;
		this.registeredDate = registeredDate;
		this.updatedDate = updatedDate;

	}

	public User() {
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String isVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				   		  + ", password=" + password + ", verify=" + verify + ", registeredDate=" + registeredDate
				   		  + ", updatedDate=" + updatedDate + "]";
	}

}
