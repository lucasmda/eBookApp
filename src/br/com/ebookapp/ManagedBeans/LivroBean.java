package br.com.ebookapp.ManagedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {
	private String titulo;
	private String descricao;
	private int codigo;
	private String dataLancamento;
	private Double preco;
	private Boolean desconto;
	private Double precoDesconto;
	private EditoraBean editora;
	private AutorBean autor;
	private AssuntoBean assunto;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Boolean getDesconto() {
		return desconto;
	}
	public void setDesconto(Boolean desconto) {
		this.desconto = desconto;
	}
	public Double getPrecoDesconto() {
		return precoDesconto;
	}
	public void setPrecoDesconto(Double precoDesconto) {
		this.precoDesconto = precoDesconto;
	}
	public EditoraBean getEditora() {
		return editora;
	}
	public void setEditora(EditoraBean editora) {
		this.editora = editora;
	}
	public AutorBean getAutor() {
		return autor;
	}
	public void setAutor(AutorBean autor) {
		this.autor = autor;
	}
	public AssuntoBean getAssunto() {
		return assunto;
	}
	public void setAssunto(AssuntoBean assunto) {
		this.assunto = assunto;
	}
}
