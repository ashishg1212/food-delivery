package com.tap.model;

import java.time.LocalDateTime;

public class User {
	private int userId;
	private String name;
	private String email;
	private int phoneNo;
	private String address;
	private String username;
	private String password;
	private UserRole role;
	private LocalDateTime createDate;
    private LocalDateTime lastLogin;
	
    public User() {
		
	}

	public User(int userId, String name, String email, int phoneNo, String address, String username, String password,
			UserRole role, LocalDateTime createDate, LocalDateTime lastLogin) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.username = username;
		this.password = password;
		this.role = role;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
	}

	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", address="
                + address + ", username=" + username + ", password=" + password + ", role=" + role + ", createDate="
                + createDate + ", lastLogin=" + lastLogin + "]";
    }
}
