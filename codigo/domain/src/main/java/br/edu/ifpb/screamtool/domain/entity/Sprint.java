package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sprint database table.
 * 
 */
@Entity
@NamedQuery(name="Sprint.findAll", query="SELECT s FROM Sprint s")
public class Sprint implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long sptId;
	private Projeto projeto;

	public Sprint() {
	}


	@Id
	@SequenceGenerator(name="SPRINT_SPTID_GENERATOR", sequenceName="SQ_SPRINT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SPRINT_SPTID_GENERATOR")
	@Column(name="spt_id")
	public Long getSptId() {
		return this.sptId;
	}

	public void setSptId(Long sptId) {
		this.sptId = sptId;
	}


	//bi-directional many-to-one association to Projeto
	@ManyToOne
	@JoinColumn(name="prj_id")
	public Projeto getProjeto() {
		return this.projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}