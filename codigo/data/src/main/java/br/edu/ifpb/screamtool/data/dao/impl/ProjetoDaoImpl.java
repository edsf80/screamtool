/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.ProjetoDao;
import br.edu.ifpb.screamtool.domain.entity.Projeto;

/**
 * @author edsf
 *
 */
@Repository("projetoDao")
public class ProjetoDaoImpl extends GenericDaoImpl<Projeto, Long> implements
		ProjetoDao {

}
