package com.ebookapp.subject.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ebookapp.database.RequestConnection;
import com.ebookapp.subject.bean.SubjectBean;

public class RequestHandler {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String sql = "";
	private List<SubjectBean> list = null;
	private SubjectBean subject = null;
	
	public boolean createSubject(SubjectBean subject) {
		this.sql = "INSERT INTO SUBJECT"
				+ " VALUES (?, ?)";
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setInt(1, this.getIdFromLastIndex());
			this.preparedStatement.setString(2, subject.getName());
			return this.preparedStatement.execute();
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
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			this.list = new ArrayList<SubjectBean>();
			while (this.resultSet.next()) {
				this.subject = new SubjectBean();
				this.subject.setSubject_id(this.resultSet.getInt(1));
				this.subject.setName(this.resultSet.getString(2));
				this.list.add(this.subject);
			}
			return this.list;
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
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			if (this.resultSet.next()) {
				this.subject = new SubjectBean();
				this.subject.setSubject_id(this.resultSet.getInt(1));
				this.subject.setName(this.resultSet.getString(2));
				return this.subject;
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
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			this.preparedStatement.setString(1, subject.getName());
			return this.preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("UPDATE SUBJECT " + e.getMessage());
		}
		return false;
	}
	
	public boolean deleteSubject(int id) {
		this.sql = "DELETE FROM SUBJECT"
				+ " WHERE 	subject_id = " + id;
		try {
			this.preparedStatement = RequestConnection.getConnection().prepareStatement(sql);
			return this.preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("DELETE SUBJECT BY ID" + e.getMessage());
		}
		return false;
	}
	
	private int getIdFromLastIndex() {
		this.sql = "SELECT MAX(subject_id) FROM SUBJECT;";
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
