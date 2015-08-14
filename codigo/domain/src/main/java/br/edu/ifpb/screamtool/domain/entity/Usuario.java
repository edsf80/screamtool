package br.edu.ifpb.screamtool.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Usuario.buscarPorLogin", query = "SELECT u FROM Usuario u LEFT JOIN FETCH u.projetos LEFT JOIN FETCH u.papeis where u.login = :login"),
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.verificarExiste", query = "SELECT 1 FROM Usuario u where u.login = :login"),
		@NamedQuery(name = "Usuario.buscarTodosPorProjeto", query = "SELECT u FROM Usuario u JOIN FETCH u.projetos p where p.id = :projeto")})
public class Usuario extends EntidadeBasica {
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
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "usuario_projeto", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = { @JoinColumn(name = "prj_id") })
	private Set<Projeto> projetos;

	/**
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "usuario_papel", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = { @JoinColumn(name = "ppl_id") })
	private Set<Papel> papeis;

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
	public Set<Projeto> getProjetos() {
		return this.projetos;
	}

	/**
	 * @param projetos
	 */
	public void setProjetos(Set<Projeto> projetos) {
		this.projetos = projetos;
	}

	/**
	 * @return the papeis
	 */
	public Set<Papel> getPapeis() {
		return papeis;
	}

	/**
	 * @param papeis
	 *            the papeis to set
	 */
	public void setPapeis(Set<Papel> papeis) {
		this.papeis = papeis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.domain.entity.EntidadeBasica#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

}