/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.SprintDao;
import br.edu.ifpb.screamtool.domain.entity.Sprint;

/**
 * @author edsf
 *
 */
@Repository("sprintDao")
public class SprintDaoImpl extends GenericDaoImpl<Sprint, Long> implements
		SprintDao {

}
