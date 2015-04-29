package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.verificarExiste", query = "SELECT 1 FROM Usuario u where u.login = :login") })
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@NotNull
	@Id
	@SequenceGenerator(name = "USUARIO_USRID_GENERATOR", sequenceName = "SQ_USUARIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_USRID_GENERATOR")
	@Column(name = "usr_id")
	private Long id;

	/**
	 * 
	 */
	@NotNull
	@Column(name = "usr_lgn")
	private String login;

	/**
	 * 
	 */
	@NotNull
	@Column(name = "usr_nme")
	private String nome;

	/**
	 * 
	 */
	@NotNull
	@Column(name = "usr_sen")
	private String senha;

	/**
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "usuario_projeto", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = { @JoinColumn(name = "prj_id") })
	private List<Projeto> projetos;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return
	 */
	public List<Projeto> getProjetos() {
		return this.projetos;
	}

	/**
	 * @param projetos
	 */
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

}