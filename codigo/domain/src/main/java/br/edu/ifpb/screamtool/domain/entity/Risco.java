/**
 * 
 */
package br.edu.ifpb.screamtool.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * @author edsf
 *
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Risco.buscarPorProjeto", query = "SELECT r FROM Risco r where r.projeto.id = :projeto"),
@NamedQuery(name = "Risco.buscarPorIdSemAssociacoes", query = "SELECT r.id, r.descricao, r.contingencia, r.probabilidade, r.impacto, r.status, r.mitigacao, u.id, u.nome FROM Risco r LEFT JOIN r.responsavel u where r.id = :id")
})
public class Risco extends EntidadeBasica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8179666740955980605L;

	public enum RiscoStatus {
		N, E, F
	}

	public enum NivelProbImpacto {
		A, M, B
	}
	
	public Risco() {
		super();
	}
	
	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "seq_risco", sequenceName = "seq_risco")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_risco")
	@Column(name = "rsc_id")
	private Long id;

	/**
	 * 
	 */
	@NotNull(message = "Descrição é obrigatória")
	@Column(name = "rsc_dsc")
	private String descricao;

	/**
	 * 
	 */
	@Column(name = "rsc_cnt")
	private String contingencia;

	/**
	 * 
	 */
	@NotNull(message = "Probabilidade é obrigatória")
	@Enumerated(EnumType.STRING)
	@Column(name = "rsc_prb")
	private NivelProbImpacto probabilidade;

	/**
	 * 
	 */
	@NotNull(message = "Impacto é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "rsc_imp")
	private NivelProbImpacto impacto;

	/**
	 * 
	 */
	@NotNull(message = "Status é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "rsc_sts")
	private RiscoStatus status;

	/**
	 * 
	 */
	@Column(name = "rsc_mit")
	private String mitigacao;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prj_id", nullable = false)
	private Projeto projeto;
	
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "rsc_rsp", nullable = true)
	private Usuario responsavel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.domain.entity.EntidadeBasica#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * @param id
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

	/**
	 * @return the contingencia
	 */
	public String getContingencia() {
		return contingencia;
	}

	/**
	 * @param contingencia
	 *            the contingencia to set
	 */
	public void setContingencia(String contingencia) {
		this.contingencia = contingencia;
	}

	/**
	 * @return the probabilidade
	 */
	public NivelProbImpacto getProbabilidade() {
		return probabilidade;
	}

	/**
	 * @param probabilidade
	 *            the probabilidade to set
	 */
	public void setProbabilidade(NivelProbImpacto probabilidade) {
		this.probabilidade = probabilidade;
	}

	/**
	 * @return the impacto
	 */
	public NivelProbImpacto getImpacto() {
		return impacto;
	}

	/**
	 * @param impacto
	 *            the impacto to set
	 */
	public void setImpacto(NivelProbImpacto impacto) {
		this.impacto = impacto;
	}

	/**
	 * @return the status
	 */
	public RiscoStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(RiscoStatus status) {
		this.status = status;
	}

	/**
	 * @return the mitigacao
	 */
	public String getMitigacao() {
		return mitigacao;
	}

	/**
	 * @param mitigacao
	 *            the mitigacao to set
	 */
	public void setMitigacao(String mitigacao) {
		this.mitigacao = mitigacao;
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
	 * @return the responsavel
	 */
	public Usuario getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel the responsavel to set
	 */
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

}
