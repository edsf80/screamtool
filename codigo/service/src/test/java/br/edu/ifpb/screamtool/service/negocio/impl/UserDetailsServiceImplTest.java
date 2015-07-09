package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Papel;
import br.edu.ifpb.screamtool.domain.entity.Permissao;
import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-application-context-test.xml" })
public class UserDetailsServiceImplTest {

	private UserDetailsServiceImpl userDetailsService;
	
	@Before
	public void setup() {
		userDetailsService = new UserDetailsServiceImpl();
	}
	
	@Test
	public void testLoadUserByUsernameSemProjeto() {
		Usuario usuarioSemNada = new Usuario();
		usuarioSemNada.setId(1l);
		usuarioSemNada.setNome("Teste de Pesquisa");
		usuarioSemNada.setPapeis(new ArrayList<Papel>());
		usuarioSemNada.setProjetos(new ArrayList<Projeto>());
		
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Mockito.when(usuarioDao.bucarPorLogin("testesp")).thenReturn(usuarioSemNada);
		
		userDetailsService.setUsuarioDao(usuarioDao);
		
		UsuarioVO userDetails = (UsuarioVO) userDetailsService.loadUserByUsername("testesp");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(userDetails.getId().equals(1l));
	}
	
	@Test
	public void testLoadUserByUsernameComProjeto() {
		Usuario usuarioPesquisadoPorLoginComProjeto = new Usuario();
		usuarioPesquisadoPorLoginComProjeto.setId(2l);
		usuarioPesquisadoPorLoginComProjeto.setNome("Teste de Pesquisa com Projeto");
		usuarioPesquisadoPorLoginComProjeto.setPapeis(new ArrayList<Papel>());
		
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
		Mockito.when(usuarioDao.bucarPorLogin("testecp")).thenReturn(usuarioPesquisadoPorLoginComProjeto);
		
		userDetailsService.setUsuarioDao(usuarioDao);
		
		UsuarioVO userDetails = (UsuarioVO) userDetailsService.loadUserByUsername("testecp");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(userDetails.getId().equals(2l));
		Assert.assertTrue(userDetails.getProjetos().size() > 0);
	}
	
	@Test
	public void testLoadUserByUsernameComPermissao() {
		Usuario usuarioComPerm = new Usuario();
		usuarioComPerm.setId(1l);
		usuarioComPerm.setNome("Teste de Pesquisa com Projeto");
		usuarioComPerm.setProjetos(new ArrayList<Projeto>());
		
		Permissao permissao = new Permissao();
		permissao.setId(1l);
		permissao.setDescricao("Permissao teste");
		permissao.setCodigo("perm_teste");
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(permissao);		
		
		
		Papel papel = new Papel();
		papel.setId(1l);
		papel.setDescricao("Papel Teste");
		papel.setPermissoes(permissoes);
		List<Papel> papeis = new ArrayList<>();
		papeis.add(papel);		
		usuarioComPerm.setPapeis(papeis);
		
		
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Mockito.when(usuarioDao.bucarPorLogin("testecp")).thenReturn(usuarioComPerm);
		
		userDetailsService.setUsuarioDao(usuarioDao);
		
		UsuarioVO userDetails = (UsuarioVO) userDetailsService.loadUserByUsername("testecp");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(userDetails.getId().equals(1l));
		Assert.assertTrue(userDetails.getAuthorities().size() > 0);
		Iterator<? extends GrantedAuthority> iterator = userDetails.getAuthorities().iterator();
		Assert.assertTrue(iterator.next().getAuthority().equals("perm_teste"));
	}
}
