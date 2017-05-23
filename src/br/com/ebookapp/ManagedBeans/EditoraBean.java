package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.publisher.bean.PublisherBean;
import br.com.ebookapp.publisher.request.RequestHandler;

@ManagedBean
public class EditoraBean {
	private PublisherBean publisher;
	private List<PublisherBean> publisherList;
	private RequestHandler requestHandler;
	private String response;
	private String error;
	
	public EditoraBean() {
		this.requestHandler = new RequestHandler();
		
		this.publisher = new PublisherBean();
		this.publisherList = this.requestHandler.getPublisher();
	}

	public PublisherBean getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}

	public List<PublisherBean> getPublisherList() {
		return publisherList;
	}

	public void setPublisherList(List<PublisherBean> publisherList) {
		this.publisherList = publisherList;
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

	public String registerNewPublisher() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.publisher.getName()) && !HandlerHelper.isBlankOrNull(this.publisher.getDescription())) {
				boolean isSuccess = requestHandler.createPublisher(this.publisher);
				if (isSuccess) {
					this.publisher.setName(null);
					this.publisher.setDescription(null);
				}
			}
			return response;
		} catch(Exception e){
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String editPublisher() {
		try {
			this.publisher = requestHandler.getPublisher(this.publisher.getPublisher_id());
			return "edit_editora";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String deletePublisher() {
		try {
			if (!HandlerHelper.isBlankOrNull(String.valueOf(this.publisher.getPublisher_id()))) {
				boolean isSuccess = requestHandler.deletePublisher(this.publisher.getPublisher_id());
				if (isSuccess) {
					this.publisherList = requestHandler.getPublisher();
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String updatePublisher() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.publisher.getName())) {
				boolean isSuccess = requestHandler.updatePublisher(this.publisher, this.publisher.getPublisher_id());
				if (isSuccess) {
					this.publisher.setName(null);
					this.publisherList = requestHandler.getPublisher();
					return "index_editora";
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}
}
