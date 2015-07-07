/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.io.Serializable;

/**
 * @author edsf
 *
 */
public class TableResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1324123040177904212L;
	
	private String data[][];
	
	public TableResult(int a, int b) {
		data = new String[a][b];
	}
	
	public void setData(String[][] data) {
		this.data = data;
	}
	
	public String[][] getData() {
		return data;
	}

}
