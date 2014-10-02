package com.oa.admin.action;

import java.util.HashMap;
import java.util.List;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.oa.admin.biz.IUserBiz;
import com.oa.admin.pojo.TPermission;
import com.oa.admin.pojo.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author ������
 *
 */
public class UserAction extends ActionSupport{

	private List<TUser> users;
	
	private List<TPermission>  permissions;
	
	private TUser user;
	
	private IUserBiz userBiz;
	
	private String rows;//ÿҳ��ʾ��¼����
	
	private String page;//��ǰҳ��
	
	private JSONObject result;  //�洢����json
	 
	
	
	public String login(){
		TUser currentUser = userBiz.queryUser(user);
		if(currentUser == null){
			return INPUT;
		}
		permissions = userBiz.queryPermission(currentUser.getId());
		return SUCCESS;
	}
	/**
	 * ��ѯ�û�
	 * @return
	 */
	public String queryUser(){
		users = userBiz.queryUsers(user,page,rows);//page ��1 rows��5 
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("rows", users);
		map.put("total", userBiz.queryUserCount(user));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"users","permissions"});
		result =JSONObject.fromObject(map,jsonConfig);
		
		System.out.println(result);
		return SUCCESS;
	}

	public List<TUser> getUsers() {
		return users;
	}

	public void setUsers(List<TUser> users) {
		this.users = users;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public void setUserBiz(IUserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public List<TPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TPermission> permissions) {
		this.permissions = permissions;
	}
}