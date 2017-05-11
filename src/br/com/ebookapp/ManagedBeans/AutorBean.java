package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.author.bean.AuthorBean;
import br.com.ebookapp.author.request.RequestHandler;
import br.com.ebookapp.database.validation.HandlerHelper;

@ManagedBean
public class AutorBean {
//	private Autor autor = new Author();
	private AuthorBean author = null;
	private List<AuthorBean> authorList = null;
	private RequestHandler requestHandler = null;
	private String response = "";
	private String error = "";
	
	public AutorBean() {
		this.requestHandler = new RequestHandler();
		
		this.author = new AuthorBean();
		this.authorList = this.requestHandler.getAuthor();
	}
	
	public List<AuthorBean> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<AuthorBean> authorList) {
		this.authorList = authorList;
	}

	public AuthorBean getAuthor() {
		return author;
	}

	public void setAuthor(AuthorBean author) {
		this.author = author;
	}

	public List<AuthorBean> getList() {
		return authorList;
	}

	public void setList(List<AuthorBean> authorList) {
		this.authorList = authorList;
	}

	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getResponse() {
		return response;
	}

	public String getError() {
		return error;
	}
	
	public String registerNewAuthor() {
		try {
			if (HandlerHelper.isBlankOrNull(this.author.getName())) {
				boolean isSuccess = requestHandler.createAuthor(this.author.getName());
				if (isSuccess) {
					this.author.setName(null);
				}
			}
			return response;
		}catch(Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}

	public String editAuthor() {
		try {
			this.author = requestHandler.getAuthor(this.author.getAuthor_id());
			return "edit_autor";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String deleteAuthor() {
		try {
			if (!HandlerHelper.isBlankOrNull(String.valueOf(this.author.getAuthor_id()))) {
				boolean isSuccess = requestHandler.deleteAuthor(this.author.getAuthor_id());
				if (isSuccess) {
					this.authorList = requestHandler.getAuthor();
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String updateAuthor() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.author.getName())) {
				boolean isSuccess = requestHandler.updateAuthor(this.author, this.author.getAuthor_id());
				if (isSuccess) {
					this.author.setName(null);
					this.authorList = requestHandler.getAuthor();
					return "index_autor";
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}
}
