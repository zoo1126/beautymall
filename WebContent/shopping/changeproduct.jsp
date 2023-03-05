<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="product.product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:rich="http://richfaces.org/rich">
<%
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>facemall</title>

	<link rel="stylesheet" type="text/css" href="shopping/css/base.css"></link>
	<link rel="stylesheet"  type="text/css" href="shopping/css/common.css"></link>
<%
	String id=request.getParameter("id");
String oldpid=request.getParameter("oldpid");
String pname=request.getParameter("pname");
String price=request.getParameter("price");
String sort=request.getParameter("sort");
String brand=request.getParameter("brand");
String liked=request.getParameter("liked");
String discount=request.getParameter("discount");
	String shouye="/shopping/manage_index.html";
	String tianjia="/beautymall/productchange?oldpid="+oldpid;
	if(id!=null)
	{
		shouye="/shopping/manage_index.html?id="+id;
		tianjia="/beautymall/productchange?id="+id+"&oldpid="+oldpid;
	}
	


%>
</head>
<body>

	<!-- 快捷导航 -->
	<section class="shortcut">
		<div class="w">
		
			<div class="fl">
				<ul>
					<li>xx美妆商城管理系统&nbsp;</li>
					<li><a id="user" href="" style="float:left;"></a><!--  -->
					<script>
			var url=location.href;
			var num=url.indexOf("?");
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				
				document.getElementById("user").innerHTML=<%=id%>;
			}
			function search()//输入关键字查找商品
	{
		var value=document.getElementById("search").value;
		var name="<%=id%>";
		if(name!=null&&name!="null")
		{
		keyword="/beautymall/search_byinput"+"?id="+name+"&keyword="+value;
		window.open(keyword);
		}
		else{
		keyword="/beautymall/search_byinput"+"?keyword="+value;
		window.open(keyword);
		}
	}
		</script></li>
					
					
					
					
			
				
				</ul>
			</div>
			<div class="fr">
				<ul>
					<li><a href="<%=shouye %>"  class="style_red">首页</a></li>
					<li></li>
					<li><a href="">我的账号</a></li>

					<li></li>
					<li><a href="">产品大全</a></li>

					
				</ul>
			</div>
		</div>
	</section>
	
	
	
	<!-- 头部模块 -->
	<header class="header w">
	<!-- logo -->
		<div class="logo">
			<h1>
				<a href="index.jsp">商城</a>
			</h1>
		</div>
	<!-- 搜索 -->
	<div class="search">
		<input type="search" name="" id="search" style="border: 0; outline:none" placeholder="输入商品名称或关键字"></input>
		<button style="border: 0; outline:none" onclick="search()">搜索</button>
	</div>
	
	</header>
	
	
	<script>

	function s()
	{
		var id=<%=id%>;
		var oldpid="<%=oldpid%>";
		var pid=document.getElementById("pid").value;
		var pname=document.getElementById("pname").value;
		var sort=document.getElementById("sort").value;
		var brand=document.getElementById("brand").value;
	
		var discount=document.getElementById("discount").value;
		var price=document.getElementById("price").value;
	
		if(id!=null)
		{
			var url="/beautymall/productchange?id="+id+"&oldpid="+oldpid+"&pid="+pid+"&pname="+pname+"&sort="+sort+"&brand="+brand+"&price="+price+"&discount="+discount;
		
			window.location.href=url;
			}
		else
			{window.location.href="../faces/shopping/man_login.xhtml";}
	}
	</script>
<div class="w">
	
<center>
<br></br>

	<form name="tochange" id="tochange" method="post">
		<table>
			<tr><td style="font-size:20px;">编号<input name="pid" id="pid" value="<%=oldpid %>" style="font-size:20px;"></input></td></tr>
			<tr><td style="font-size:20px;">名称<input name="pname" id="pname" value="<%=pname %>" style="font-size:20px;"></input></td></tr>
			<tr><td style="font-size:20px;">种类<input name="sort" id="sort" value="<%=sort %>" style="font-size:20px;"></input></td></tr>
			<tr><td style="font-size:20px;">品牌<input name="brand" id="brand" value="<%=brand %>" style="font-size:20px;"></input></td></tr>
			<tr><td style="font-size:20px;">价格<input name="price" id="price" value="<%=price%>" style="font-size:20px;"></input></td></tr>
			<tr><td style="font-size:20px;">折扣<input name="discount" id="discount" value="<%=discount %>" style="font-size:20px;"></input></td></tr>
			
			<tr><td style="text-align:center ;"><input type="button" onclick="s()" style="font-size:20px;" value="确定修改"></input></td></tr>
		</table>
	</form>
</center>
</div>
</body>
</html>