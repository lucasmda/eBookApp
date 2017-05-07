package br.com.ebookapp.user.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.user.bean.UserBean;

public class RequestHandler {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String sql = "";
	private UserBean user = null;
	
	public UserBean signin(String email, String password) {
		this.sql = "SELECT  eBookUser.user_id,"
				+ "			eBookUser.name,"
				+ "			eBookUser.email,"
				+ "			eBookUser.address"
				+ " FROM	BOOK_USER eBookUser"
				+ " WHERE   eBookUser.email    = ?"
				+ " AND     eBookUser.password = ?";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setString(1, email);
			this.preparedStatement.setString(2, password);
			this.resultSet = this.preparedStatement.executeQuery();
			if (this.resultSet.next()) {
				user = new UserBean();
				user.setUser_id(this.resultSet.getInt(1));
				user.setName(this.resultSet.getString(2));
				user.setEmail(this.resultSet.getString(3));
				user.setAddress(this.resultSet.getString(4));
				return this.user;
			}
		} catch (Exception e) {
			System.out.println("LOGIN QUERY " + e.getMessage());
		}
		return null;
	}
	
	public boolean createUser(UserBean user, String password) {
		this.sql = "INSERT INTO BOOK_USER"
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setInt(1, this.getIdFromLastIndex());
			this.preparedStatement.setString(2, user.getName());
			this.preparedStatement.setString(3, user.getEmail());
			this.preparedStatement.setString(4, password);
			this.preparedStatement.setString(5, user.getAddress());
			return this.preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("CREATE USER " + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		this.sql = "SELECT MAX(user_id) FROM BOOK_USER";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			if (this.resultSet.next())
				return this.resultSet.getInt(1);
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
