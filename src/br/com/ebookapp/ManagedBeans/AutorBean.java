package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AutorBean {
	private String nome;
	private int idade;
	private String pais;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}
