package br.edu.ifpb.screamtool.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item_backlog database table.
 * 
 */
@Entity
@Table(name="item_backlog")
@NamedQuery(name="ItemBacklog.findAll", query="SELECT i FROM ItemBacklog i")
public class ItemBacklog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long iblId;
	private ProductBacklog productBacklog;
	private List<Tarefa> tarefas;

	public ItemBacklog() {
	}


	@Id
	@SequenceGenerator(name="ITEM_BACKLOG_IBLID_GENERATOR", sequenceName="SQ_ITEM_BACKLOG")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEM_BACKLOG_IBLID_GENERATOR")
	@Column(name="ibl_id")
	public Long getIblId() {
		return this.iblId;
	}

	public void setIblId(Long iblId) {
		this.iblId = iblId;
	}


	//bi-directional many-to-one association to ProductBacklog
	@ManyToOne
	@JoinColumn(name="pbg_id")
	public ProductBacklog getProductBacklog() {
		return this.productBacklog;
	}

	public void setProductBacklog(ProductBacklog productBacklog) {
		this.productBacklog = productBacklog;
	}


	//bi-directional many-to-one association to Tarefa
	@OneToMany(mappedBy="itemBacklog")
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

}