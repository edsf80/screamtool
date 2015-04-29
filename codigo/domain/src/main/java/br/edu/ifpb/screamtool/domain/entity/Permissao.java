package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissao database table.
 * 
 */
@Entity
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long prmId;
	private String prmDsc;
	private List<Papel> papels;

	public Permissao() {
	}


	@Id
	@SequenceGenerator(name="PERMISSAO_PRMID_GENERATOR", sequenceName="SQ_PERMISSAO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERMISSAO_PRMID_GENERATOR")
	@Column(name="prm_id")
	public Long getPrmId() {
		return this.prmId;
	}

	public void setPrmId(Long prmId) {
		this.prmId = prmId;
	}


	@Column(name="prm_dsc")
	public String getPrmDsc() {
		return this.prmDsc;
	}

	public void setPrmDsc(String prmDsc) {
		this.prmDsc = prmDsc;
	}


	//bi-directional many-to-many association to Papel
	@ManyToMany(mappedBy="permissaos")
	public List<Papel> getPapels() {
		return this.papels;
	}

	public void setPapels(List<Papel> papels) {
		this.papels = papels;
	}

}