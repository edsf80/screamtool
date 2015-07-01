/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.mockito.Mockito;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author edsf
 *
 */
public class MockFactoryBean<T> implements FactoryBean<T> {

	private Class<T> classToBeMocked;
	
	public MockFactoryBean(Class<T> classToBeMocked) {
		this.classToBeMocked = classToBeMocked;
	}
	
	@Override
	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return Mockito.mock(classToBeMocked);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return classToBeMocked;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
