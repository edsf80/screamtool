/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.edu.ifpb.screamtool.data.dao.ItemBacklogDao;
import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author edsf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("/testdataset.xml")
public class ItemBacklogDaoImplTest {
	
	@Autowired
	@Qualifier("itemBacklogDao")
	private ItemBacklogDao itemBacklogDao;

	@Test
	public void testeBuscarTodosPorProduto() {

		List<ItemBacklog> itens = itemBacklogDao.buscarTodosPorProduto(20l);
		
		Assert.assertNotNull(itens);
		Assert.assertTrue(itens.size() > 0);

	}
	
	@Test
	public void testeBuscarTodosPorProdutoSemItens() {

		List<ItemBacklog> itens = itemBacklogDao.buscarTodosPorProduto(1l);
		
		Assert.assertNotNull(itens);
		Assert.assertTrue(itens.size() == 0);

	}
	
	@Test
	public void testeBuscarTodosPorProdutoInexistente() {

		List<ItemBacklog> itens = itemBacklogDao.buscarTodosPorProduto(100l);
		
		Assert.assertNotNull(itens);
		Assert.assertTrue(itens.size() == 0);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testeBuscarTodosPorProdutoNulo() {

		List<ItemBacklog> itens = itemBacklogDao.buscarTodosPorProduto(null);
		
		Assert.assertNotNull(itens);
		Assert.assertTrue(itens.size() == 0);

	}
}
