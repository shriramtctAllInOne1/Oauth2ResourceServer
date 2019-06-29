package com.stock.oauth2.resourceserver.domain;

import java.util.List;
import java.util.Set;

public class MongoUserDetails {

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
	private String role;

	/**
	* 
	*/
	private String clientId;
	/**
	 * 
	 */
	private String resourceIds;
	/**
	 * 
	 */
	private boolean secretRequired;
	/**
	 * 
	 */
	private String clientSecret;
	/**
	 * 
	 */
	private boolean scoped;
	/**
	 * 
	 */
	private List<String> scope;
	/**
	 * 
	 */
	private List<String> authorizedGrantTypes;
	/**
	 * 
	 */
	private String registeredRedirectUri;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Integer accessTokenValiditySeconds;
	/**
	 * 
	 */
	private Integer refreshTokenValiditySeconds;
	/**
	 * 
	 */
	private boolean autoApprove;
	/**
	 * 
	 */
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public boolean isSecretRequired() {
		return secretRequired;
	}
	public void setSecretRequired(boolean secretRequired) {
		this.secretRequired = secretRequired;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public boolean isScoped() {
		return scoped;
	}
	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}
	public List<String> getScope() {
		return scope;
	}
	public void setScope(List<String> scope) {
		this.scope = scope;
	}
	public List<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	public void setAuthorizedGrantTypes(List<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	public String getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}
	public void setRegisteredRedirectUri(String registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}
	public boolean isAutoApprove() {
		return autoApprove;
	}
	public void setAutoApprove(boolean autoApprove) {
		this.autoApprove = autoApprove;
	}
	
	

}
