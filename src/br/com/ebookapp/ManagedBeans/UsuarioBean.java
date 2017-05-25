package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.database.validation.RequestValidator;
import br.com.ebookapp.user.bean.UserBean;
import br.com.ebookapp.user.request.RequestHandler;

@ManagedBean
public class UsuarioBean {
	private UserBean user;
	private List<UserBean> userList;
	private RequestHandler requestHandler;
	private String response;
	private String error;
	
	public UsuarioBean() {
		this.requestHandler = new RequestHandler();
		
		this.user = new UserBean();
		this.userList = this.requestHandler.getUser();
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
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

	public String registerNewUser() {
		try {
			if (RequestValidator.validateUser(user)) {
				boolean isSuccess = requestHandler.createUser(this.user);
				if (isSuccess) {
					this.user.setName(null);
					this.user.setEmail(null);
					this.user.setPassword(null);
					this.user.setAddress(null);
				}
			}
			return response;
		} catch(Exception e){
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String editUser() {
		try {
			this.user = requestHandler.getUser(this.user.getUser_id());
			return "edit_usuario";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String deleteUser() {
		try {
			if (!HandlerHelper.isBlankOrNull(String.valueOf(this.user.getUser_id()))) {
				boolean isSuccess = requestHandler.deleteUser(this.user.getUser_id());
				if (isSuccess) {
					this.userList = requestHandler.getUser();
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String updateUser() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.user.getName())) {
				boolean isSuccess = requestHandler.updateUser(this.user, this.user.getUser_id());
				if (isSuccess) {
					this.user.setName(null);
					this.user.setEmail(null);
					this.user.setPassword(null);
					this.user.setAddress(null);
					this.userList = requestHandler.getUser();
					return "index_usuario";
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}
	
	public void validateEmailField(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
		if (!HandlerHelper.validateValidEmail(value.toString())) {
			throw new ValidatorException(new FacesMessage("Please input a valid e-mail"));
		}
	}
}
