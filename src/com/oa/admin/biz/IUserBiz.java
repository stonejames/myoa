package com.oa.admin.biz;

import java.util.List;

import com.oa.admin.pojo.TPermission;
import com.oa.admin.pojo.TUser;


public interface IUserBiz {

	/**
	 * 登录
	 * @return
	 */
	public TUser queryUser(TUser user);
	
	public List<TPermission>  queryPermission(Integer id);
	/**
	 * 分页查询
	 * @return
	 */
	public List<TUser> queryUsers(TUser user,String pageNumber,String pageSize);
	
	/**
	 * 分页查询总数
	 * @return
	 */
	public Integer queryUserCount(TUser user);
	
}
