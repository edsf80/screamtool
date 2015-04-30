/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.io.Serializable;

/**
 * @author edsf
 *
 */
public class ResponseEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6418194596327112608L;

	private T objeto;

	private int status;

	public ResponseEntity(int codigo) {
		this.status = codigo;
	}

	public ResponseEntity(T objeto, int codigo) {
		this(codigo);
		this.objeto = objeto;
	}

	/**
	 * @return the objeto
	 */
	public T getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto
	 *            the objeto to set
	 */
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
