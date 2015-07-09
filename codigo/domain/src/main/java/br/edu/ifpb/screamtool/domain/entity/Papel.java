package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the papel database table.
 * 
 */
@Entity
@NamedQuery(name = "Papel.findAll", query = "SELECT p FROM Papel p")
public class Papel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_papel")
	@SequenceGenerator(name = "seq_papel", sequenceName = "seq_papel")
	@Column(name = "ppl_id")
	private Long id;

	/**
	 * 
	 */
	@Column(name = "ppl_dsc")
	private String descricao;

	/**
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "papel_permissao", joinColumns = { @JoinColumn(name = "ppl_id") }, inverseJoinColumns = { @JoinColumn(name = "prm_id") })
	private List<Permissao> permissoes;

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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the permissoes
	 */
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	/**
	 * @param permissoes
	 *            the permissoes to set
	 */
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}