package br.edu.ifpb.screamtool.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the sprint database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s"),
		@NamedQuery(name = "Sprint.findByDateRangePerProject", query = "SELECT s FROM Sprint s WHERE (s.release.projeto.id = :idProjeto) AND ((:dataInicio BETWEEN s.dataInicio AND s.dataTermino) OR (:dataTermino BETWEEN s.dataInicio AND s.dataTermino))"),
		@NamedQuery(name = "Sprint.findByDateRangePerProjectDifferentSprint", query = "SELECT s FROM Sprint s WHERE (s.id != :idSprint) AND (s.release.projeto.id = :idProjeto) AND ((:dataInicio BETWEEN s.dataInicio AND s.dataTermino) OR (:dataTermino BETWEEN s.dataInicio AND s.dataTermino))")})
public class Sprint extends EntidadeBasica {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spt_id", insertable = false, updatable = false)
	private Long id;

	/**
	 * 
	 */
	@NotNull(message = "O release do sprint deve ser informado")
	@ManyToOne
	@JoinColumn(name = "rls_id")
	private Release release;

	/**
	 * 
	 */
	@NotNull(message = "A data de início do sprint deve ser informado")
	@Column(name = "spt_ini")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	/**
	 * 
	 */
	@NotNull(message = "A data de término do sprint deve ser informado")
	@Column(name = "spt_fim")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	/**
	 * 
	 */
	@OneToMany(mappedBy = "sprint")
	@Fetch(FetchMode.SUBSELECT)
	private List<ItemBacklog> itensBacklog;

	/**
	 * 
	 */
	@NotNull(message = "O nome do sprint deve ser informado")
	@Column(name = "spt_nm")
	private String nome;
	
	@Column(name = "spt_obj")
	private String objetivo;

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
	 * @param dataInicio
	 *            the dataInicio to set
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
	 * @param dataTermino
	 *            the dataTermino to set
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
	 * @param itensBacklog
	 *            the itensBacklog to set
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
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the objetivo
	 */
	public String getObjetivo() {
		return objetivo;
	}

	/**
	 * @param objetivo the objetivo to set
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

}