/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.RiscoDao;
import br.edu.ifpb.screamtool.domain.entity.Risco;
import br.edu.ifpb.screamtool.service.negocio.RiscoService;

/**
 * @author edsf
 *
 */
@Validated
@Service("riscoService")
public class RiscoServiceImpl extends GenericServiceImpl<Risco, Long> implements
		RiscoService {

	/**
	 * @param dao
	 */
	@Autowired
	public RiscoServiceImpl(RiscoDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.RiscoSerivce#buscarTodosPorProjeto
	 * (java.lang.Long)
	 */
	public List<Risco> buscarTodosPorProjeto(@NotNull Long idProjeto) {

		RiscoDao riscoDao = (RiscoDao) this.dao;

		return riscoDao.buscarTodosPorProjeto(idProjeto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.RiscoService#buscarPorIdSemAssociacoes
	 * (java.lang.Long)
	 */
	@Transactional
	public Risco buscarPorIdSemAssociacoes(@NotNull Long id) {
		RiscoDao riscoDao = (RiscoDao) this.dao;

		return riscoDao.buscarPorIdSemAssociacoes(id);
	}

}
