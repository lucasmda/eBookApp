package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AssuntoBean {
	private String assunto;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
}
