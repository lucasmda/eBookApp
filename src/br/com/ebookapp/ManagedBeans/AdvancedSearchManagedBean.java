package br.com.ebookapp.ManagedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.book.request.RequestHandler;
import br.com.ebookapp.database.validation.HandlerHelper;

@ManagedBean
@SessionScoped
public class AdvancedSearchManagedBean {
	private String author = "";
	private String book = "";
	private String publisher = "";
	private String subject = "";
	private List<BookBean> bookList;
	private RequestHandler requestHandler = null;
	private String response = "";
	private String error = "";
	
	public AdvancedSearchManagedBean() {
		this.requestHandler = new RequestHandler();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<BookBean> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}

	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
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
	
	public void searchBook() {
		List<String> map = new ArrayList<String>();
		if (!HandlerHelper.isBlankOrNull(this.book)) {
			map.add("book.name LIKE '%" + this.book + "%'");
			map.add("book.description LIKE '%" + this.book + "%'");
		}
		
		if (!HandlerHelper.isBlankOrNull(this.author)) {
			map.add("author.name LIKE '%" + this.author + "%'");
		}
		
		if (!HandlerHelper.isBlankOrNull(this.publisher)) {
			map.add("publisher.name LIKE '%" + this.publisher + "%'");
			map.add("publisher.description LIKE '%" + this.publisher + "%'");
		}
		
		if (!HandlerHelper.isBlankOrNull(this.subject)) {
			map.add("subject.name LIKE '%"+ this.subject + "%'");
		}
		
		if (map.size() > 0) {
			this.bookList = this.requestHandler.getBookFilteredByAdvancedSearch(map);
		}
	}
}
