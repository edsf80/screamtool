package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario_projeto database table.
 * 
 */
@Entity
@Table(name="usuario_projeto")
@NamedQuery(name="UsuarioProjeto.findAll", query="SELECT u FROM UsuarioProjeto u")
public class UsuarioProjeto implements Serializable {
	private static final long serialVersionUID = 1L;
	private UsuarioProjetoPK id;
	private List<Papel> papels;

	public UsuarioProjeto() {
	}


	@EmbeddedId
	public UsuarioProjetoPK getId() {
		return this.id;
	}

	public void setId(UsuarioProjetoPK id) {
		this.id = id;
	}


	//bi-directional many-to-many association to Papel
	@ManyToMany
	@JoinTable(
		name="usuario_papel_projeto"
		, joinColumns={
			@JoinColumn(name="prj_id", referencedColumnName="prj_id"),
			@JoinColumn(name="usr_id", referencedColumnName="usr_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ppl_id")
			}
		)
	public List<Papel> getPapels() {
		return this.papels;
	}

	public void setPapels(List<Papel> papels) {
		this.papels = papels;
	}

}