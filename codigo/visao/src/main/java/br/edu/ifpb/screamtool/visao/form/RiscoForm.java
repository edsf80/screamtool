/**
 * 
 */
package br.edu.ifpb.screamtool.visao.form;

import java.io.Serializable;

/**
 * @author edsf
 *
 */
public class RiscoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	/*private Risco.RiscoStatus Status;
	
	private Risco.NivelProbImpacto probabilidade;

	private Risco.NivelProbImpacto impacto;*/

	private String mitigacao;

	private String contingencia;

	private String responsavel;

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

	/**
	 * @return the status
	 */
	/*public Risco.RiscoStatus getStatus() {
		return Status;
	}*/

	/**
	 * @param status
	 *            the status to set
	 */
	/*public void setStatus(Risco.RiscoStatus status) {
		Status = status;
	}*/

	/**
	 * @return the probabilidade
	 */
	/*public Risco.NivelProbImpacto getProbabilidade() {
		return probabilidade;
	}*/

	/**
	 * @param probabilidade
	 *            the probabilidade to set
	 */
	/*public void setProbabilidade(Risco.NivelProbImpacto probabilidade) {
		this.probabilidade = probabilidade;
	}*/

	/**
	 * @return the impacto
	 */
	/*public Risco.NivelProbImpacto getImpacto() {
		return impacto;
	}*/

	/**
	 * @param impacto
	 *            the impacto to set
	 */
	/*public void setImpacto(Risco.NivelProbImpacto impacto) {
		this.impacto = impacto;
	}*/

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
	 * @return the responsavel
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel
	 *            the responsavel to set
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

}
