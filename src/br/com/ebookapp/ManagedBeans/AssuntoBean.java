package br.com.ebookapp.ManagedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.database.validation.HandlerHelper;
import br.com.ebookapp.subject.bean.SubjectBean;
import br.com.ebookapp.subject.request.RequestHandler;

@ManagedBean
public class AssuntoBean {
//	private Assunto assunto = new Assunto();
	private SubjectBean subject = null;
	private List<SubjectBean> subjectList = null;
	private RequestHandler requestHandler = null;
	private String response = "";
	private String error = "";
	
	public AssuntoBean() {
		this.requestHandler = new RequestHandler();
		
		this.subject = new SubjectBean();
		this.subjectList = this.requestHandler.getSubject();
	}

	public List<SubjectBean> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectBean> subjectList) {
		this.subjectList = subjectList;
	}

	public SubjectBean getSubject() {
		return subject;
	}

	public void setSubject(SubjectBean subject) {
		this.subject = subject;
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

	public String registerNewSubject() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.subject.getName())) {
				boolean isSuccess = requestHandler.createSubject(this.subject);
				if (isSuccess) {
					this.subject.setName(null);
				}
			}
			return response;
		} catch(Exception e){
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String editSubject() {
		try {
			this.subject = requestHandler.getSubject(this.subject.getSubject_id());
			return "edit_assunto";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String deleteSubject() {
		try {
			if (!HandlerHelper.isBlankOrNull(String.valueOf(this.subject.getSubject_id()))) {
				boolean isSuccess = requestHandler.deleteSubject(this.subject.getSubject_id());
				if (isSuccess) {
					this.subjectList = requestHandler.getSubject();
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "erro";
		}
	}
	
	public String updateSubject() {
		try {
			if (!HandlerHelper.isBlankOrNull(this.subject.getName())) {
				boolean isSuccess = requestHandler.createSubject(this.subject);
				if (isSuccess) {
					this.subject.setName(null);
					return "index_asssunto";
				}
			}
			return response;
		} catch (Exception e) {
			this.error = e.getMessage();
			return "error";
		}
	}
}
