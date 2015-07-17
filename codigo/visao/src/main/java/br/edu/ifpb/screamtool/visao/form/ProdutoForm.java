/**
 * 
 */
package br.edu.ifpb.screamtool.visao.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.service.vo.ProjetoVO;
import br.edu.ifpb.screamtool.visao.exception.TableResult;

/**
 * @author edsf
 *
 */
public class ProdutoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TableResult<Produto> produtos;
	
	private String usuario;
	
	private Collection<? extends GrantedAuthority> permissoes;
	
	private List<ProjetoVO> projetos;

	/**
	 * @return the produtos
	 */
	public TableResult<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(TableResult<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the permissoes
	 */
	public Collection<? extends GrantedAuthority> getPermissoes() {
		return permissoes;
	}

	/**
	 * @param permissoes the permissoes to set
	 */
	public void setPermissoes(Collection<? extends GrantedAuthority> permissoes) {
		this.permissoes = permissoes;
	}

	/**
	 * @return the projetos
	 */
	public List<ProjetoVO> getProjetos() {
		return projetos;
	}

	/**
	 * @param projetos the projetos to set
	 */
	public void setProjetos(List<ProjetoVO> projetos) {
		this.projetos = projetos;
	}

}
