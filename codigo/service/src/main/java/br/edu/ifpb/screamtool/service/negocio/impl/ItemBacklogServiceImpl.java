package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.ItemBacklogDao;
import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.service.negocio.ItemBacklogService;

@Validated
@Service("itemBacklogService")
public class ItemBacklogServiceImpl extends
		GenericServiceImpl<ItemBacklog, Long> implements ItemBacklogService {

	/**
	 * @param dao
	 */
	@Autowired
	public ItemBacklogServiceImpl(ItemBacklogDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.service.negocio.ItemBacklogService#
	 * buscarTodosPorProduto(java.lang.Long)
	 */
	@Override
	public List<ItemBacklog> buscarTodosPorProduto(@NotNull Long idProduto) {

		ItemBacklogDao dao = (ItemBacklogDao) this.dao;

		return dao.buscarTodosPorProduto(idProduto);
	}
}
