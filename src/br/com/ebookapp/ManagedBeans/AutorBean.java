package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;

import br.com.ebookapp.Models.Autor;

@ManagedBean
public class AutorBean {
	private Autor autor = new Autor();
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	private String response = "";
	private String error;
	
	public String getResponse() {
		return response;
	}

	public String getError() {
		return error;
	}
	
	public String cadastrarAutor(){
		try{
			System.out.println(autor.getNome());
			return response;
		}catch(Exception e){
			return error = e.getMessage();
		}
	}
}
