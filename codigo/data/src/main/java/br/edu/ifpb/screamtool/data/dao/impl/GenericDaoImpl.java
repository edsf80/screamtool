/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.ifpb.screamtool.data.dao.GenericDao;

/**
 * @author edsf
 *
 */
public class GenericDaoImpl<T, K> implements GenericDao<T, K> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> tipo;

	/**
	 * 
	 */
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		tipo = (Class) pt.getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.data.dao.GenericDao#criar(java.lang.Object)
	 */
	public T criar(T entidade) {

		this.entityManager.persist(entidade);

		return entidade;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.GenericDao#buscarPorId(java.lang.Object)
	 */
	public T buscarPorId(K id) {

		return (T) this.entityManager.find(tipo, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.GenericDao#atualizar(java.lang.Object)
	 */
	public T atualizar(T entidade) {

		T resultado = this.entityManager.merge(entidade);

		this.entityManager.detach(resultado);

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.data.dao.GenericDao#buscarTodos()
	 */
	public List<T> buscarTodos() {

		Query query = entityManager.createQuery("select t from "
				+ tipo.getName() + " t");

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.data.dao.GenericDao#apagar(java.lang.Object)
	 */
	public void apagar(T entidade) {

		T apagar = this.entityManager.merge(entidade);
		this.entityManager.remove(apagar);
	}

}
