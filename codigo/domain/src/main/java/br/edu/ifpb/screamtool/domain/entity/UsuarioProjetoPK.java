package br.edu.ifpb.screamtool.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the usuario_projeto database table.
 * 
 */
@Embeddable
public class UsuarioProjetoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long usrId;
	private Long prjId;

	public UsuarioProjetoPK() {
	}

	@Column(name="usr_id", insertable=false, updatable=false)
	public Long getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	@Column(name="prj_id", insertable=false, updatable=false)
	public Long getPrjId() {
		return this.prjId;
	}
	public void setPrjId(Long prjId) {
		this.prjId = prjId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioProjetoPK)) {
			return false;
		}
		UsuarioProjetoPK castOther = (UsuarioProjetoPK)other;
		return 
			this.usrId.equals(castOther.usrId)
			&& this.prjId.equals(castOther.prjId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usrId.hashCode();
		hash = hash * prime + this.prjId.hashCode();
		
		return hash;
	}
}