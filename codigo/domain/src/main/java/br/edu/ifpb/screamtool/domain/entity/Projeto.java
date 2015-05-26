package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the projeto database table.
 * 
 */
@Entity
@NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROJETO_PRJID_GENERATOR", sequenceName = "seq_projeto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJETO_PRJID_GENERATOR")
	@Column(name = "prj_id")
	private Long id;

	@Column(name = "prj_dsc")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "prd_id")
	private Produto produto;

	// bi-directional many-to-one association to Release
	@OneToMany(mappedBy = "projeto")
	private List<Release> releases;

	// bi-directional many-to-one association to Sprint
	@OneToMany(mappedBy = "projeto")
	private List<Sprint> sprints;

	public Projeto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long prjId) {
		this.id = prjId;
	}

	// bi-directional many-to-one association to Produto

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Release> getReleases() {
		return this.releases;
	}

	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}

	public Release addReleas(Release releas) {
		getReleases().add(releas);
		releas.setProjeto(this);

		return releas;
	}

	public Release removeReleas(Release releas) {
		getReleases().remove(releas);
		releas.setProjeto(null);

		return releas;
	}

	public List<Sprint> getSprints() {
		return this.sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public Sprint addSprint(Sprint sprint) {
		getSprints().add(sprint);
		sprint.setProjeto(this);

		return sprint;
	}

	public Sprint removeSprint(Sprint sprint) {
		getSprints().remove(sprint);
		sprint.setProjeto(null);

		return sprint;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}