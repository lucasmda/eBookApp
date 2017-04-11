package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.Models.Assunto;

@ManagedBean
public class AssuntoBean {
	private Assunto assunto = new Assunto();
	
	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	private String response = "";
	private String error;

	public String getResponse() {
		return response;
	}

	public String getError() {
		return error;
	}

	public String cadastrarAssunto(){
		try{
			return response;
		}catch(Exception e){
			return error = e.getMessage();
		}
	}
}
