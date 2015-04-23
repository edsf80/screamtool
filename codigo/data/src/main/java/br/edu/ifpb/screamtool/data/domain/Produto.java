package br.edu.ifpb.screamtool.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long prdId;
	private List<ProductBacklog> productBacklogs;
	private List<Projeto> projetos;

	public Produto() {
	}


	@Id
	@SequenceGenerator(name="PRODUTO_PRDID_GENERATOR", sequenceName="SQ_PRODUTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUTO_PRDID_GENERATOR")
	@Column(name="prd_id")
	public Long getPrdId() {
		return this.prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}


	//bi-directional many-to-one association to ProductBacklog
	@OneToMany(mappedBy="produto")
	public List<ProductBacklog> getProductBacklogs() {
		return this.productBacklogs;
	}

	public void setProductBacklogs(List<ProductBacklog> productBacklogs) {
		this.productBacklogs = productBacklogs;
	}

	public ProductBacklog addProductBacklog(ProductBacklog productBacklog) {
		getProductBacklogs().add(productBacklog);
		productBacklog.setProduto(this);

		return productBacklog;
	}

	public ProductBacklog removeProductBacklog(ProductBacklog productBacklog) {
		getProductBacklogs().remove(productBacklog);
		productBacklog.setProduto(null);

		return productBacklog;
	}


	//bi-directional many-to-one association to Projeto
	@OneToMany(mappedBy="produto")
	public List<Projeto> getProjetos() {
		return this.projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Projeto addProjeto(Projeto projeto) {
		getProjetos().add(projeto);
		projeto.setProduto(this);

		return projeto;
	}

	public Projeto removeProjeto(Projeto projeto) {
		getProjetos().remove(projeto);
		projeto.setProduto(null);

		return projeto;
	}

}