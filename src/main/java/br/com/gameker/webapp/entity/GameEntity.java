package br.com.gameker.webapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="tb_game",schema="gameker")
@Entity
public class GameEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_game")
	private Long codigo;
	
	@Column(name="ds_nome")
	private String nome;
	
	@Column(name="nr_nota")
	private String nota;
	
	@Column(name="nr_status")
	private String status;
	
	@JoinTable(name = "tb_game_x_plataforma", 
	joinColumns = {@JoinColumn(name = "id_game", referencedColumnName = "id_game")}, 
	inverseJoinColumns = {@JoinColumn(name = "id_plataforma", referencedColumnName = "id_plataforma")}
    )
	@ManyToMany(cascade ={ CascadeType.PERSIST, CascadeType.MERGE})	
	private List<PlataformaEntity> plataformas;

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

	public List<PlataformaEntity> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<PlataformaEntity> plataformas) {
		this.plataformas = plataformas;
	}
}
