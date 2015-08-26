/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

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
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.screamtool.data.dao.ProdutoDao;
import br.edu.ifpb.screamtool.domain.entity.Produto;

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
public class ProdutoDaoImplTest {

	@Autowired
	@Qualifier("produtoDao")
	private ProdutoDao produtoDao;

	@Test
	@Transactional
	public void testeCriar() {

		Produto produto = new Produto();
		produto.setDescricao("Produto Teste");

		Assert.assertTrue(produtoDao.criar(produto).getId() != null);
	}

	@Test
	@Transactional
	public void testeCriarAtributoNulo() {

		Produto produto = new Produto();

		Assert.assertTrue(produtoDao.criar(produto).getId() != null);
	}

	@Test
	@Transactional
	public void testeBuscarPorId() {

		Produto produto = produtoDao.buscarPorId(20l);

		Assert.assertTrue(produto != null);
	}

	@Test
	public void testeBuscarPorIdInexistente() {

		Produto produto = produtoDao.buscarPorId(50l);

		Assert.assertTrue(produto == null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeBuscarPorIdParamNulo() {

		produtoDao.buscarPorId(null);

	}

	@Test
	@Transactional
	public void testeAlterar() {

		Produto produto = produtoDao.buscarPorId(20l);

		produto.setDescricao("Testando Alteracao");

		produto = produtoDao.atualizar(produto);

		Assert.assertTrue(produto.getDescricao().equals("Testando Alteracao"));

	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testeAlterarUsuarioNulo() {

		Produto produto = produtoDao.buscarPorId(20l);

		produto.setDescricao("Testando Alteracao");

		produto = produtoDao.atualizar(null);

		Assert.assertTrue(produto.getDescricao().equals("Testando Alteracao"));

	}

	@Test(expected = PersistenceException.class)
	@Transactional
	public void testeAlterarProdutoSemAtributos() {

		produtoDao.atualizar(new Produto());

	}

	@Test
	public void testeBuscarTodos() {

		List<Produto> produtos = produtoDao.buscarTodos();

		Assert.assertNotNull(produtos);
		Assert.assertNotNull(produtos.size() > 0);

	}
}
