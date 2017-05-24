package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.book.bean.BookBean;

@ManagedBean
@SessionScoped
public class BookDetailManagedBean {
	private BookBean book;
	private String response;
	private String error;
	
	public BookDetailManagedBean() {}
	
	public BookBean getBook() {
		return book;
	}
	public String setBook(BookBean book) {
		this.book = book;
		
		return "detalhesLivro.jsf";
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
