package com.oa.admin.biz.impl;

import java.util.List;

import com.oa.admin.biz.IRoleBiz;
import com.oa.admin.dao.TRoleDAO;
import com.oa.admin.pojo.TRole;

public class RoleBiz implements IRoleBiz {

	private TRoleDAO roleDao;
	
	@Override
	public List<TRole> queryRoles(TRole role, String pageNumber, String pageSize) {
		// TODO Auto-generated method stub
	    List<TRole> roles = roleDao.findAllByPage(role, pageNumber, pageSize);
		return roles;
	}

	@Override
	public Integer queryRoleCount(TRole role) {
		// TODO Auto-generated method stub
		return roleDao.count();
	}

	public void setRoleDao(TRoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	
}
