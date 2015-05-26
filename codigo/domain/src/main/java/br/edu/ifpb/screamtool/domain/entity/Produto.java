package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUTO_PRDID_GENERATOR", sequenceName = "seq_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_PRDID_GENERATOR")
	@Column(name = "prd_id")
	private Long id;

	@Column(name = "prd_dsc")
	private String descricao;

	// bi-directional many-to-one association to ProductBacklog
	@OneToMany(mappedBy = "produto")
	private List<ProductBacklog> productBacklogs;

	// bi-directional many-to-one association to Projeto
	@OneToMany(mappedBy = "produto")
	private List<Projeto> projetos;

	public Produto() {
	}

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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
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

}