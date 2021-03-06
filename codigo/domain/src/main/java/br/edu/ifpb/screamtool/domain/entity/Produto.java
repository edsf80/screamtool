package br.edu.ifpb.screamtool.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto extends EntidadeBasica {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUTO_PRDID_GENERATOR", sequenceName = "seq_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_PRDID_GENERATOR")
	@Column(name = "prd_id")
	private Long id;

	@NotNull(message = "O nome do produto deve ser informado")
	@Column(name = "prd_dsc")
	private String descricao;

	public Produto() {
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