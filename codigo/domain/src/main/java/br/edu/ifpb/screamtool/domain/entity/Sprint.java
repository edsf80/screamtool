package br.edu.ifpb.screamtool.domain.entity;

import java.util.Date;

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
	@JoinColumn(name = "rls_id")
	private Release release;

	/**
	 * 
	 */
	@Column(name = "spt_ini")
	private Date dataInicio;
	
	/**
	 * 
	 */
	@Column(name = "spt_fim")
	private Date dataTermino;
	
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