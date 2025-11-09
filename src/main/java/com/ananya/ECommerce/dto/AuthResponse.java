package com.ananya.ECommerce.dto;


public class AuthResponse {
	private String token;
	private String email;
	private String role;
	public AuthResponse() {
		
	}
	public AuthResponse(String toke,String email,String role) {
		this.token=token;
		this.email=email;
		this.role=role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String toString() {
		return "AuthResponse"+ "token='"
	+token+'\''+"email='"+email+ '\''+"role='"+role+'\''+'}';
	}
}
