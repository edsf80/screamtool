/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.ReleaseDao;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.service.negocio.ReleaseService;

/**
 * @author edsf
 *
 */
@Validated
@Service("releaseService")
public class ReleaseServiceImpl extends GenericServiceImpl<Release, Long>
		implements ReleaseService {

	@Autowired
	public ReleaseServiceImpl(ReleaseDao releaseDao) {
		this.dao = releaseDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.ReleaseService#buscarPorProjeto
	 * (java.lang.Long)
	 */
	@Override
	public List<Release> buscarPorProjeto(@NotNull Long idProjeto) {

		ReleaseDao releaseDao = (ReleaseDao) this.dao;

		List<Release> releases = releaseDao.buscarPorProjeto(idProjeto);

		return releases;
	}
}
