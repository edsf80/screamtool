/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.ProdutoDao;
import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;

/**
 * @author edsf
 *
 */
@Validated
@Service("produtoService")
public class ProdutoServiceImpl extends GenericServiceImpl<Produto, Long>
		implements ProdutoService {

	@Autowired
	public ProdutoServiceImpl(ProdutoDao dao) {
		this.dao = dao;
	}

}
