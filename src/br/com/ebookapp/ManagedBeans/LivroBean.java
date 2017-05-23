package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.database.validation.RequestValidator;
import br.com.ebookapp.publisher.bean.PublisherBean;
import br.com.ebookapp.subject.bean.SubjectBean;
import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.book.request.RequestHandler;

@ManagedBean
public class LivroBean {
	private BookBean book;
	private List<BookBean> bookList;
	private List<AuthorBean> authors;
	private List<PublisherBean> publishers;
	private List<SubjectBean> subjects;
	private RequestHandler requestHandler;
	private String response;
	private String error;
	
	public LivroBean() {
		this.requestHandler = new RequestHandler();
		
		this.book = new BookBean();
		this.bookList = this.requestHandler.getBook();
		
		this.authors = new br.com.ebookapp.author.request.RequestHandler().getAuthor();
		this.publishers = new br.com.ebookapp.publisher.request.RequestHandler().getPublisher();
		this.subjects = new br.com.ebookapp.subject.request.RequestHandler().getSubject();
	}

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}

	public List<AuthorBean> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorBean> authors) {
		this.authors = authors;
	}

	public List<PublisherBean> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<PublisherBean> publishers) {
		this.publishers = publishers;
	}

	public List<SubjectBean> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectBean> subjects) {
		this.subjects = subjects;
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

	public String registerNewBook() {
		try {
			if (RequestValidator.validateBook(this.book)) {
				boolean isSuccess = requestHandler.createBook(this.book);
				if (isSuccess) {
					this.book.setName(null);
					this.book.setDescription(null);
					this.book.setPrice(null);
					this.book.setDiscount(null);
					this.book.setStock(null);
					this.book.setEdition(null);
					this.book.setAuthor(null);
					this.book.setPublisher(null);
					this.book.setSubject(null);
				}
			}
			return response;
		} catch(Exception e){
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String editBook() {
		try {
			this.book = requestHandler.getBook(this.book.getBook_id());
			return "edit_livro";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String deleteBook() {
		try {
			if (!HandlerHelper.isBlankOrNull(String.valueOf(this.book.getBook_id()))) {
				boolean isSuccess = requestHandler.deleteBook(this.book.getBook_id());
				if (isSuccess) {
					this.bookList = requestHandler.getBook();
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String updateBook() {
		try {
			if (RequestValidator.validateBook(this.book)) {
				boolean isSuccess = requestHandler.updateBook(this.book, this.book.getBook_id());
				if (isSuccess) {
					this.book.setName(null);
					this.book.setDescription(null);
					this.book.setPrice(null);
					this.book.setDiscount(null);
					this.book.setStock(null);
					this.book.setEdition(null);
					this.book.setAuthor(null);
					this.book.setPublisher(null);
					this.book.setSubject(null);
					this.bookList = requestHandler.getBook();
					return "index_livro";
				} 
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}
}
