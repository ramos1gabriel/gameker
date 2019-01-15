package br.com.gameker.webapp.model;

public class PlataformaModel {
	private Long codigo;
	private String nome;
	private Boolean checked;
 
	public PlataformaModel(){}
 
	public PlataformaModel(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public PlataformaModel(Long codigo) {
		super();
		this.codigo = codigo;
	}
 
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
