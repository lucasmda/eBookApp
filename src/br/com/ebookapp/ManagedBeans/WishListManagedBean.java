package br.com.ebookapp.ManagedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.book.bean.BookBean;

@ManagedBean
@SessionScoped
public class WishListManagedBean {
	private List<BookBean> wishList;
	private String response;
	private String error;

	public WishListManagedBean() {
		this.wishList = new ArrayList<BookBean>();
	}

	public List<BookBean> getWishList() {
		return wishList;
	}

	public void setWishList(List<BookBean> wishList) {
		this.wishList = wishList;
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
	
	public String addToWishList(BookBean book) {
		if (this.wishList != null && !this.wishList.contains(book)) {
			this.wishList.add(book);
			return "desejo.jsf";
		}
		return "";
	}
}
