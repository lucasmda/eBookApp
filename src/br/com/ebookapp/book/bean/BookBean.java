package com.ebookapp.book.bean;

import com.ebookapp.author.bean.AuthorBean;
import com.ebookapp.publisher.bean.PublisherBean;
import com.ebookapp.subject.bean.SubjectBean;

public class BookBean {
	private int book_id;
	private String name;
	private String description;
	private double price;
	private double discount;
	private int stock;
	private int edition;
	private AuthorBean author;
	private PublisherBean publisher;
	private SubjectBean subject;
	
	public BookBean() {}

	public BookBean(int book_id, String name, String description, double price, double discount, int stock, int edition,
			AuthorBean author, PublisherBean publisher, SubjectBean subject) {
		super();
		this.book_id = book_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.edition = edition;
		this.author = author;
		this.publisher = publisher;
		this.subject = subject;
	}

	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public AuthorBean getAuthor() {
		return author;
	}
	public void setAuthor(AuthorBean author) {
		this.author = author;
	}
	public PublisherBean getPublisher() {
		return publisher;
	}
	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}
	public SubjectBean getSubject() {
		return subject;
	}
	public void setSubject(SubjectBean subject) {
		this.subject = subject;
	}
}
