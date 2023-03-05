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
	ArrayList<product> product=(ArrayList<product>)request.getAttribute("product");
	String id=request.getParameter("id");
	String select=request.getParameter("select");
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
		all="/beautymall/search_all_servlet?";
	}
	
	if(product!=null)
	{
		num=product.size();

	}
	else
	{num=0;}
	
	%>
		
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
	
	function search()
	{
		alert(document.getElementById("search").value);
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
			function search()//输入关键字查找商品
	{
		var name="<%=id%>";
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
	function dele()
			{
				var id=<%=id%>;

				if(id!=null&&id!="null")
				{
					var pid=document.getElementById("oldpid").innerHTML;
				
					window.location.href="/beautymall/deleteproduct?id="+id+"&pid="+pid;
				}
				else
					{
					window.location.href="http://localhost:8888/beautymall/faces/shopping/man_login.xhtml";
					}
				
			}
		function jumptochange()
	{
		var id="<%=id%>";
		
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
		</script></li>
					
					
					
					
			
				
				</ul>
			</div>
			<div class="fr">
				<ul>
					<li><a href="<%=shouye %>"  class="style_red">首页</a></li>
					<li></li>
					<li><a href="">我的账号</a></li>

					<li></li>
					<li><a href="<%=all %>man=true">产品大全</a></li>

					
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
	
	<!-- 全部商品 -->
	
	</header>
	<!-- 头部模块 end-->
	
	
	<!-- 显示产品 start-->
<div class="w" id="f">
	<div class="searchtype">
	<ul>
		<li><p style="margin-left:520px;margin-top:10px;float:left; font-size:20px;">全部产品</p></li>
		<li><select id="select" style="float:left; margin-left:20px;font-size:20px;margin-top:10px;" onchange="window.location=this.value;">
			<option  >请选择</option>
			<option value="<%=all %>select=0&man=true" >综合</option>
			<option value="<%=all %>select=1&man=true">价格升序</option>
			<option value="<%=all %>select=2&man=true">价格降序</option>
		</select>
		</li>
	</ul>
	</div>
	<div class="showproduct">
		<form>
			<table>
				<td width="100"><p style="font-size:20px;margin-top:30px;">产品编号</p></td>
				<td width="540"><p style="font-size:20px;margin-top:30px;">产品名称</p></td>
				<td width="100"><p style="font-size:20px;margin-top:30px;">品牌名</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">价格</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">折扣</p></td>
				<td width="60"><p style="font-size:20px;margin-top:30px;">收藏量</p></td>
				<td width="120"><p style="font-size:20px;margin-top:30px;">种类</p></td>
				<td width="80" style="font-size:20px;margin-top:30px;"></td>
				<td width="80" style="font-size:20px;margin-top:30px;"></td>
				</table>
		</form>
	</div>
	<!-- 循环输出产品 start-->
	<%
	for(int i=0;i<num;i++)
	{
	//判断是否收藏某个产品
	 %>
	 <div class="showproduct">
		<form name="th" id="th">
			<table>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="oldpid"><%=product.get(i).getPid() %></p></td>
				<td width="540"><p style="font-size:22px;margin-top:30px;" id="pname"><%=product.get(i).getPname() %></p></td>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="brand"><%=product.get(i).getBrand() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="price"><%=product.get(i).getPrice() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="discount"><%=product.get(i).getDiscount() %></p></td>
				<td width="30"><p style="font-size:22px;margin-top:30px;" id="liked"><%=product.get(i).getLiked() %></p></td>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="sort"><%=product.get(i).getPsort() %></p></td>
				<td width="60"><input type="button" value="删除" onclick="dele()" style="font-size:22px;margin-top:30px;background-color:#b1191a;color:#fff"></input></td>
				<td width="60"><input type="button" value="修改" onclick="jumptochange()" style="font-size:22px;margin-top:30px;"></input></td>
				</table>
		</form>
	</div>
		<%
		}
		 %>
	<!-- 循环输出产品 end-->

	</div>
	<!-- 显示产品 end-->
</body>

</html>