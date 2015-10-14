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

import br.edu.ifpb.screamtool.data.dao.ReleaseDao;
import br.edu.ifpb.screamtool.domain.entity.Release;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("/testdataset.xml")
public class ReleaseDaoImplTest {
	
	@Autowired
	@Qualifier("releaseDao")
	private ReleaseDao releaseDao;
	
	@Test
	public void testeBuscarTodosProjeto() {
		
		List<Release> releases = releaseDao.buscarPorProjeto(1l);
		
		Assert.assertNotNull(releases);
		Assert.assertTrue(releases.size() > 0);
		Assert.assertTrue(releases.get(0).getSprints().size() > 0);
	}
	
	@Test
	public void testeBuscarTodosProjetoInexistente() {
		
		List<Release> releases = releaseDao.buscarPorProjeto(20l);
		
		Assert.assertNotNull(releases);
		Assert.assertTrue(releases.size() == 0);
	}
	
	@Test
	public void testeBuscarTodosProjetoNulo() {
		
		List<Release> releases = releaseDao.buscarPorProjeto(null);
		
		Assert.assertNotNull(releases);
		Assert.assertTrue(releases.size() == 0);
	}

}
