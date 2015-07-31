package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;

public abstract class EntidadeBasica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3347012291986207784L;

	/**
	 * @return
	 */
	public abstract Long getId();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int resultado;

		if (this.getId() == null) {
			resultado = super.hashCode();
		} else {
			resultado = this.getId().intValue();
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
			EntidadeBasica eb = (EntidadeBasica) obj;

			if ((eb.getId() == this.getId()) || eb.getId().equals(this.getId())) {
				resultado = Boolean.TRUE;
			}
		}

		return resultado;
	}

}
