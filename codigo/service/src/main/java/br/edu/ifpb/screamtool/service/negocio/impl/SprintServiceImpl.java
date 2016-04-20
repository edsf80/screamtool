/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.SprintDao;
import br.edu.ifpb.screamtool.domain.entity.Sprint;
import br.edu.ifpb.screamtool.service.negocio.SprintService;
import br.edu.ifpb.screamtool.service.validator.ValidSprint;

/**
 * @author edsf
 *
 */
@Validated
@Service("sprintService")
public class SprintServiceImpl extends GenericServiceImpl<Sprint, Long>
		implements SprintService {

	@Autowired
	public SprintServiceImpl(SprintDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.SprintService#criarVerificacaoDatas
	 * (br.edu.ifpb.screamtool.domain.entity.Sprint)
	 */
	@Transactional
	@Override
	public Sprint criarVerificacaoDatas(@ValidSprint(message = "Período do sprint sobrepõe outro sprint") Sprint sprint) {

		return this.criar(sprint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.service.negocio.SprintService#
	 * atualizarVerificacaoDatas(br.edu.ifpb.screamtool.domain.entity.Sprint)
	 */
	@Transactional
	@Override
	public Sprint atualizarVerificacaoDatas(@ValidSprint(message = "Período do sprint sobrepõe outro sprint") Sprint sprint) {
		// TODO: Na hora de atualizar não pode considerar a sprint atual na
		// busca, do contrario nunca serão salvas.

		return this.atualizar(sprint);
	}

}
