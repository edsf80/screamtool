/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.ProjetoDao;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.negocio.ProjetoService;

/**
 * @author edsf
 *
 */
@Validated
@Service("projetoService")
public class ProjetoServiceImpl extends GenericServiceImpl<Projeto, Long> implements
		ProjetoService {
	
	@Autowired
	public ProjetoServiceImpl(ProjetoDao dao) {
		this.dao = dao;
	}

}
