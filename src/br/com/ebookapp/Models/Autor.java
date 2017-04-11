package br.com.ebookapp.Models;

public class Autor {
	private String nome;
	private int anoNascimento;
	private String pais;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoNascimento() {
		return anoNascimento;
	}
	public void setIdade(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}
