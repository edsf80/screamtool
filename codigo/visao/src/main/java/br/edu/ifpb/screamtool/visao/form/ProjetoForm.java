/**
 * 
 */
package br.edu.ifpb.screamtool.visao.form;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;
import br.edu.ifpb.screamtool.visao.exception.TableResult;

/**
 * @author edsf
 *
 */
public class ProjetoForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TableResult<Projeto> projetos;
	
	private List<Produto> produtos;
	
	private UsuarioVO usuario;

	/**
	 * @return the projetos
	 */
	public TableResult<Projeto> getProjetos() {
		return projetos;
	}

	/**
	 * @param projetos the projetos to set
	 */
	public void setProjetos(TableResult<Projeto> projetos) {
		this.projetos = projetos;
	}

	/**
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

}
