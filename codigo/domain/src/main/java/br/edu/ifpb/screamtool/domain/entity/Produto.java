package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUTO_PRDID_GENERATOR", sequenceName = "seq_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_PRDID_GENERATOR")
	@Column(name = "prd_id")
	private Long id;

	@Column(name = "prd_dsc")
	private String descricao;

	public Produto() {
	}

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

}