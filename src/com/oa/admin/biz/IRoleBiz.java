package com.oa.admin.biz;

import java.util.List;

import com.oa.admin.pojo.TRole;

public interface IRoleBiz {

	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	public List<TRole> queryRoles(TRole role,String pageNumber,String pageSize);
	
	/**
	 * ��ҳ��ѯ����
	 * @return
	 */
	public Integer queryRoleCount(TRole role);
	
}
