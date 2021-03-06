package br.edu.ifpb.screamtool.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the item_backlog database table.
 * 
 */
@Entity
@Table(name = "item_backlog")
@NamedQueries({
		@NamedQuery(name = "ItemBacklog.buscarPorProduto", query = "SELECT i FROM ItemBacklog i where i.produto.id = :produto"),
		@NamedQuery(name = "ItemBacklog.buscarPorProdutoNaoAlocado", query = "SELECT i FROM ItemBacklog i where i.produto.id = :produto AND i.sprint IS NULL")})
public class ItemBacklog extends EntidadeBasica {

	public enum ItemBacklogStatus {
		N, P, A, F
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ibl_id", insertable = false, updatable = false)
	private Long id;

	/**
	 * 
	 */
	@NotNull(message = "A descrição do item de backlog deve ser informada")
	@Column(name = "ibl_dsc")
	private String descricao;

	/**
	 * 
	 */
	@NotNull(message = "O produto do item de backlog deve ser informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prd_id", nullable = false)
	private Produto produto;

	/**
	 * 
	 */
	@NotNull(message = "O status do item de backlog deve ser informado")
	@Enumerated(EnumType.STRING)
	@Column(name = "ibl_sts")
	private ItemBacklogStatus status;	
	
	/**
	 * 
	 */
	@NotNull(message = "A estória do usuário do item de backlog deve ser informada")
	@Column(name = "ibl_esu")
	private String estoriaUsuario;

	/**
	 * 
	 */
	@Column(name = "ibl_stp")
	private Integer storyPoints;

	/**
	 * 
	 */
	@OneToMany(mappedBy = "itemBacklog")
	@Fetch(FetchMode.SUBSELECT)
	private List<Tarefa> tarefas;
	
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "spt_id")
	private Sprint sprint;

	public List<Tarefa> getTarefas() {
		return this.tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Tarefa addTarefa(Tarefa tarefa) {
		getTarefas().add(tarefa);
		tarefa.setItemBacklog(this);

		return tarefa;
	}

	public Tarefa removeTarefa(Tarefa tarefa) {
		getTarefas().remove(tarefa);
		tarefa.setItemBacklog(null);

		return tarefa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.domain.entity.EntidadeBasica#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto
	 *            the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the status
	 */
	public ItemBacklogStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ItemBacklogStatus status) {
		this.status = status;
	}

	/**
	 * @return the storyPoints
	 */
	public Integer getStoryPoints() {
		return storyPoints;
	}

	/**
	 * @param storyPoints
	 *            the storyPoints to set
	 */
	public void setStoryPoints(Integer storyPoints) {
		this.storyPoints = storyPoints;
	}

	/**
	 * @return the estoriaUsuario
	 */
	public String getEstoriaUsuario() {
		return estoriaUsuario;
	}

	/**
	 * @param estoriaUsuario the estoriaUsuario to set
	 */
	public void setEstoriaUsuario(String estoriaUsuario) {
		this.estoriaUsuario = estoriaUsuario;
	}

	/**
	 * @return the sprint
	 */
	public Sprint getSprint() {
		return sprint;
	}

	/**
	 * @param sprint the sprint to set
	 */
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

}