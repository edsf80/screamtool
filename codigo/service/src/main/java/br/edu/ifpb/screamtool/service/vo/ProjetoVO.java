package br.edu.ifpb.screamtool.service.vo;

import java.io.Serializable;

public class ProjetoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5245368073991487622L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String nome;

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

}
