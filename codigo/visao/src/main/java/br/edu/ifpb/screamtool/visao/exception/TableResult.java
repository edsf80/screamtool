/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.io.Serializable;

/**
 * @author edsf
 *
 */
public class TableResult<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1324123040177904212L;
	
	private T data[];
	
	public void setData(T[] data) {
		this.data = data;
	}
	
	public T[] getData() {
		return data;
	}

}
