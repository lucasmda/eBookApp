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
public class ResultManagedBean {
	private List<BookBean> bookList;
	private RequestHandler requestHandler;
	private String response;
	private String error;
	private String inputSearch;
	
	public ResultManagedBean() {
		this.requestHandler = new RequestHandler();
		this.bookList = new ArrayList<BookBean>();
	}

	public List<BookBean> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
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
	
	public String searchBookByField() {
		if (!HandlerHelper.isBlankOrNull(this.inputSearch)) {
			this.bookList = this.requestHandler.getBook(this.inputSearch);
			
			return "resultado.jsf";
		}
		return "";
	}
}
