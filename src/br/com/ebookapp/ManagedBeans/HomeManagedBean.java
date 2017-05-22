package br.com.ebookapp.ManagedBeans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.book.request.RequestHandler;
import br.com.ebookapp.database.validation.HandlerHelper;

@ManagedBean
public class HomeManagedBean {
	private BookBean book = null;
	private List<BookBean> bookList = null;
	private RequestHandler requestHandler = null;
	private String response = "";
	private String error = "";
	private String inputSearch = "";

	public HomeManagedBean() {
		this.requestHandler = new RequestHandler();

		this.book = new BookBean();
		this.bookList = requestHandler.getBook();
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

	public List<BookBean> getBookListFilteredByDiscount() {
		Collections.sort(this.bookList, new Comparator<BookBean>() {
			@Override
		    public int compare(BookBean o1, BookBean o2) {
		        return o1.getDiscount().compareTo(o2.getDiscount());
		    }
		});
		return bookList;
	}

	public List<BookBean> getBookListFilteredByRelease() {
		Collections.sort(this.bookList, new Comparator<BookBean>() {
			@Override
		    public int compare(BookBean o1, BookBean o2) {
		        return o1.getPrice().compareTo(o2.getPrice());
		    }
		});
		return bookList;
	}

	public List<BookBean> getBookListFilteredByNew() {
		Collections.sort(this.bookList, new Comparator<BookBean>() {
			@Override
		    public int compare(BookBean o1, BookBean o2) {
		        return new Integer(o1.getBook_id()).compareTo(new Integer(o2.getBook_id()));
		    }
		});
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
