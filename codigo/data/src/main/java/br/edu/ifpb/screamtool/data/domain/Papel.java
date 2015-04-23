package br.edu.ifpb.screamtool.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the papel database table.
 * 
 */
@Entity
@NamedQuery(name="Papel.findAll", query="SELECT p FROM Papel p")
public class Papel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long pplId;
	private String pplDsc;
	private List<Permissao> permissaos;
	private List<UsuarioProjeto> usuarioProjetos;

	public Papel() {
	}


	@Id
	@SequenceGenerator(name="PAPEL_PPLID_GENERATOR", sequenceName="SQ_PAPEL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAPEL_PPLID_GENERATOR")
	@Column(name="ppl_id")
	public Long getPplId() {
		return this.pplId;
	}

	public void setPplId(Long pplId) {
		this.pplId = pplId;
	}


	@Column(name="ppl_dsc")
	public String getPplDsc() {
		return this.pplDsc;
	}

	public void setPplDsc(String pplDsc) {
		this.pplDsc = pplDsc;
	}


	//bi-directional many-to-many association to Permissao
	@ManyToMany
	@JoinTable(
		name="papel_permissao"
		, joinColumns={
			@JoinColumn(name="ppl_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="prm_id")
			}
		)
	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}


	//bi-directional many-to-many association to UsuarioProjeto
	@ManyToMany(mappedBy="papels")
	public List<UsuarioProjeto> getUsuarioProjetos() {
		return this.usuarioProjetos;
	}

	public void setUsuarioProjetos(List<UsuarioProjeto> usuarioProjetos) {
		this.usuarioProjetos = usuarioProjetos;
	}

}