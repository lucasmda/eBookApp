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
	private String payment;
	private String creditCard;
	private String response;
	private String error;
	
	public CartManagedBean() {
		this.cartList = new ArrayList<BookBean>();
	}
	
	public double getTotalPrice() {
		double count = 0;
		if (this.cartList != null && this.cartList.size() > 0) {
			for (BookBean book : this.cartList) {
				if (book != null) {
					count += (book.getPriceWithDiscount() * book.getAmmount());
				}
			}
			
			if (this.payment != null && this.payment.length() > 0) {
				count += Double.parseDouble(this.payment);
			}
		}
		
		return count;
	}

	public List<BookBean> getCartList() {
		return cartList;
	}

	public void setCartList(List<BookBean> cartList) {
		this.cartList = cartList;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
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
	
	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public void addToCart(BookBean book) {
		if (this.cartList != null && !this.cartList.contains(book)) {
			this.cartList.add(book);
		}
	}
	
	public void removeFromCart(BookBean book) {
		if (this.cartList != null && this.cartList.contains(book)) {
			this.cartList.remove(book);
		}
	}
	
	public String finishCart() {
		if (this.cartList.size() > 0) {
			this.cartList.clear();
			return "checkout";
		}
		return "carrinho";
	}
}
