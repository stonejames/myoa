<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'show.jsp' starting page</title>
    
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
	<script type="text/javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
				url:'emp_queryEmp.action',
				pagination:true,//显示底部分页栏
				pageSize:5,//默认每页记录数
				pageList:[5,10,15],
				rownumbers: true,//行号列
				//singleSelect:true,//只允许选择一行
				columns:[[
					{field:'ck',checkbox:true },
					{field:'empno',title:'员工编号',width:70},
					{field:'ename',title:'员工姓名',width:70},
					{field:'dept.dname',title:'员工所属部门',width:260},
					{field:'job',title:'员工职位',width:70},
					{field:'mgrname',title:'员工上级',width:70},
					{field:'sal',title:'员工薪水',width:70},
					{field:'comm',title:'员工福利',width:70},
					{field:'hiredate',title:'员工入职时间',width:260,
						 formatter:function(val)  
			                { /* 调用函数显示时间 */  
			                    return formattime(val);  
			                }  
					},
					{field:'dept.deptno',title:'员工所属部门ID',hidden:true},
					{field:'mgr',title:'员工上级ID',hidden:true},
				]],
				toolbar:[{
						iconCls: 'icon-save', // 'icon-search'  'icon-edit'  'icon-help',
						text:'保存',
						id:'saveEmp',
						handler: function(){
								$('#win').window('open');
								//重置form表单的值 不然上次输入的值还在
								$('#ff').get(0).reset();
								
								$('#cc').combobox({   //部门下拉
									url:'emp_queryDepts.action',
									valueField:'deptno',    
								    textField:'dname',  
									panelHeight:100,
									onLoadSuccess:function(){
										 //把下拉列表第一个值设置为默认值
										 var data = $("#cc").combobox('getData');
										 if(data.length>0){
										     $('#cc').combobox('select', data[0].deptno);
										 }
								    }
								});  
								$('#mm').combobox({   //上级领导下拉                      
								    textField:'label', //这个功能有问题  上级领导应该根据   部门编号查询出该部门负责人
									valueField:'value',  
								    data:[{
									    	label: 'CLARK',
									    	value: '7782'
									    },{
									    	label: 'BLAKE',
									    	value: '7698'
									    },{
									    	label: 'JONES',
									    	value: '7566'
									    },{
									    	label: '赵六',
									    	value: '7937'
									    }], 
									panelHeight:80,
									onLoadSuccess:function(){
									 //把下拉列表第一个值设置为默认值
										 var data = $("#mm").combobox('getData');
										 if(data.length>0){
										     $('#mm').combobox('select', data[0].value);
										 }
							    	}
								});
								$('#dd').datebox({    
								    required:true,
								    formatter:function(date){
											var y = date.getFullYear();
											var m = date.getMonth()+1;
											var d = date.getDate();
											return y+'-'+d+'-'+m; 
								    }
								});  
						}
				},{
					iconCls: 'icon-remove', // 'icon-search'  'icon-edit'  'icon-help',
					text:'删除',
					handler: function(){
					//getChecked none 在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用） 
					// getSelected 返回第一个被选中的行或如果没有选中的行则返回null。
					// getSelections none 返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。 
					  var objArray = $('#dg').datagrid('getSelections'); 
					 
					  if(objArray.length > 0){
						  var ids = "";
						  for(var i=0;i<objArray.length;i++){
							  ids += objArray[i].empno + ",";
						  }
						  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
						      if (r){    
								  $.get("emp_deleteEmp.action","ids="+ids,function(result){
									  if(result.flag == "true"){
											  $.messager.alert('警告','删除成功');   
											  $('#dg').datagrid('reload');    // 删除 重新载入当前页面数据   
									  }
								  })
						      }    
						  });  
					  }else{
						  $.messager.show({
   							    title:'我的消息',
								msg:'<font color=\'red\'>请选择要删除的行</font>',
								timeout:3000,
								showType:'slide'
						  })
					  }         
					}
					},{
						iconCls: 'icon-edit', // 'icon-search'  'icon-edit'  'icon-help',
						text:'修改',
						handler: function(){
						  var objArray = $('#dg').datagrid('getSelections'); 
						  if(objArray.length == 1){
							  $('#win').window({
									title:'修改'
							  });
							  $('#win').window('open');

							  $('#cc').combobox({   //部门下拉
									url:'emp_queryDepts.action',
									valueField:'deptno',    
								    textField:'dname',  
									panelHeight:100,
									onLoadSuccess:function(){
										$('#cc').combobox('select', objArray[0].dept.deptno);
									}
								});  
								$('#mm').combobox({   //上级领导下拉                      
								    textField:'label', //这个功能有问题  上级领导应该根据   部门编号查询出该部门负责人
									valueField:'value',  
								    data:[{
									    	label: 'CLARK',
									    	value: '7782'
									    },{
									    	label: 'BLAKE',
									    	value: '7698'
									    },{
									    	label: 'JONES',
									    	value: '7566'
									    },{
									    	label: '赵六',
									    	value: '7937'
									    }], 
									panelHeight:80,
									onLoadSuccess:function(){
										$('#mm').combobox('select', objArray[0].mgr);
									}
								})
								$('#dd').datebox({    
								    required:true,
								    formatter:function(date){
											var y = date.getFullYear();
											var m = date.getMonth()+1;
											var d = date.getDate();
											return y+'-'+d+'-'+m; 
								    }
								});
								
							  $('#username').val(objArray[0].ename);
							  alert(objArray[0].hiredate)
							  $('#dd').val(objArray[0].hiredate);
							   
						  }else if(objArray.length == 0){
							  $.messager.show({
									    title:'我的消息',
									msg:'<font color=\'red\'>请选择要删除的行</font>',
									timeout:3000,
									showType:'slide'
							  })
						  }else if(objArray.length > 1){
							  $.messager.show({
								    title:'我的消息',
								msg:'<font color=\'red\'>只能单独修改一行</font>',
								timeout:3000,
								showType:'slide'
						  })
					  }        
						}
				}]
			});
			$("#quxiao").click(function(){
				$('#win').window('close');  
			})
		});

		
		function saveemp(){
			$.messager.progress();	// 显示进度条
			$('#ff').form('submit', {
				url: 'emp_saveEmp.action',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				success: function(){
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
					$('#win').window('close'); // close , destroy  
				}
			});
		}
	</script>
	<style type="text/css">
		label{float:left;text-align:right;}
	</style>
  </head>
  	
  <body style="padding: 0px;">
	  <div id="lay" class="easyui-layout" style="width: 100%;height:100%;" >
	  	  <div region="north" title="用户查询" collapsed="true" style="height:100px;" >
				<form id="mysearch" method="post">
						用户名:&nbsp;&nbsp;&nbsp;<input name="username" class="easyui-validatebox"  value="" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						开始时间:<input name="startTime"  class="easyui-datetimebox" editable="false" style="width:160px;"  value="" />	
						结束时间:<input name="endTime"  class="easyui-datetimebox" editable="false" style="width:160px;"  value="" />	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="searchbtn" class="easyui-linkbutton">查询</a> <a id="clearbtn" class="easyui-linkbutton">清空</a>
				</form>
			
			</div>
			<div region="center">
	  			<table id="dg" ></table>
	  		</div>
	  </div>
  	 
    	
    <div id="win" class="easyui-window" title="添加员工"  
    		  style="width:400px;height:300px;padding:5px;top:100px" data-options="closed:true,modal:true">   
		     	 <form style="padding:10px 20px 10px 40px;" id="ff">  
			        <p> <label for="emp.ename">姓名:</label>   
			        	<input type="text"  name="emp.ename" id="username" value=""></p>  
			        <p><label for="emp.ename">入职时间: </label>   
			          		   <input id="dd" name="emp.hiredate"  type="text"></input>  
			          		   </p>  
			        <p>部门: <input id="cc"  name="emp.dept.deptno"  ></p>  
			        <p>上级领导: <input id="mm"  name="emp.mgr" ></p>  
			        <div style="padding:5px;text-align:center;">  
			            <a href="javascript:saveemp();" class="easyui-linkbutton" icon="icon-ok">提交</a>  
			            <a  class="easyui-linkbutton" icon="icon-cancel" id="quxiao">取消</a>  
			        </div>  
			    </form>  
    </div>  
    
  </body>
</html>
