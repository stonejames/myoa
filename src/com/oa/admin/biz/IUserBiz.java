package com.oa.admin.biz;

import java.util.List;

import com.oa.admin.pojo.TPermission;
import com.oa.admin.pojo.TUser;


public interface IUserBiz {

	/**
	 * ��¼
	 * @return
	 */
	public TUser queryUser(TUser user);
	
	public List<TPermission>  queryPermission(Integer id);
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	public List<TUser> queryUsers(TUser user,String pageNumber,String pageSize);
	
	/**
	 * ��ҳ��ѯ����
	 * @return
	 */
	public Integer queryUserCount(TUser user);
	
}
