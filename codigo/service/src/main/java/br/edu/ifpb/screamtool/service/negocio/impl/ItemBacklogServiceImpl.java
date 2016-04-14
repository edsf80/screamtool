package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public List<ItemBacklog> buscarTodosPorProduto(@NotNull Long idProduto) {

		ItemBacklogDao dao = (ItemBacklogDao) this.dao;

		return dao.buscarTodosPorProduto(idProduto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.service.negocio.ItemBacklogService#
	 * buscarTodosPorProdutoNaoAlocados(java.lang.Long)
	 */
	@Override
	public List<ItemBacklog> buscarTodosPorProdutoNaoAlocados(Long idProduto) {

		ItemBacklogDao dao = (ItemBacklogDao) this.dao;

		return dao.buscarTodosPorProdutoNaoAlocados(idProduto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.ItemBacklogService#mergeItemBacklog
	 * (br.edu.ifpb.screamtool.domain.entity.ItemBacklog)
	 */
	@Override
	@Transactional
	public ItemBacklog mergeItemBacklog(@NotNull ItemBacklog itemBacklog) {
		// TODO: verificar com o bv se apenas o id não é nulo.
		// TODO: fazer verificação quando o item de backlog não for encontrado.
		ItemBacklogDao dao = (ItemBacklogDao) this.dao;

		ItemBacklog itemBacklogPersistido = dao
				.buscarPorId(itemBacklog.getId());

		itemBacklogPersistido.setSprint(itemBacklog.getSprint());

		itemBacklogPersistido.setStoryPoints(itemBacklog.getStoryPoints());

		return itemBacklogPersistido;
	}
}
