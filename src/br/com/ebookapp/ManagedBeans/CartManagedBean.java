package br.com.ebookapp.ManagedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.book.bean.BookBean;

@ManagedBean
@SessionScoped
public class CartManagedBean {
	private List<BookBean> cartList;
	private String response;
	private String error;
	
	public CartManagedBean() {
		this.cartList = new ArrayList<BookBean>();
	}
	
	public List<BookBean> getCartList() {
		return cartList;
	}

	public void setCartList(List<BookBean> cartList) {
		this.cartList = cartList;
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

	public void addNewItem(BookBean book) {
		System.out.println(book.getName());
	}
}
