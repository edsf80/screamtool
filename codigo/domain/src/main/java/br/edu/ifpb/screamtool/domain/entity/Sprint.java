package br.edu.ifpb.screamtool.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the sprint database table.
 * 
 */
@Entity
@NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s")
public class Sprint extends EntidadeBasica {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "seq_sprint", sequenceName = "seq_sprint")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sprint")
	@Column(name = "spt_id")
	private Long id;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "prj_id")
	private Projeto projeto;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "rls_id")
	private Release release;

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
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto
	 *            the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	/**
	 * @return the release
	 */
	public Release getRelease() {
		return release;
	}

	/**
	 * @param release
	 *            the release to set
	 */
	public void setRelease(Release release) {
		this.release = release;
	}

}