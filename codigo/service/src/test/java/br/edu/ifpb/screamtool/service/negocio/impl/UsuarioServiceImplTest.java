/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;

/**
 * @author edsf
 *
 */
public class UsuarioServiceImplTest {

	private UsuarioServiceImpl usuarioService;

	private Usuario usuarioDaBusca, usuarioCriar, usuarioCriado;

	@Before
	public void setup() {
		usuarioDaBusca = new Usuario();
		usuarioDaBusca.setId(1l);
		usuarioCriar = new Usuario();
		usuarioCriar.setNome("Teste de criacao");
		usuarioCriado = new Usuario();
		usuarioCriado.setId(2l);
		usuarioCriado.setNome("Teste de criacao");

		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Mockito.when(usuarioDao.buscarPorId(1l)).thenReturn(usuarioDaBusca);
		Mockito.when(usuarioDao.criar(usuarioCriar)).thenReturn(usuarioCriado);

		usuarioService = new UsuarioServiceImpl(usuarioDao);
	}

	@Test
	public void testBuscarPorId() {

		Usuario usuario = usuarioService.buscarPorId(1l);

		Assert.assertNotNull(usuario);
	}
}
