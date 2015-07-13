/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.io.Serializable;
import java.util.List;

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
	private List<String> errorMessages;

	/**
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages
	 *            the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
