package br.edu.ifpb.screamtool.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product_backlog database table.
 * 
 */
@Entity
@Table(name="product_backlog")
@NamedQuery(name="ProductBacklog.findAll", query="SELECT p FROM ProductBacklog p")
public class ProductBacklog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long pbgId;
	private List<ItemBacklog> itemBacklogs;
	private Produto produto;

	public ProductBacklog() {
	}


	@Id
	@SequenceGenerator(name="PRODUCT_BACKLOG_PBGID_GENERATOR", sequenceName="SQ_PRODUCT_BACKLOG")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_BACKLOG_PBGID_GENERATOR")
	@Column(name="pbg_id")
	public Long getPbgId() {
		return this.pbgId;
	}

	public void setPbgId(Long pbgId) {
		this.pbgId = pbgId;
	}


	//bi-directional many-to-one association to ItemBacklog
	@OneToMany(mappedBy="productBacklog")
	public List<ItemBacklog> getItemBacklogs() {
		return this.itemBacklogs;
	}

	public void setItemBacklogs(List<ItemBacklog> itemBacklogs) {
		this.itemBacklogs = itemBacklogs;
	}

	public ItemBacklog addItemBacklog(ItemBacklog itemBacklog) {
		getItemBacklogs().add(itemBacklog);
		itemBacklog.setProductBacklog(this);

		return itemBacklog;
	}

	public ItemBacklog removeItemBacklog(ItemBacklog itemBacklog) {
		getItemBacklogs().remove(itemBacklog);
		itemBacklog.setProductBacklog(null);

		return itemBacklog;
	}


	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prd_id")
	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}