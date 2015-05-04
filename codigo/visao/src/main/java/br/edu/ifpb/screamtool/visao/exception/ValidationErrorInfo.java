/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.io.Serializable;

/**
 * @author edsf
 *
 */
public class ValidationErrorInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130706908054635528L;

	/**
	 * 
	 */
	private String errorMessages;

	/**
	 * @return the errorMessages
	 */
	public String getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages
	 *            the errorMessages to set
	 */
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

}
