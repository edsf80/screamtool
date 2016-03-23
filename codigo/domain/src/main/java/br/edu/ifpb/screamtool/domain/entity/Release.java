package br.edu.ifpb.screamtool.domain.entity;

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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the release database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Release.findAll", query = "SELECT r FROM Release r"),
		@NamedQuery(name = "Release.buscarProProjeto", 
		            query = "SELECT r.id, r.nome, s.id, s.nome, i.id, i.descricao FROM Release r LEFT JOIN r.sprints s LEFT JOIN s.itensBacklog i WHERE r.projeto.id = :idProjeto") })
public class Release extends EntidadeBasica {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_release", sequenceName = "seq_release")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_release")
	@Column(name = "rls_id")
	private Long id;

	@NotNull
	@Column(name = "rls_nme")
	private String nome;

	@NotNull
	@Column(name = "rls_dsc")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "prj_id")
	private Projeto projeto;

	@OneToMany(mappedBy = "release")
	@Fetch(FetchMode.SUBSELECT)
	private List<Sprint> sprints;

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
	 * @return the sprints
	 */
	public List<Sprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints
	 *            the sprints to set
	 */
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

}