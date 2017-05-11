package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.database.validation.RequestValidator;
import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.book.request.RequestHandler;

@ManagedBean
public class LivroBean {
	private BookBean book = null;
	private List<BookBean> bookList = null;
	private RequestHandler requestHandler = null;
	private String response = "";
	private String error = "";
	
	public LivroBean() {
		this.requestHandler = new RequestHandler();
		
		this.book = new BookBean();
		this.bookList = this.requestHandler.getBook();
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
					this.book.setPrice(0);
					this.book.setDiscount(0);
					this.book.setStock(0);
					this.book.setEdition(0);
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
					this.book.setPrice(0);
					this.book.setDiscount(0);
					this.book.setStock(0);
					this.book.setEdition(0);
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
