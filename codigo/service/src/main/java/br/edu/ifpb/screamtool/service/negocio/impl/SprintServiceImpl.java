/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.SprintDao;
import br.edu.ifpb.screamtool.domain.entity.Sprint;
import br.edu.ifpb.screamtool.service.negocio.SprintService;

/**
 * @author edsf
 *
 */
@Validated
@Service("sprintService")
public class SprintServiceImpl extends GenericServiceImpl<Sprint, Long> implements
		SprintService {

	@Autowired
	public SprintServiceImpl(SprintDao dao) {
		this.dao = dao;
	}
}
