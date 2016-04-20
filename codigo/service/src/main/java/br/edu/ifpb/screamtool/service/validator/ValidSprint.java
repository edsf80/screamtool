/**
 * 
 */
package br.edu.ifpb.screamtool.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author edsf
 *
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidSprintValidator.class })
@Documented
public @interface ValidSprint {

	String message() default "Sprint inválida";
	
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
