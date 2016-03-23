/**
 * 
 */
package br.edu.ifpb.screamtool.visao.form;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.domain.entity.Release;

/**
 * @author edsf
 *
 */
public class ReleaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908420994372253210L;

	/**
	 * 
	 */
	private List<ItemBacklog> itensBacklogNaoAlocados;
	
	/**
	 * 
	 */
	private List<Release> releases;

	/**
	 * @return the itensBacklogNaoAlocados
	 */
	public List<ItemBacklog> getItensBacklogNaoAlocados() {
		return itensBacklogNaoAlocados;
	}

	/**
	 * @param itensBacklogNaoAlocados
	 *            the itensBacklogNaoAlocados to set
	 */
	public void setItensBacklogNaoAlocados(
			List<ItemBacklog> itensBacklogNaoAlocados) {
		this.itensBacklogNaoAlocados = itensBacklogNaoAlocados;
	}

	/**
	 * @return the releases
	 */
	public List<Release> getReleases() {
		return releases;
	}

	/**
	 * @param releases the releases to set
	 */
	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}

}
