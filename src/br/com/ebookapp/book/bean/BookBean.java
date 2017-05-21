package br.com.ebookapp.book.bean;

import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.publisher.bean.PublisherBean;
import br.com.ebookapp.subject.bean.SubjectBean;

public class BookBean {
	private int book_id;
	private String name;
	private String description;
	private Double price;
	private Double discount;
	private Integer stock;
	private Integer edition;
	private AuthorBean author;
	private PublisherBean publisher;
	private SubjectBean subject;
	private String image;

	public BookBean() {}

	public BookBean(int book_id, String name, String description, Double price, Double discount, Integer stock, Integer edition,
			AuthorBean author, PublisherBean publisher, SubjectBean subject, String image) {
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
		this.image = image;
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
	
	public String getReducedName() {
		final int maxLengh = 35;
		if (name.length() > maxLengh)
			return name.substring(0, maxLengh) + "...";
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getReducedDescription() {
		final int maxLengh = 200;
		if (description.length() > maxLengh)
			return description.substring(0, maxLengh) + "...";
		
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
