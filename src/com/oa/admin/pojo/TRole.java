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

@Entity
@Table(name="t_role")
public class TRole {

	@Id
	private Integer id;

	@Column(name="ROLENAME")
	private String rolename;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="ROLEID")},
			inverseJoinColumns={@JoinColumn(name="USERID")})
	private Set<TUser> users = new HashSet<TUser>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="t_role_permission",joinColumns={@JoinColumn(name="ROLEID")},
			inverseJoinColumns={@JoinColumn(name="PERMISSIONID")})
	private Set<TPermission>  permissions = new HashSet<TPermission>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<TUser> getUsers() {
		return users;
	}

	public void setUsers(Set<TUser> users) {
		this.users = users;
	}

	public Set<TPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<TPermission> permissions) {
		this.permissions = permissions;
	}
}
