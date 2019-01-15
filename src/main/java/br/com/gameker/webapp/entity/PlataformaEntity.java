package br.com.gameker.webapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="tb_plataforma",schema="gameker")
@Entity
public class PlataformaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_plataforma")
	private Long codigo;
	
	@Column(name="ds_nome")
	private String nome;
	
	@ManyToMany
	@JoinTable(
	name="tb_game_x_plataforma",
	joinColumns=@JoinColumn(name="id_plataforma", referencedColumnName="id_plataforma"),
	inverseJoinColumns=@JoinColumn(name="id_game", referencedColumnName="id_game")
	)
	private List<GameEntity> games;

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

	public List<GameEntity> getGames() {
		return games;
	}
	
	public void setGames(List<GameEntity> games) {
		this.games = games;
	}
}
