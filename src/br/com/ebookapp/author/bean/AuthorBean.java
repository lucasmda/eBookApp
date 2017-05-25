package br.com.ebookapp.author.bean;

public class AuthorBean {
	private int author_id;
	private String name;
	
	public AuthorBean() {
		this.author_id = -1;
	}
	
	public AuthorBean(int author_id, String name) {
		super();
		this.author_id = author_id;
		this.name = name;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
