/**
 * 
 */
package br.edu.ifpb.screamtool.service.vo;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.service.validator.UniqueUsername;

/**
 * @author edsf
 *
 */
public class RegistroUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012742511975437096L;

	private Long id;

	/**
	 * 
	 */
	@NotNull
	@UniqueUsername
	private String login;

	/**
	 * 
	 */
	@NotNull
	private String nome;

	/**
	 * 
	 */
	@NotNull
	private String senha;

	/**
	 * 
	 */
	@NotNull
	private String confirmacaoSenha;
	
	/**
	 * 
	 */
	@AssertTrue
	private boolean acordoTermos;

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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the confirmacaoSenha
	 */
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	/**
	 * @param confirmacaoSenha
	 *            the confirmacaoSenha to set
	 */
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	/**
	 * @return
	 */
	@AssertTrue
	private boolean ehValido() {
		return this.senha.equals(this.confirmacaoSenha);
	}

	/**
	 * @return the acordoTermos
	 */
	public boolean getAcordoTermos() {
		return acordoTermos;
	}

	/**
	 * @param acordoTermos the acordoTermos to set
	 */
	public void setAcordoTermos(boolean acordoTermos) {
		this.acordoTermos = acordoTermos;
	}

}
