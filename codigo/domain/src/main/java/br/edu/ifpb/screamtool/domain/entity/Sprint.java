package br.edu.ifpb.screamtool.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the sprint database table.
 * 
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
	 * 
	 */
	@OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ItemBacklog> itensBacklog;
	
	/**
	 * 
	 */
	@Column(name = "spt_nm")
	private String nome;
	
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

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataTermino
	 */
	public Date getDataTermino() {
		return dataTermino;
	}

	/**
	 * @param dataTermino the dataTermino to set
	 */
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 * @return the itensBacklog
	 */
	public List<ItemBacklog> getItensBacklog() {
		return itensBacklog;
	}

	/**
	 * @param itensBacklog the itensBacklog to set
	 */
	public void setItensBacklog(List<ItemBacklog> itensBacklog) {
		this.itensBacklog = itensBacklog;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}