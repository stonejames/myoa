package com.oa.admin.biz;

import java.util.List;

import com.oa.admin.pojo.TRole;

public interface IRoleBiz {

	/**
	 * 分页查询
	 * @return
	 */
	public List<TRole> queryRoles(TRole role,String pageNumber,String pageSize);
	
	/**
	 * 分页查询总数
	 * @return
	 */
	public Integer queryRoleCount(TRole role);
	
}
