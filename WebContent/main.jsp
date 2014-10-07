<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>MyOA</title>
    
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
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
			$(function(){
				$('a[title]').click(function(){
						var src = $(this).attr('title');
						var title = $(this).html();
						if($('#tt').tabs('exists' ,title)){
							$('#tt').tabs('select',title);
						} else {
							$('#tt').tabs('add',{   
							    title:title,   
							    content:'<iframe frameborder=0 style=width:100%;height:100% src='+ src +' ></iframe>',   
							    closable:true  
							});  
						}
				});
			});
	</script>	
  </head>
  
  <body>
    	<div id="cc" class="easyui-layout" fit="true" style="width:100%;height:100%;">  
		    <div region="north" title="easyui-layout"  split="false" style="height:100px;">
		    </div>  
		    <!-- 
		    <div region="south" title="South Title" split="true" style="height:100px;"></div>  
		    <div region="east" collapsed=true iconCls="icon-reload" title="East" split="true" style="width:100px;"></div>  
		     -->
		     
		    
		     
		    <div region="west"  iconCls="icon-ok" split="true" title="菜单" style="width:150px;">
			    
				<div id="aa" class="easyui-accordion" fit="true" >  
					 <s:iterator value="permissions">
					 	<s:if test='id == parentid'>
					 	    <s:set var="parent"  value="id"></s:set>
					 		<div title="${name}"  style="overflow:auto;padding:10px;">  
					 				 <s:iterator value="permissions">
					 				 	 <s:if test="id != parentid && buttionid == null &&  #request.parent  == parentid">
					 				 	 	<a title="jsp/${url }">${name }</a><br>
					 				 	 </s:if>
					 				 </s:iterator>
				    			<%--<a title="jsp/${url }">${name }</a><br>--%>
				    		</div>  
					 	</s:if>
				     </s:iterator>
				</div>  
		    </div>  
		    <div region="center"  title="主界面" style="padding:0px;">
				<div id="tt" class="easyui-tabs" fit="true" style="width:500px;height:250px;">  
				</div>  
		    </div>  
		</div>  
  </body>
</html>
