package br.com.ebookapp.author.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.database.RequestConnection;

public class RequestHandler {
	private ResultSet resultSet = null;
	private String sql = "";
	private List<AuthorBean> list = null;
	private AuthorBean author = null;
	
	public boolean createAuthor(String authorName) {
		this.sql = "INSERT 	INTO AUTHOR "
				+ " VALUES(?, ?)";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			preparedStatement.setInt(1, getIdFromLastIndex());
			preparedStatement.setString(2, authorName);
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("CREATE AUTHOR " + e.getMessage());
		}
		return false;
	}
	
	public List<AuthorBean> getAuthor() {
		this.sql = "SELECT 	author.author_id,"
				+ "			author.name"
				+ " FROM 	AUTHOR author";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = preparedStatement.executeQuery();
			this.list = new ArrayList<AuthorBean>();
			while (this.resultSet.next()) {
				this.author = new AuthorBean();
				this.author.setAuthor_id(this.resultSet.getInt(1));
				this.author.setName(this.resultSet.getString(2));
				this.list.add(this.author);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET AUTHOR " + e.getMessage());
		}
		return null;
	}
	
	public AuthorBean getAuthor(int id) {
		this.sql = "SELECT 	author.author_id,"
				+ "			author.name"
				+ " FROM 	AUTHOR author"
				+ "	WHERE 	author.author_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = preparedStatement.executeQuery();
			if (this.resultSet.next()) {
				this.author = new AuthorBean();
				this.author.setAuthor_id(this.resultSet.getInt(1));
				this.author.setName(this.resultSet.getString(2));
				return this.author;
			}
		} catch (Exception e) {
			System.out.println("GET AUTHOR BY ID " + e.getMessage());
		}
		return null;
	}
	
	public boolean updateAuthor(AuthorBean author, int id) {
		this.sql = "UPDATE 	AUTHOR SET name = ?"
				+ " WHERE 	author_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			preparedStatement.setString(1, author.getName());
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {	
			System.out.println("UPDATE AUTHOR " + e.getMessage());
		}
		return false;
	}
	
	public boolean deleteAuthor(int id) {
		this.sql = "DELETE 	FROM AUTHOR"
				+ " WHERE 	author_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("DELETE AUTHOR " + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		String maxIndexQuery = "SELECT MAX(author_id) FROM AUTHOR;";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(maxIndexQuery);
			this.resultSet = preparedStatement.executeQuery();
			if (this.resultSet.next())
				return this.resultSet.getInt(1)+1;
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
