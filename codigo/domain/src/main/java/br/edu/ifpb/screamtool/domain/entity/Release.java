package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the release database table.
 * 
 */
@Entity
@NamedQuery(name="Release.findAll", query="SELECT r FROM Release r")
public class Release implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long pkRelease;
	private Projeto projeto;

	public Release() {
	}


	@Id
	@SequenceGenerator(name="RELEASE_PKRELEASE_GENERATOR", sequenceName="SQ_RELEASE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELEASE_PKRELEASE_GENERATOR")
	@Column(name="pk_release")
	public Long getPkRelease() {
		return this.pkRelease;
	}

	public void setPkRelease(Long pkRelease) {
		this.pkRelease = pkRelease;
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