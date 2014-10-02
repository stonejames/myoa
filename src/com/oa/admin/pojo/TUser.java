package com.oa.admin.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_user")
public class TUser {
	
	@Id
	private Integer id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;

	@ManyToMany(mappedBy="users",fetch=FetchType.LAZY)
	private Set<TRole> roles = new HashSet<TRole>();
	
	@Transient
	private String rolename;
	
	@Transient
	private String buttonid;
	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<TRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TRole> roles) {
		this.roles = roles;
	}

	public String getButtonid() {
		return buttonid;
	}

	public void setButtonid(String buttonid) {
		this.buttonid = buttonid;
	}
}
