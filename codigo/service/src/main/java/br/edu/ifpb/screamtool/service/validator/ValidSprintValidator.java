/**
 * 
 */
package br.edu.ifpb.screamtool.service.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.screamtool.data.dao.ReleaseDao;
import br.edu.ifpb.screamtool.data.dao.SprintDao;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.domain.entity.Sprint;

/**
 * @author edsf
 *
 */
public class ValidSprintValidator implements
		ConstraintValidator<ValidSprint, Sprint> {

	@Autowired
	private SprintDao sprintDao;

	@Autowired
	private ReleaseDao releaseDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(ValidSprint arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Sprint sprint, ConstraintValidatorContext context) {

		boolean resultado = Boolean.TRUE;

		if (sprint == null || sprint.getDataInicio() == null
				|| sprint.getDataTermino() == null
				|| sprint.getRelease() == null
				|| sprint.getRelease().getId() == null) {
			return Boolean.FALSE;
		}

		List<Sprint> sprints = null;

		Release release = releaseDao.buscarPorId(sprint.getRelease().getId());

		if (sprint.getId() != null) {

			sprints = sprintDao.buscarPorDatasNoProjetoDiferenteDoAtual(release
					.getProjeto().getId(), sprint.getId(), sprint
					.getDataInicio(), sprint.getDataTermino());
		} else {
			sprints = sprintDao.buscarPorDatasNoProjeto(release.getProjeto()
					.getId(), sprint.getDataInicio(), sprint.getDataTermino());
		}

		if (sprints.size() > 0) {
			resultado = Boolean.FALSE;
		}

		return resultado;
	}

}
