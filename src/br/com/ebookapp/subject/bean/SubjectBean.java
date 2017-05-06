package com.ebookapp.subject.bean;

public class SubjectBean {
	private int subject_id;
	private String name;
	
	public SubjectBean() {}
	
	public SubjectBean(int subject_id, String name) {
		super();
		this.subject_id = subject_id;
		this.name = name;
	}
	
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
