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

@Table(name="tb_usuario",schema="gameker")
@Entity
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long codigo;
	
	@Column(name="ds_nome")
	private String nome;
	
	@Column(name="ds_login")
	private String login;
	
	@Column(name="ds_senha")
	private String senha;
	
	@Column(name="fl_ativo")
	private boolean ativo;
	
	@JoinTable(name = "tb_usuario_x_grupo", 
	joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, 
	inverseJoinColumns = {@JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")}
    )
	@ManyToMany(cascade ={ CascadeType.PERSIST, CascadeType.MERGE})	
	private List<GrupoEntity> grupos;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<GrupoEntity> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoEntity> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public boolean equals(Object obj) {
		UsuarioEntity usuario = (UsuarioEntity) obj;
		return usuario.getLogin().equals(this.getLogin()) && usuario.getSenha().equals(this.getSenha());
	}
}
