/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;
import java.util.Set;

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
import br.edu.ifpb.screamtool.domain.entity.Projeto;
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

	@Test
	@Transactional
	public void testeBuscarPorId() {

		Usuario usuario = usuarioDao.buscarPorId(20l);

		Assert.assertTrue(usuario != null);
		Assert.assertTrue(usuario.getProjetos().size() > 0);
	}

	@Test
	public void testeBuscarPorIdInexistente() {

		Usuario usuario = usuarioDao.buscarPorId(50l);

		Assert.assertTrue(usuario == null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeBuscarPorIdParamNulo() {

		usuarioDao.buscarPorId(null);

	}

	@Test
	@Transactional
	public void testeAlterar() {

		Usuario usuario = usuarioDao.buscarPorId(20l);

		usuario.setNome("Testando Alteracao");

		usuario = usuarioDao.atualizar(usuario);

		Assert.assertTrue(usuario.getNome().equals("Testando Alteracao"));

	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testeAlterarUsuarioNulo() {

		Usuario usuario = usuarioDao.buscarPorId(20l);

		usuario.setNome("Testando Alteracao");

		usuario = usuarioDao.atualizar(null);

		Assert.assertTrue(usuario.getNome().equals("Testando Alteracao"));

	}

	@Test
	@Transactional
	public void testeAlterarUsuarioSemAtributos() {

		usuarioDao.atualizar(new Usuario());

	}

	@Test
	public void testeBuscarPorLogin() {

		Usuario usuario = usuarioDao.bucarPorLogin("tstdev");
		Set<Projeto> projetos = usuario.getProjetos();

		Assert.assertNotNull(usuario);
		Assert.assertTrue(projetos.size()>0);
	}

	@Test
	public void testeBuscarPorLoginInexistente() {

		Usuario usuario = usuarioDao.bucarPorLogin("naoexiste");

		Assert.assertNull(usuario);

	}

	@Test
	public void testeBuscarPorLoginNulo() {

		Usuario usuario = usuarioDao.bucarPorLogin(null);

		Assert.assertNull(usuario);

	}

	@Test
	public void testeBuscarTodos() {

		List<Usuario> usuarios = usuarioDao.buscarTodos();

		Assert.assertNotNull(usuarios);
		Assert.assertNotNull(usuarios.size() > 0);

	}

	@Test
	public void testeVerificarLoginExiste() {

		boolean resultado = usuarioDao.verificarLoginExiste("tstdev");

		Assert.assertTrue(resultado);

	}
	
	@Test
	public void testeVerificarLoginExisteLoginInexistente() {

		boolean resultado = usuarioDao.verificarLoginExiste("naoexiste");

		Assert.assertFalse(resultado);

	}
	
	@Test
	public void testeVerificarLoginExisteParametroNulo() {

		boolean resultado = usuarioDao.verificarLoginExiste(null);

		Assert.assertFalse(resultado);

	}

}
