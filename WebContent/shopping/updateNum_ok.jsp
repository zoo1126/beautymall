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
<html xmlns="http://www.w3.org/1999/xhtml">
<%
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>facemall</title>
<%String id=request.getParameter("id"); %>
	<link rel="stylesheet" href="shopping/css/base.css"></link>
	<link rel="stylesheet" href="shopping/css/common.css"></link>
<script>
		
			var keyword="/beautymall/search_byinput";
			
			var index="/beautymall/shopping/index.jsp";
			var url=location.href;
			var num=url.indexOf("?");
			var name=null;

</script>
</head>
<body>

	<!-- 快捷导航 -->
	<section class="shortcut">
		<div class="w">
		
			<div class="fl">
				<ul>
					<li>FaceMALL美妆商城欢迎您！&nbsp;</li>
					<li><a id="user" href="" style="float:left;"></a><!--  -->
		<script>
				if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				name=arr[0].substr(3);
				document.getElementById("user").innerHTML=<%=id%>;
			}
			function jumptoindex()
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					index="/beautymall/shopping/index.jsp?"+"id="+name;
					window.location.href="/beautymall/shopping/index.jsp?"+"id="+name;
				}
				else
				{
				index="/beautymall/shopping/index.jsp";
				window.location.href="/beautymall/shopping/index.jsp";
				}
			}
			function jumptoall( )
				{
				
				var name="<%=id%>";
				if(name!=null&&name!="null")
						{window.open("/beautymall/search_all_servlet?id="+name);}
					else
					{
					window.open("/beautymall/search_all_servlet");
					}
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
	function jumptoshopcar()
	{
		var name="<%=id%>";
		if(name!=null&&name!="null")
		{
			index="/beautymall/shopping/index1.jsp?"+"id="+name;
			window.location.href="/beautymall/shopping/index1.jsp?"+"id="+name;
		}
		else
		{
		index="/beautymall/faces/shopping/login.xhtml";
		window.location.href="/beautymall/faces/shopping/login.xhtml";
		}
	}
	function jumptoorder()
	{
		var name="<%=id%>";
		if(name!=null&&name!="null")
		{
			index="/beautymall/u_search_s?"+"id="+name;
			window.location.href="/beautymall/u_search_s?"+"id="+name;
		}
		else
		{
		index="/beautymall/faces/shopping/login.xhtml";
		window.location.href="/beautymall/faces/shopping/login.xhtml";
		}
	}
		</script></li>
					
					<li ><a id="log" href="../faces/shopping/login.xhtml" class="style_red">请登录</a>&nbsp;&nbsp;<a id="reg" href="" >立即注册</a></li>
			
				
				</ul>
			</div>
			<div class="fr">
				<ul>
					<li><a href="javascript:;" onclick="jumptoindex()" class="style_red">首页</a></li>
					<li></li>
					<li><a href="">我的账号</a></li>
					<li></li>
					<li><a href="javascript:;" onclick="jumptoshopcar()">购物车</a></li>

					<li></li>
					<li><a href="javascript:;" onclick="jumptoorder()">我的订单</a></li>

					
				</ul>
			</div>
		</div>
	</section>
						<script>
			var url=location.href;
			var num=url.indexOf("?");
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				
				
				var idp=arr[0].indexOf("id");
				if(idp!=-1)
				{	
				document.getElementById("user").innerHTML=<%=id%>;
				document.getElementById("log").innerHTML="";
				document.getElementById("reg").innerHTML="";
				}
			}
			
		</script>
	
	
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
		<input type="search"  id="search" style="border: 0; outline:none" placeholder="输入商品名称或关键字"></input>
		<button style="border: 0; outline:none" onclick="search()">搜索</button>
	</div>
	<!-- 全部商品 -->
	<div class="shopcar"><img src="shopping/image/shopcar.png" width="35px" height="32px" style="float:left;background-color:#b1191a"></img><a id="all_pro" href="" onclick="jumptoall()">&nbsp;全部商品&nbsp;<img src="shopping/image/toright.png" width="8px"></img></a>
	</div>
	</header>
<center>
<h4>修改成功，是否继续？</h4>
<form action="carServlet" method="post">
<input type="hidden" name="id" value="<%=id%>">
<input type="hidden" name="methodName" value="0"><input type="submit" value="查看我的购物车"><br>
		</form>
</body>
</html>