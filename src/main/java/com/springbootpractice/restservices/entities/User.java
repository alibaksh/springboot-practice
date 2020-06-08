package com.springbootpractice.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

@Table(name = "users")
@Entity
//@JsonIgnoreProperties({"firstName", "lasttName"}) This is part of static filtering
//@JsonFilter(value = "userFilter") Used for Mapping Jackson value filtering
public class User extends RepresentationModel<User> {

	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;

	@NotEmpty(message = "Username is mandatory, please provide valid username.")
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;

	@Size(min = 2, message = "First name should have at least 2 characters.")
	@Column(name = "first_name", nullable = false)
	@JsonView(Views.External.class)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@JsonView(Views.External.class)
	private String lastName;

	@Column(name = "email", nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "role", nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

	@Column(name = "ssn", length = 50, nullable = false, unique = true)
//	@JsonIgnore  This is part of static filtering
	@JsonView(Views.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	public User() {

	}

	public User(Long id, String username, String firstName, String lastName, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lasttName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
}
