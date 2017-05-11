package br.com.ebookapp.database.validation;

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
}
