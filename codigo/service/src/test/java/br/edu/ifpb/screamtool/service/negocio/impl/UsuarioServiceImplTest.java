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
	
	@Before
	public void setup() {
		Usuario usuarioDaBusca = new Usuario();
		usuarioDaBusca.setId(1l);
		
		
		usuarioService = new UsuarioServiceImpl();
		
		
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Mockito.when(usuarioDao.buscarPorId(1l)).thenReturn(usuarioDaBusca);
		
		
		usuarioService.setUsuarioDao(usuarioDao);
	}
	
	@Test
	public void testBuscarPorId() {
		
		Usuario usuario = usuarioService.buscarPorId(1l);
		
		Assert.assertNotNull(usuario);
	}

}
