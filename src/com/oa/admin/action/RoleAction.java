package com.oa.admin.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.oa.admin.biz.IRoleBiz;
import com.oa.admin.pojo.TRole;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport{

	private IRoleBiz roleBiz;
	
	private List<TRole> roles;
	
	private String rows;//每页显示记录条数
	
	private String page;//当前页数
	
	private JSONObject result;  //存储返回json
	
	private TRole role;
	
	/**
	 * 查询用户
	 * @return
	 */
	public String queryRole(){
		roles = roleBiz.queryRoles(role, page, rows);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("rows", roles);
		map.put("total", roleBiz.queryRoleCount(role));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"roles","permissions"});
		result =JSONObject.fromObject(map,jsonConfig);
		return SUCCESS;
	}

	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
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

	public TRole getRole() {
		return role;
	}

	public void setRole(TRole role) {
		this.role = role;
	}

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
	}
	
}
