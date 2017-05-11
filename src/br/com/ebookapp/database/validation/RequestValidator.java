package br.com.ebookapp.database.validation;

import br.com.ebookapp.book.bean.BookBean;
import br.com.ebookapp.user.bean.UserBean;

public class RequestValidator {
	public static boolean validateUser(UserBean user) {
		if (HandlerHelper.isBlankOrNull(user.getName())) {
			return false;
		}
		if (HandlerHelper.isBlankOrNull(user.getEmail())) {
			return false;
		}
		if (HandlerHelper.isBlankOrNull(user.getPassword())) {
			return false;
		}
		if (HandlerHelper.isBlankOrNull(user.getAddress())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateBook(BookBean book) {
		if (HandlerHelper.isBlankOrNull(book.getName())) {
			return false;
		}
		if (HandlerHelper.isBlankOrNull(book.getDescription())) {
			return false;
		}
		return true;
	}
}
