/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

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

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;

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
public class UsuarioDaoImplTest {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;

	@Test
	@Transactional
	public void testeCriar() {

		Usuario usuario = new Usuario();
		usuario.setLogin("teste");
		usuario.setNome("Teste de Criacao");
		usuario.setSenha("senha");

		Assert.assertTrue(usuarioDao.criar(usuario).getId() != null);
	}
	
	@Test
	@Transactional
	public void testeCriarAtributoNulo() {

		Usuario usuario = new Usuario();

		Assert.assertTrue(usuarioDao.criar(usuario).getId() != null);
	}

}
