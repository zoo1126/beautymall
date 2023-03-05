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
      String path=request.getContextPath();
      String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
      %>
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>manage</title>
	<link rel="stylesheet" href="shopping/css/base.css"></link>
	<link rel="stylesheet" href="shopping/css/common.css"></link>
	
		<%
String id=request.getParameter("id"); 
String msg=request.getParameter("msg");

%>
	<script>
	
if("<%=msg%>"!="null")
	{alert("<%=msg%>");}

</script>
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

			
				
	
				
				document.getElementById("user").innerHTML=<%=id%>;
				
		
		function jumptoall()
			{
				var url=location.href;
				var num=url.indexOf("?");
				var name=null;
				if(num!=-1)
				{
					var str=url.substr(num+1);
					var arr=str.split(",");
					var idp=arr[0].indexOf("id");
					if(idp!=-1)
					{
						name=<%=id%>;
					}
					
				}
				if(name!=null&&name!="null")
						{window.open("/beautymall/search_all_servlet?id="+name+"&man=true");}
					else
					{
					window.open("/beautymall/search_all_servlet?man=true");
					}
			}
		
		function search()//输入关键字查找商品
	{
	var url=location.href;
			var num=url.indexOf("?");
			var name=null;
			var idp=-1;
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				var idp=arr[0].indexOf("id");
				if(idp!=-1)
				{name=<%=id%>;}
				
			}
	
		var value=document.getElementById("search").value;
		if(name!=null&&name!="null")
		{
		keyword="/beautymall/search_byinput"+"?id="+name+"&keyword="+value+"&man=true";
		window.open(keyword);
		}
		else{
		keyword="../faces/shopping/man_login.xhtml";
		window.open(keyword);
		}
	}
	function jumptobyid()
	{
	var url=location.href;
			var num=url.indexOf("?");
			var name=null;
			var idp=-1;
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				var idp=arr[0].indexOf("id");
			}

		if(idp!=-1)
		{
		window.location.href="search_byid.jsp?id="+<%=id%>;
		
		}
		else
		{window.location.href="../faces/shopping/man_login.xhtml";}
	}

	function jumptorefund()
	{
		var id="<%=id%>";

		if(id!=null&&id!="null")
		{
		window.location.href="/beautymall/a_search_s?id="+id;
		}
		else
		{window.location.href="../faces/shopping/man_login.xhtml";}
	}
	function jumptoindex()
	{
		var url=location.href;
			var num=url.indexOf("?");
			var name=null;
			var idp=-1;
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				var idp=arr[0].indexOf("id");
			}

		if(idp!=-1)
		{
		window.location.href="/beautymall/shopping/manage_index.jsp?id="+<%=id%>;
		
		}
		else
		{window.location.href="../faces/shopping/man_login.xhtml";}
	}
	function jumptoadd()
	{
		var id="<%=id%>";

		if(id!=null&&id!="null")
		{
		window.location.href="http://localhost:8888/beautymall/shopping/addproduct.jsp?id="+id;
		
		}
		else
		{window.location.href="../faces/shopping/man_login.xhtml";}
	}
		</script></li>
					
					
					
					
			
				
				</ul>
			</div>
			<div class="fr">
				<ul>
					<li><a href="javascript:;" onclick="jumptoindex()" class="style_red">首页</a></li>
					<li></li>
					<li><a >我的账号</a></li>

					<li></li>
					<li><a href="javascript:;" onclick="jumptoall()">产品大全</a></li>

					
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
		<input type="search" name="" id="search" style="border: 0; outline:none;" placeholder="输入商品名称或关键字"></input>
		<button style="border: 0; outline:none" onclick="search()">搜索</button>
	</div>
	
	</header>
	
	<!-- nav -->
	<nav class="nav">
		<div class="w">
			<div class="dropdown">
				<div class="dt" style="font-size:30px">管理操作</div>
				<div class="dd">
					<ul>
						<li><a style="font-size:25px" href="javascript:;" onclick="jumptobyid()">查询产品</a></li>
						<li><a style="font-size:25px" href="javascript:;" onclick="jumptoadd()" id="add">增加产品</a>
						<script>

					
						</script>
						</li>

						<li><a style="font-size:25px" href="javascript:;" onclick="jumptorefund()" >处理退款</a></li>
						<li><a id="adduser" style="font-size:25px" href="../faces/shopping/man_adduser.xhtml"></a>
							<script>
			var url=location.href;
			var num=url.indexOf("?");
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				if(arr.includes("true"))
				{
					document.getElementById("adduser").innerHTML="添加新用户";
					document.getElementById("adduser").href="../faces/shopping/man_adduser.xhtml?"+str;
				}
			}
		</script>
						</li>
					</ul>
				</div>
				
			</div>
			<div class="navitems">
				<ul>
					
				</ul>
			</div>
		</div>
		
	</nav>
	<!-- nav end-->
	<!-- mainshow -->
	<div class="w">
		<div class="main">
		<img src="shopping/image/mainshow.jpg" width="870px" height="361px" ></img>
		</div>
	</div>
	<!-- mainshow end-->
	
	<!-- foot -->
	<footer class="footer">
		<div class="w">
			<div class="mod_service">
				<ul>
					<li>
						<h5 style="margin-right:8px; float:left;"><img src="shopping/image/true.png" style="width:70px;height:70px;"></img></h5>
						<div class="service_txt">
							<h4>正品保障</h4>
							<p>全球货源</p>
							<p>提供发票</p>
						</div>
					</li>
					<li><h5 style="margin-right:8px; float:left;"><img src="shopping/image/tran.png" style="width:70px;height:70px;"></img></h5>
						<div class="service_txt">
							<h4>极速快递</h4>
							<p>快递速发</p>
							<p>保障运费</p>
						</div></li>
					<li><h5 style="margin-right:8px; float:left;"><img src="shopping/image/shouhou.png" style="width:70px;height:70px;"></img></h5>
						<div class="service_txt">
							<h4>在线售后</h4>
							<p>在线答疑</p>
							<p>保您满意</p>
						</div></li>
				</ul>
			</div>
			<div class="mod_copyright">
				<div class="copyright">
				<p>吉林大学软件学院软件工程专业2017级6班</p>
				<p>张园园  刘师妤  剪赫菲</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- foot end-->

</body>
</html>