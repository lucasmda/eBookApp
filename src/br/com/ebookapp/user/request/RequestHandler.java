package br.com.ebookapp.user.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.subject.bean.SubjectBean;
import br.com.ebookapp.user.bean.UserBean;

public class RequestHandler {
	private ResultSet resultSet = null;
	private String sql = "";
	private List<UserBean> list = null;
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
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next()) {
				this.user = new UserBean();
				this.user.setUser_id(this.resultSet.getInt(1));
				this.user.setName(this.resultSet.getString(2));
				this.user.setEmail(this.resultSet.getString(3));
				this.user.setAddress(this.resultSet.getString(4));
				return this.user;
			}
		} catch (Exception e) {
			System.out.println("LOGIN QUERY " + e.getMessage());
		}
		return null;
	}
	
	public boolean createUser(UserBean user) {
		this.sql = "INSERT INTO BOOK_USER"
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setInt(1, getIdFromLastIndex());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getAddress());
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("CREATE USER " + e.getMessage());
		}
		return false;
	}
	
	public List<UserBean> getUser() {
		this.sql = "SELECT 	eBookUser.user_id,"
				+ " 		eBookUser.name,"
				+ "			eBookUser.email,"
				+ "			eBookUser.password,"
				+ "			eBookUser.address"
				+ " FROM 	BOOK_USER eBookUser";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = preparedStatement.executeQuery();
			this.list = new ArrayList<UserBean>();
			while (this.resultSet.next()) {
				this.user = new UserBean();
				this.user.setUser_id(this.resultSet.getInt(1));
				this.user.setName(this.resultSet.getString(2));
				this.user.setEmail(this.resultSet.getString(3));
				this.user.setPassword(this.resultSet.getString(4));
				this.user.setAddress(this.resultSet.getString(5));
				this.list.add(this.user);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET ALL USER " + e.getMessage());
		}
		return null;
	}
	
	public UserBean getUser(int id) {
		this.sql = "SELECT 	eBookUser.user_id,"
				+ " 		eBookUser.name,"
				+ "			eBookUser.email,"
				+ "			eBookUser.password,"
				+ "			eBookUser.address"
				+ " FROM 	BOOK_USER eBookUser"
				+ " WHERE	eBookUser.user_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = preparedStatement.executeQuery();
			if (this.resultSet.next()) {
				this.user = new UserBean();
				this.user.setUser_id(this.resultSet.getInt(1));
				this.user.setName(this.resultSet.getString(2));
				this.user.setEmail(this.resultSet.getString(3));
				this.user.setPassword(this.resultSet.getString(4));
				this.user.setAddress(this.resultSet.getString(5));
				return this.user;
			}
		} catch (Exception e) {
			System.out.println("GET USER BY ID " + e.getMessage());
		}
		return null;
	}
	
	public boolean updateUser(UserBean user, int id) {
		this.sql = "UPDATE 	BOOK_USER"
				+ " SET		name = ?,"
				+ "			email = ?,"
				+ "			password = ?,"
				+ "			address = ?"
				+ " WHERE 	user_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getAddress());
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("UPDATE USER " + e.getMessage());
		}
		return false;
	}
	
	public boolean deleteUser(int id) {
		this.sql = "DELETE 	FROM BOOK_USER"
				+ " WHERE 	user_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("DELETE USER BY ID" + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		String maxIndexQuery = "SELECT MAX(user_id) FROM BOOK_USER";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(maxIndexQuery);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next())
				return this.resultSet.getInt(1)+1;
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
