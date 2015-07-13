package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the projeto database table.
 * 
 */
@Entity
@NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projeto")
	@SequenceGenerator(name = "seq_projeto", sequenceName = "seq_projeto")	
	@Column(name = "prj_id")
	private Long id;

	@NotNull(message = "O nome do projeto é obrigatório")
	@Column(name = "prj_dsc")
	private String nome;

	@NotNull(message = "O produto é obrigatório")
	@ManyToOne
	@JoinColumn(name = "prd_id")
	private Produto produto;

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


/*	public Release addReleas(Release releas) {
		getReleases().add(releas);
		releas.setProjeto(this);

		return releas;
	}

	public Release removeReleas(Release releas) {
		getReleases().remove(releas);
		releas.setProjeto(null);

		return releas;
	}*/

	/*public List<Sprint> getSprints() {
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
	}*/

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