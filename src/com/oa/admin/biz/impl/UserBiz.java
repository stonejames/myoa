package com.oa.admin.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.oa.admin.biz.IUserBiz;
import com.oa.admin.dao.TPermissionDAO;
import com.oa.admin.dao.TRoleDAO;
import com.oa.admin.dao.TUserDAO;
import com.oa.admin.pojo.TPermission;
import com.oa.admin.pojo.TRole;
import com.oa.admin.pojo.TUser;
import com.opensymphony.xwork2.ActionContext;

public class UserBiz implements IUserBiz{

	private TUserDAO userDao;
	
	private TRoleDAO roleDao;
	
	private TPermissionDAO permissionDao;
	@Override
	public TUser queryUser(TUser user) {
		List<TUser> list = userDao.findByProperty2("username", user.getUsername());
		if(list.size() ==1){
			TUser u = (TUser)list.get(0);
			if(u.getPassword().equals(user.getPassword())){
				//�ʺ�������ȷ  ��ѯ��ǰ��Ӧ����ɾ��Ȩ��
				StringBuilder sb = new StringBuilder();
				if(u.getRoles().size() > 0){
					for(TRole role:u.getRoles()){
						List<TRole> roles = roleDao.getButtonid(role.getId());
						for(TRole r:roles){
							 Set<TPermission> pers = r.getPermissions();
							 for(TPermission tp:pers){
								 String str =  tp.getButtionid();
								 if(str != null){
									 sb.append(str + ",");
								 }
							 }
						}
					}
				}
				user.setButtonid(sb.toString());
				ActionContext.getContext().getSession().put("CurrentSession", user);
				return (TUser) list.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	@Override
	public Integer queryUserCount(TUser user) {
		return userDao.count();
	}

	@Override
	public List<TUser> queryUsers(TUser user, String pageNumber, String pageSize) {
		List<TUser> users = userDao.findAllByPage(user,pageNumber,pageSize);
		for(TUser u:users){
			String rolename = "";
			for(TRole role:u.getRoles()){
				rolename += role.getRolename() + ",";
			}
			int b = rolename.lastIndexOf(",");
			if( b != -1){
				rolename = rolename.substring(0, b);
			}
			u.setRolename(rolename);
		}
		return users;
	}

	public TUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(TUserDAO userDao) {
		this.userDao = userDao;
	}

	public TRoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(TRoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public TPermissionDAO getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(TPermissionDAO permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Override
	public List<TPermission> queryPermission(Integer id) {
		//�ȸ���ID��ѯ����ǰ�û��Ľ�ɫ
		List<TRole> roles = roleDao.queryRolebyUserId(id);
		List<TPermission> permissions = new ArrayList<TPermission>();
		if(roles.size() == 1){
			//���ݽ�ɫ��ѯ��Ȩ��
			permissions  = permissionDao.queryPermissionByRoleId(roles.get(0).getId());
			for(TPermission p:permissions){
				System.out.println(p.getId()+"   "+p.getName()+ "  "+p.getParentid());
			}
		}
		return permissions;
	}
}
