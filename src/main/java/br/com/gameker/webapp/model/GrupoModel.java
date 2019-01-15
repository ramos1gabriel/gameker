package br.com.gameker.webapp.model;

public class GrupoModel {
	private Long codigo;
	private String nome;	
	private String descricao;
	private Boolean checked;
 
	public GrupoModel(){}
 
	public GrupoModel(Long codigo, String nome, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}
	public GrupoModel(Long codigo,String descricao) {
		super();
		this.codigo = codigo;	
		this.descricao = descricao;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getChecked() {
		return checked;
	}
 
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
