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
      String path=request.getContextPath();
      String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
      %>
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>facemall</title>

	<link rel="stylesheet" type="text/css" href="shopping/css/base.css"></link>
	<link rel="stylesheet"  type="text/css" href="shopping/css/common.css"></link>
	<%
	
	String id=request.getParameter("id");
	String select=request.getParameter("select");
	request.setAttribute("id", id);
	String msg=request.getParameter("msg");
	product product=(product)request.getAttribute("product");
	String all="/beautymall/search_all_servlet";
	String shouye="/beautymall/shopping/manage_index.jsp";
	int num=0;
	if(id!=null)
	{
		all="/beautymall/search_all_servlet"+"?id="+id+"&";
		shouye="/beautymall/shopping/manage_index.jsp"+"?id="+id;
	}
	else
	{
		all="/beautymall/search_all_servlet"+"?";
	}
	
	
	%>
			<script>
	
if("<%=msg%>"!="null")
	{alert("<%=msg%>");}

</script>
	<script>

	var url=location.href;
	var num=url.indexOf("?");
	if(num!=-1)
	{
		var str=url.substr(num+1);
		var arr=str.split(",");
		var id=arr[0].substr(0,2);
		var name=arr[0].substr(3);
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
	
	function jumptochange()
	{
		var id=<%=id%>;
		var oldpid=document.getElementById("oldpid").innerHTML;
		var pname=document.getElementById("pname").innerHTML;
		var sort=document.getElementById("sort").innerHTML;
		var brand=document.getElementById("brand").innerHTML;
		var liked=document.getElementById("liked").innerHTML;
		var discount=document.getElementById("discount").innerHTML;
		var price=document.getElementById("price").innerHTML;
		if(id!=null)
			{
			window.location.href="shopping/changeproduct.jsp?id="+id+"&oldpid="+oldpid+"&pname="+pname+"&sort="+sort+"&brand="+brand+"&price="+price+"&liked="+liked+"&discount="+discount;
			
			}
		else
			window.location.href="../faces/shopping/man_login.xhtml";
	}		

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
			var url=location.href;
			var num=url.indexOf("?");
			if(num!=-1)
			{
				var str=url.substr(num+1);
				var arr=str.split(",");
				
				document.getElementById("user").innerHTML=<%=id%>;
			}
			

			function dele()
			{
				var id=<%=id%>;

				if(id!=null&&id!="null")
				{
					var pid=document.getElementById("pid").innerHTML;
					
					window.location.href="/beautymall/deleteproduct?id="+id+"&pid="+pid;
				}
				else
					{
					window.location.href="http://localhost:8888/beautymall/faces/shopping/man_login.xhtml";
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
					<li><a href="<%=all %>">产品大全</a></li>

					
				</ul>
			</div>
		</div>
	</section>
	
	
	
	<!-- 头部模块 -->
	<header class="header w">
	<!-- logo -->
		<div class="logo">
			<h1>
				<a href="index.html">商城</a>
			</h1>
		</div>
	<!-- 搜索 -->
	<div class="search">
		<input type="search"  id="search" style="border: 0; outline:none" placeholder="输入商品名称或关键字"></input>
		<button style="border: 0; outline:none" onclick="search()">搜索</button>
	</div>
	
	
	</header>
	<!-- 头部模块 end-->
	
	<center>
	<br></br>
	<script>
	function tobyid()
	{
		var id=<%=id%>;
		if(id!=null&&id!="null")
			{document.th.action="/beautymall/search_byid?id="+id;
			document.th.submit();
			}
		else
		{
			window.location.replace("http://localhost:8888/beautymall/faces/shopping/man_login.xhtml");
		
		}
	}
	
	</script>
	<form method="post" name="th">
		<input style="font-size:20px;" name="pid" type="text" placeholder="请输入商品编号"></input>
		<input style="font-size:20px;background-color:#b1191a ; color:#fff" type="button" onclick="tobyid()" value="查找" ></input>
	</form>
	<br></br>
	</center>
	<div class="w">
	<%if(product!=null)
	{ %>
	<div class="showproduct">
		<form id="byid" name="byid">
			<table>
				<td width="100"><p style="font-size:22px;margin-top:30px;" name="oldpid" id="oldpid"><%=product.getPid() %></p></td>
				<td width="600"><p style="font-size:22px;margin-top:30px;" id="pname" ><%=product.getPname() %></p></td>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="brand"><%=product.getBrand() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="price"><%=product.getPrice() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="discount"><%=product.getDiscount() %></p></td>
				<td width="30"><p style="font-size:22px;margin-top:30px;" id="liked"><%=product.getLiked() %></p></td>
				<td width="40"><p style="font-size:22px;margin-top:30px;" id="sort"><%=product.getPsort() %></p></td>
				<td width="60"><input type="button"  onclick="dele()" value="删除" style="font-size:22px;margin-top:30px;background-color:#b1191a;color:#fff"></input></td>
				<td width="60"><input type="button" onclick="jumptochange()" value="修改" style="font-size:22px;margin-top:30px;"></input></td>
				</table>
		</form>
	</div>
	<%} %>
	</div>
</body>
</html>