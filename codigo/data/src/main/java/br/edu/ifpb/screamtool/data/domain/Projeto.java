package br.edu.ifpb.screamtool.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the projeto database table.
 * 
 */
@Entity
@NamedQuery(name="Projeto.findAll", query="SELECT p FROM Projeto p")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long prjId;
	private Produto produto;
	private List<Release> releases;
	private List<Sprint> sprints;
	private List<Usuario> usuarios;

	public Projeto() {
	}


	@Id
	@SequenceGenerator(name="PROJETO_PRJID_GENERATOR", sequenceName="SQ_PROJETO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJETO_PRJID_GENERATOR")
	@Column(name="prj_id")
	public Long getPrjId() {
		return this.prjId;
	}

	public void setPrjId(Long prjId) {
		this.prjId = prjId;
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


	//bi-directional many-to-one association to Release
	@OneToMany(mappedBy="projeto")
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


	//bi-directional many-to-one association to Sprint
	@OneToMany(mappedBy="projeto")
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


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="projetos")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}