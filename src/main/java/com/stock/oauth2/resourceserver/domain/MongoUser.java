package com.stock.oauth2.resourceserver.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;

/**
 * @author shriram
 *
 */
public class MongoUser {

	/**
	 * 
	 */
	@Id
	private String id;

	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private Set<String> roles;

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}