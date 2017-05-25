package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.database.validation.RequestValidator;
import br.com.ebookapp.user.bean.UserBean;
import br.com.ebookapp.user.request.RequestHandler;

@ManagedBean
@SessionScoped
public class LoginSessionManagedBean {
	private UserBean user;
	private RequestHandler requestHandler;
	private String email;
	private String password;
	private String response;
	private String error;
	
	public LoginSessionManagedBean() {
		this.requestHandler = new RequestHandler();
		
		this.user = new UserBean();
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String loginUser() {
		if (!HandlerHelper.isBlankOrNull(this.email) && !HandlerHelper.isBlankOrNull(this.password)) {
			this.user = requestHandler.signin(this.email, this.password);
			if (this.user != null) {
				this.email = null;
				this.password = null;
				return this.user.getName().equals("root") ? "/views/home_admin/home_admin.jsf" : "app.jsf";
			}
		}
		
		return "login.jsf";
	}

	public String registerNewUser() {
		if (RequestValidator.validateUser(user)) {
			boolean isSuccess = requestHandler.createUser(this.user);
			if (isSuccess) {
				this.user = new UserBean();
				
				return "app.jsf";
			}
		}
		return "cadastroUsuario.jsf";
	}
}
