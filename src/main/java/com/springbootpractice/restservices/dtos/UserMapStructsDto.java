package com.springbootpractice.restservices.dtos;

public class UserMapStructsDto {

	private Long id;
	private String username;
	private String emailAddress;

	public UserMapStructsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMapStructsDto(Long id, String username, String emailAddress) {
		super();
		this.id = id;
		this.username = username;
		this.emailAddress = emailAddress;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
