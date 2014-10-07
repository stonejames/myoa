<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>角色管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/easyUI/css/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/easyUI/css/common.css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.toolbar.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#roledg').datagrid({
				fit: true, //宽度和高度自适应屏幕
				url:'queryRole.action',
				pagination:true,//显示底部分页栏
				pageSize:5,//默认每页记录数
				pageList:[5,10,15],
				rownumbers: true,//行号列
				//singleSelect:true,//只允许选择一行
				columns:[[
					{field:'ck',checkbox:true },
					{field:'id',title:'角色编号',width:70},
					{field:'rolename',title:'角色名称',width:100},
				]],
				toolbar:[{
						iconCls: 'icon-save', // 'icon-search'  'icon-edit'  'icon-help',
						text:'保存',
						id:'rolebtnadd',
						handler: function(){
						}
				},{
					iconCls: 'icon-remove', // 'icon-search'  'icon-edit'  'icon-help',
					text:'删除',
					id:'rolebtndel',
					handler: function(){
					}
				},{
					iconCls: 'icon-edit', // 'icon-search'  'icon-edit'  'icon-help',
					text:'修改',
					id:'rolebtnedit',
					handler: function(){ 
					}
				}]
			});
			$("#quxiao").click(function(){
				$('#win').window('close');  
			})
			$('#roledg').datagrid("permissionToolbarItem",'${CurrentSession.buttonid}');
			
		});
		</script>
  </head>
  
  <body style="padding: 0px;">
       <div id="lay" class="easyui-layout" style="width: 100%;height:100%;" >
       		<div region="north" title="角色查询" collapsed="true" style="height:80px;" >
				<form id="mysearch" method="post">
						角色名:&nbsp;&nbsp;&nbsp;<input name="rolename" class="easyui-validatebox"  value="" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="searchbtn" class="easyui-linkbutton">查询</a> <a id="clearbtn" class="easyui-linkbutton">清空</a>
				</form>
			
			</div>
			<div region="center">
	  			<table id="roledg"></table>
	  		</div>
	  </div>
  </body>
</html>
