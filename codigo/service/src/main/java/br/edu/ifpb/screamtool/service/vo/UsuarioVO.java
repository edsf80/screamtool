/**
 * 
 */
package br.edu.ifpb.screamtool.service.vo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifpb.screamtool.domain.entity.Projeto;

/**
 * @author edsf
 *
 */
public class UsuarioVO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3788228642288820308L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String nome;

	/**
	 * 
	 */
	private String username;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private Set<Projeto> projetos;

	/**
	 * A lista de permissoes do usuário
	 */
	private List<GrantedAuthority> authorities;

	/**
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities
	 * ()
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;
	}
	
	/**
	 * @param authorities
	 */
	public void setAuthorities(List<GrantedAuthority> authorities) {

		this.authorities = authorities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired
	 * ()
	 */
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked
	 * ()
	 */
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return the projetos
	 */
	public Set<Projeto> getProjetos() {
		return projetos;
	}

	/**
	 * @param projetos
	 *            the projetos to set
	 */
	public void setProjetos(Set<Projeto> projetos) {
		this.projetos = projetos;
	}
}
