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
			ProjetoVO eb = (ProjetoVO) obj;

			if ((eb.getId() == this.getId()) || eb.getId().equals(this.getId())) {
				resultado = Boolean.TRUE;
			}
		}

		return resultado;
	}

}
