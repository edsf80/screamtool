/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.service.vo.RegistroUsuarioVO;

/**
 * @author edsf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-application-context-test.xml" })
public class UsuarioServiceImplTest {

	//@InjectMocks
	@Autowired
	private UsuarioService usuarioService;
	
	@Mock
	//@Autowired
	private UsuarioDao usuarioDao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);		
		usuarioService.setDao(usuarioDao);
		
		
		/*usuarioCriar = new Usuario();
		usuarioCriar.setNome("Teste de criacao");
		usuarioCriado = new Usuario();
		usuarioCriado.setId(2l);
		usuarioCriado.setNome("Teste de criacao");

		
		Mockito.when(usuarioDao.criar(usuarioCriar)).thenReturn(usuarioCriado);*/

	}

	@Test
	public void testBuscarPorId() {
		Usuario usuarioDaBusca = new Usuario();
		usuarioDaBusca.setId(1l);
		Mockito.when(usuarioDao.buscarPorId(1l)).thenReturn(usuarioDaBusca);

		Usuario usuario = usuarioService.buscarPorId(1l);
		Assert.assertNotNull(usuario);
		
		usuario = usuarioService.buscarPorId(2l);
		Assert.assertNull(usuario);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testBuscarPorIdParamNulo() {
		
		usuarioService.buscarPorId(null);

	}
	
	@Test
	public void testCriar() {
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setLogin("teste");
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		Usuario usuarioCriado = new Usuario();
		usuarioCriado.setId(1l);
		Mockito.when(usuarioDao.criar(usuarioCriar)).thenReturn(usuarioCriado);
		
		Usuario usuario = usuarioService.criar(usuarioCriar);
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testCriarParamNuloEInvalido() {
		//Usuario sem login definido
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		usuarioService.criar(null);
		usuarioService.criar(usuarioCriar);
	}
	
	@Test
	public void testAtualizar() {
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setId(1l);
		usuarioCriar.setLogin("teste");
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		Mockito.when(usuarioDao.atualizar(usuarioCriar)).thenReturn(usuarioCriar);
		
		Usuario usuario = usuarioService.atualizar(usuarioCriar);
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId().equals(1l));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testAtualizarParamNuloEInvalido() {
		//Usuario sem login definido
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		usuarioService.atualizar(null);
		usuarioService.atualizar(usuarioCriar);
	}
	
	@Test
	public void testBuscarTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuarios.add(usuario);
		
		Mockito.when(usuarioDao.buscarTodos()).thenReturn(usuarios);
		
		List<Usuario> resultado = usuarioService.buscarTodos();
		Assert.assertNotNull(resultado);
		Assert.assertTrue(resultado.size() > 0);
	}
	
	@Test
	public void testBuscarTodosSemRetorno() {
		List<Usuario> usuarios = new ArrayList<>();
		
		Mockito.when(usuarioDao.buscarTodos()).thenReturn(usuarios);
		
		List<Usuario> resultado = usuarioService.buscarTodos();
		Assert.assertNotNull(resultado);
		Assert.assertTrue(resultado.size() == 0);
	}
	
	@Test
	public void testApagar() {
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setId(1l);
		usuarioCriar.setLogin("teste");
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		usuarioService.apagar(usuarioCriar);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testApagarParamNuloEInvalido() {
		//Usuario sem login definido
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		usuarioService.apagar(null);
		usuarioService.apagar(usuarioCriar);
	}
	
	@Test
	public void testRegistrarUsuario() {
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setLogin("teste");
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		RegistroUsuarioVO registroVO = new RegistroUsuarioVO();
		registroVO.setLogin("teste");
		registroVO.setNome("TESTE");
		registroVO.setSenha("teste");
		registroVO.setConfirmacaoSenha("teste");
		registroVO.setAcordoTermos(true);
		
		Assert.assertTrue(usuarioService.registrarUsuario(registroVO));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testRegistrarUsuarioParamNuloOuInvalido() {
		Usuario usuarioCriar = new Usuario();
		usuarioCriar.setLogin("teste");
		usuarioCriar.setNome("Teste");
		usuarioCriar.setSenha("teste");
		
		RegistroUsuarioVO registroVO = new RegistroUsuarioVO();
		registroVO.setLogin("teste");
		
		usuarioService.registrarUsuario(null);
		usuarioService.registrarUsuario(registroVO);
	}
}
