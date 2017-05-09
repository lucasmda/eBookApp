package br.com.ebookapp.subject.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebookapp.database.RequestConnection;
import br.com.ebookapp.subject.bean.SubjectBean;

public class RequestHandler {
	private ResultSet resultSet = null;
	private String sql = "";
	private List<SubjectBean> list = null;
	private SubjectBean subject = null;
	
	public boolean createSubject(SubjectBean subject) {
		this.sql = "INSERT INTO SUBJECT"
				+ " VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			preparedStatement.setInt(1, getIdFromLastIndex());
			preparedStatement.setString(2, subject.getName());
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("CREATE SUBJECT " + e.getMessage());
		}
		return false;
	}
	
	public List<SubjectBean> getSubject() {
		this.sql = "SELECT 	subject.subject_id,"
				+ " 		subject.name"
				+ " FROM 	SUBJECT subject";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<SubjectBean>();
			while (resultSet.next()) {
				subject = new SubjectBean();
				subject.setSubject_id(resultSet.getInt(1));
				subject.setName(resultSet.getString(2));
				list.add(subject);
			}
			return list;
		} catch (Exception e) {
			System.out.println("GET ALL SUBJECT " + e.getMessage());
		}
		return null;
	}
	
	public SubjectBean getSubject(int id) {
		this.sql = "SELECT	subject.subject_id,"
				+ "			subject.name"
				+ " FROM	SUBJECT subject"
				+ " WHERE	subject.subject_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subject = new SubjectBean();
				subject.setSubject_id(resultSet.getInt(1));
				subject.setName(resultSet.getString(2));
				return subject;
			}
		} catch (Exception e) {
			System.out.println("GET SUBJECT BY ID " + e.getMessage());
		}
		return null;
	}
	
	public boolean updateSubject(SubjectBean subject, int id) {
		this.sql = "UPDATE SUBJECT SET name = ?"
				+ " WHERE 	subject_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			preparedStatement.setString(1, subject.getName());
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("UPDATE SUBJECT " + e.getMessage());
		}
		return false;
	}
	
	public boolean deleteSubject(int id) {
		this.sql = "DELETE FROM SUBJECT"
				+ " WHERE 	subject_id = " + id;
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(this.sql);
			return preparedStatement.executeUpdate()==1;
		} catch (Exception e) {
			System.out.println("DELETE SUBJECT BY ID" + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		String maxIndexQuery = "SELECT MAX(subject_id) FROM SUBJECT;";
		try {
			PreparedStatement preparedStatement = RequestConnection.getConnection().prepareStatement(maxIndexQuery);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt(1)+1;
		} catch (Exception e) {
			System.out.println("GET MAX INDEX " + e.getMessage());
		}
		return 999;
	}
}
