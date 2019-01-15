package br.com.gameker.webapp.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class GameModel {
	
	private Long codigo;
	 
	@NotEmpty(message ="O Nome é de preenchimento obrigatório.")
	private String nome;
 
	@NotEmpty(message ="A Nota é de preenchimento obrigatório.")
	private String nota;
	
	@NotEmpty(message ="O status é de preenchimento obrigatório.")
	private String status;
	
	@NotEmpty(message ="Não existe nenhuma plataforma selecionada.")
	private List<Long> plataformas; 
	
	public GameModel(){
		System.out.println("Passei " + LocalDate.now());
	}
 
	public GameModel(Long codigo, String nome, String nota, String status, List<Long> plataformas) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nota = nota;
		this.status = status;
		this.plataformas = plataformas;
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Long> getPlataformas() {
		return plataformas;
	}
	
	public void setPlataformas(List<Long> plataformas) {
		this.plataformas = plataformas;
	}
}
