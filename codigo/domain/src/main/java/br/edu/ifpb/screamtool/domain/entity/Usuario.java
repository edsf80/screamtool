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
		@NamedQuery(name = "Usuario.buscarPorLogin", query = "SELECT u FROM Usuario u where u.login = :login"),
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
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")	
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
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_projeto", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = { @JoinColumn(name = "prj_id") })
	private List<Projeto> projetos;

	/**
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_papel", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = { @JoinColumn(name = "ppl_id") })
	private List<Papel> papeis;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int resultado;

		if (this.id == null) {
			resultado = super.hashCode();
		} else {
			resultado = this.id.intValue();
		}

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		boolean resultado = Boolean.FALSE;

		if (obj != null) {
			Usuario eb = (Usuario) obj;

			if ((eb.getId() == this.getId()) || eb.getId().equals(this.getId())) {
				resultado = Boolean.TRUE;
			}
		}

		return resultado;
	}

	/**
	 * @return the papeis
	 */
	public List<Papel> getPapeis() {
		return papeis;
	}

	/**
	 * @param papeis the papeis to set
	 */
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

}