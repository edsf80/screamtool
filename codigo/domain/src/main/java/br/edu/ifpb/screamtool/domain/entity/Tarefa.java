package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tarefa database table.
 * 
 */
@Entity
@NamedQuery(name="Tarefa.findAll", query="SELECT t FROM Tarefa t")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long trfId;
	private ItemBacklog itemBacklog;

	public Tarefa() {
	}


	@Id
	@SequenceGenerator(name="TAREFA_TRFID_GENERATOR", sequenceName="SQ_TAREFA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAREFA_TRFID_GENERATOR")
	@Column(name="trf_id")
	public Long getTrfId() {
		return this.trfId;
	}

	public void setTrfId(Long trfId) {
		this.trfId = trfId;
	}


	//bi-directional many-to-one association to ItemBacklog
	@ManyToOne
	@JoinColumn(name="ibl_id")
	public ItemBacklog getItemBacklog() {
		return this.itemBacklog;
	}

	public void setItemBacklog(ItemBacklog itemBacklog) {
		this.itemBacklog = itemBacklog;
	}

}