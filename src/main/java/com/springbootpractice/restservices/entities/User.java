package com.springbootpractice.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lasttName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "ssn", length = 50, nullable = false, unique = true)
	private String ssn;

	public User() {

	}

	public User(Long id, String username, String firstName, String lasttName, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lasttName;
	}

	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lasttName=" + lasttName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
