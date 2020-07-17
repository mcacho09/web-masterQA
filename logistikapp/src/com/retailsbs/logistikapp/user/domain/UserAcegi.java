package com.retailsbs.logistikapp.user.domain;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;

public class UserAcegi extends User implements UserDetails {

	private static final long serialVersionUID = -6979074244195161994L;
	
	private String fullname;
	
	/**
	 * Constructor a partir del usuario
	 * @param User u
	 */
	public UserAcegi(User u) {
		this.setImage(u.getImage());
		this.setActive(u.getActive());
		this.setCreated(u.getCreated());
		this.setId_user(u.getId_user());
		this.setLogin(u.getLogin());
		this.setModified(u.getModified());
		this.setOrderby(u.getOrderby());
		this.setPasswd(u.getPasswd());
		this.setProfile(u.getProfile());
		this.setUsername(u.getUsername());
		this.setUserlogin(u.getUserlogin());
		this.setSuperuser(u.getSuperuser());
		this.setJob(u.getJob());
		this.setPhone1(u.getPhone1());
		this.setPhone2(u.getPhone2());
		this.setUbi_time(u.getUbi_time());
		this.fullname = u.getUsername();
	}
   
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/* -- Metodos que se implementan de Acegi UserDetails -- */
	private boolean isAccountNonExpired = true;
	public void setAccountNonExpired(boolean isAccountNonExpired) { this.isAccountNonExpired = isAccountNonExpired; }

	public GrantedAuthority[] getAuthorities() {
		return new GrantedAuthority[]{new GrantedAuthorityImpl(this.getProfile())};
	}
	public String getUsername() {
		return this.getUserlogin();
	}
	public String getPassword() {
		return this.getPasswd();
	}
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		if( this.getActive().equals("S") )
			return true;
		else
			return false;
	}
	/* -- Metodos que se implementan de Acegi UserDetails -- */
	
}
