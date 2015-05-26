package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

public class UserDetailsServiceImplTest {

	private UserDetailsServiceImpl userDetailsService;
	
	private Usuario usuarioPesquisadoPorLoginSemProjeto;
	
	private Usuario usuarioPesquisadoPorLoginComProjeto;
	
	@Before
	public void setup() {
		userDetailsService = new UserDetailsServiceImpl();
		usuarioPesquisadoPorLoginSemProjeto = new Usuario();
		usuarioPesquisadoPorLoginSemProjeto.setId(1l);
		usuarioPesquisadoPorLoginSemProjeto.setNome("Teste de Pesquisa");
		
		usuarioPesquisadoPorLoginComProjeto = new Usuario();
		usuarioPesquisadoPorLoginComProjeto.setId(2l);
		usuarioPesquisadoPorLoginComProjeto.setNome("Teste de Pesquisa com Projeto");
		
		Produto produto = new Produto();
		produto.setId(1l);
		Projeto projeto = new Projeto();
		projeto.setId(1l);
		projeto.setNome("Projeto Teste");
		projeto.setProduto(produto);
		
		List<Projeto> projetos = new ArrayList<>();
		projetos.add(projeto);
		usuarioPesquisadoPorLoginComProjeto.setProjetos(projetos);
		
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Mockito.when(usuarioDao.bucarPorLogin("testesp")).thenReturn(usuarioPesquisadoPorLoginSemProjeto);
		Mockito.when(usuarioDao.bucarPorLogin("testecp")).thenReturn(usuarioPesquisadoPorLoginComProjeto);
		
		userDetailsService.setUsuarioDao(usuarioDao);
	}
	
	@Test
	public void testLoadUserByUsernameSemProjeto() {
		UsuarioVO userDetails = (UsuarioVO) userDetailsService.loadUserByUsername("testesp");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(userDetails.getId().equals(1l));
		Assert.assertNull(userDetails.getProjetos());
	}
	
	@Test
	public void testLoadUserByUsernameComProjeto() {
		UsuarioVO userDetails = (UsuarioVO) userDetailsService.loadUserByUsername("testecp");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(userDetails.getId().equals(2l));
		Assert.assertTrue(userDetails.getProjetos().size() > 0);
	}
}
