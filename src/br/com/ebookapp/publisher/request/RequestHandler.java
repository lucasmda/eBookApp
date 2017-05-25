package br.com.ebookapp.publisher.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.publisher.bean.PublisherBean;

public class RequestHandler {
	private ResultSet resultSet = null;
	private String sql = "";
	private List<PublisherBean> list = null;
	private PublisherBean publisher;
	
	public boolean createPublisher(PublisherBean publisher) {
		this.sql = "INSERT INTO PUBLISHER"
				+ " VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setInt(1, getIdFromLastIndex());
			pstmt.setString(2, publisher.getName());
			pstmt.setString(3, publisher.getDescription());
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("CREATE PUBLISHER " + e.getMessage());
		}
		return false;
	}
	
	public PublisherBean createPublisherFromName(String name) {
		this.sql = "INSERT INTO PUBLISHER"
				+ " VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setInt(1, getIdFromLastIndex());
			pstmt.setString(2, publisher.getName());
			pstmt.setString(3, publisher.getDescription());
			boolean success =  pstmt.executeUpdate()==1;
			if (success) {
				return this.getPublisher(name);
			}
		} catch (Exception e) {
			System.out.println("CREATE PUBLISHER " + e.getMessage());
		}
		return null;
	}
	
	public List<PublisherBean> getPublisher() {
		this.sql = "SELECT 	publisher.publisher_id,"
				+ "			publisher.name,"
				+ "			publisher.description"
				+ " FROM 	Publisher publisher";
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			this.list = new ArrayList<PublisherBean>();
			while (this.resultSet.next()) {
				this.publisher = new PublisherBean();
				this.publisher.setPublisher_id(this.resultSet.getInt(1));
				this.publisher.setName(this.resultSet.getString(2));
				this.publisher.setDescription(this.resultSet.getString(3));
				this.list.add(this.publisher);
			}
			return this.list;
		} catch (Exception e) {
			System.out.println("GET PUBLISHER " + e.getMessage());
		}
		return null;
	}
	
	public PublisherBean getPublisher(int id) {
		this.sql = "SELECT 	publisher.publisher_id,"
				+ "			publisher.name,"
				+ "			publisher.description"
				+ " FROM 	Publisher publisher"
				+ " WHERE	publisher_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next()) {
				this.publisher = new PublisherBean();
				this.publisher.setPublisher_id(this.resultSet.getInt(1));
				this.publisher.setName(this.resultSet.getString(2));
				this.publisher.setDescription(this.resultSet.getString(3));
			}
			return this.publisher;
		} catch (Exception e) {
			System.out.println("GET PUBLISHER BY ID " + e.getMessage());
		}
		return null;
	}
	
	public PublisherBean getPublisher(String name) {
		this.sql = "SELECT 	publisher.publisher_id,"
				+ "			publisher.name,"
				+ "			publisher.description"
				+ " FROM 	Publisher publisher"
				+ " WHERE	name = " + name;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			this.resultSet = pstmt.executeQuery();
			if (this.resultSet.next()) {
				this.publisher = new PublisherBean();
				this.publisher.setPublisher_id(this.resultSet.getInt(1));
				this.publisher.setName(this.resultSet.getString(2));
				this.publisher.setDescription(this.resultSet.getString(3));
			}
			return this.publisher;
		} catch (Exception e) {
			System.out.println("GET PUBLISHER BY ID " + e.getMessage());
		}
		return null;
	}
	
	public boolean updatePublisher(PublisherBean publisher, int id) {
		this.sql = "UPDATE 	PUBLISHER SET name = ?,"
				+ "			description = ?"
				+ " WHERE 	publisher_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			pstmt.setString(1, publisher.getName());
			pstmt.setString(2, publisher.getDescription());
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("UPDATE PUBLISHER " + e.getMessage());
		}
		return false;
	}
	
	public boolean deletePublisher(int id) {
		this.sql = "DELETE 	FROM PUBLISHER"
				+ " WHERE 	publisher_id = " + id;
		try {
			PreparedStatement pstmt = RequestConnection.getConnection().prepareStatement(this.sql);
			return pstmt.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("DELETE PUBLISHER " + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		String maxIndexQuery = "SELECT MAX(publisher_id) FROM PUBLISHER;";
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
