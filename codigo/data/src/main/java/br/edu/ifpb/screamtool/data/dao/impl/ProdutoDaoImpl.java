/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.ProdutoDao;
import br.edu.ifpb.screamtool.domain.entity.Produto;

/**
 * @author edsf
 *
 */
@Repository("produtoDao")
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Long> implements
		ProdutoDao {

}
