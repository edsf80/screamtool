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

import br.edu.ifpb.screamtool.data.dao.RiscoDao;
import br.edu.ifpb.screamtool.domain.entity.Risco;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("/testdataset.xml")
public class RiscoDaoImplTest {
	
	@Autowired
	@Qualifier("riscoDao")
	private RiscoDao riscoDao;
	
	@Test
	@Transactional
	public void testBuscarPorIdSemAssociacoes() {
		
		Risco risco = riscoDao.buscarPorIdSemAssociacoes(1l);
		
		Assert.assertNotNull(risco);
		Assert.assertNotNull(risco.getResponsavel());
	}
	
	@Test
	@Transactional
	public void testBuscarPorIdSemAssociacoesSemResponsavel() {
		
		Risco risco = riscoDao.buscarPorIdSemAssociacoes(2l);
		
		Assert.assertNotNull(risco);
		Assert.assertNull(risco.getResponsavel());
	}
	
	@Test
	@Transactional
	public void testBuscarPorIdSemAssociacoesInexistente() {
		
		Risco risco = riscoDao.buscarPorIdSemAssociacoes(3l);
		
		Assert.assertNull(risco);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testBuscarPorIdSemAssociacoesIdNull() {
		
		Risco risco = riscoDao.buscarPorIdSemAssociacoes(null);
		
		Assert.assertNull(risco);
	}
}
