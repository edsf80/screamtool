/**
 * 
 */
package br.edu.ifpb.screamtool.visao.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author edsf
 *
 */
@ControllerAdvice("br.edu.ifpb.screamtool.visao.rest")
public class RestExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ValidationErrorInfo> handleErroValidacao(
			HttpServletRequest req, ConstraintViolationException cvex) {

		Set<ConstraintViolation<?>> violacoes = cvex.getConstraintViolations();
		Iterator<ConstraintViolation<?>> iterator = violacoes.iterator();

		List<String> messages = new ArrayList<>();

		while (iterator.hasNext()) {
			messages.add(iterator.next().getMessage());
		}

		ValidationErrorInfo validationErrorInfo = new ValidationErrorInfo();
		validationErrorInfo.setErrorMessages(messages);
		ResponseEntity<ValidationErrorInfo> responseEntity = new ResponseEntity<ValidationErrorInfo>(
				validationErrorInfo, 200);

		return responseEntity;
	}

}
