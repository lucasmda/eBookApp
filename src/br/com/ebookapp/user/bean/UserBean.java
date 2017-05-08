package br.com.ebookapp.user.bean;

public class UserBean {
	private int user_id;
	private String name;
	private String email;
	private String address;
	
	public UserBean() {}
	
	public UserBean(int user_id, String name, String email, String address) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}