/**
 * 
 */
package br.edu.ifpb.screamtool.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;

/**
 * @author edsf
 *
 */
public class UniqueUsernameValidator implements
		ConstraintValidator<UniqueUsername, String> {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	public void initialize(UniqueUsername uniqueUsername) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	public boolean isValid(String valor, ConstraintValidatorContext context) {

		return !usuarioDao.verificarLoginExiste(valor);
	}

}
