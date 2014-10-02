package com.oa.admin.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_permission")
public class TPermission {

	@Id
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="BUTTIONID")
	private String buttionid;
	
	@Column(name="PARENTID")
	private Integer parentid;
	
	@ManyToMany(mappedBy="permissions",fetch=FetchType.LAZY)
	private Set<TRole> roles = new HashSet<TRole>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<TRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TRole> roles) {
		this.roles = roles;
	}

	public String getButtionid() {
		return buttionid;
	}

	public void setButtionid(String buttionid) {
		this.buttionid = buttionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
