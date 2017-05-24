package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.book.request.RequestHandler;
import br.com.ebookapp.database.validation.HandlerHelper;

@ManagedBean
@SessionScoped
public class HomeManagedBean {
	private BookBean selectedBook;
	private List<BookBean> bookList;
	private List<BookBean> bookWithDiscountList;
	private RequestHandler requestHandler;
	private String response;
	private String error;
	private String inputSearch;

	public HomeManagedBean() {
		this.requestHandler = new RequestHandler();

		this.selectedBook = new BookBean();
		this.bookList = requestHandler.getBook();
		this.bookWithDiscountList = requestHandler.getBookWithDiscount();
	}

	public BookBean getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(BookBean selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<BookBean> getBookList() {
		return bookList;
	}

	public List<BookBean> getBookWithDiscountList() {
		return bookWithDiscountList;
	}

	public void setBookWithDiscountList(List<BookBean> bookWithDiscountList) {
		this.bookWithDiscountList = bookWithDiscountList;
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

	public String getInputSearch() {
		return inputSearch;
	}

	public void setInputSearch(String inputSearch) {
		this.inputSearch = inputSearch;
	}
	
	public void searchBookByField() {
		if (!HandlerHelper.isBlankOrNull(this.inputSearch)) {
			this.bookList = this.requestHandler.getBook(this.inputSearch);
		}
	}
}
