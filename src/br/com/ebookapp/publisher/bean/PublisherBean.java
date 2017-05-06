package com.ebookapp.publisher.bean;

public class PublisherBean {
	private int publisher_id;
	private String name;
	private String description;
	
	public PublisherBean() {}

	public PublisherBean(int publisher_id, String name, String description) {
		super();
		this.publisher_id = publisher_id;
		this.name = name;
		this.description = description;
	}

	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
