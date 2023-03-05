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
	ArrayList<product> product=(ArrayList<product>)request.getAttribute("product");
	String id=request.getParameter("id");
	String select=request.getParameter("select");
	String type=request.getParameter("type");
	
	String all="/beautymall/search_special";
	String shouye="/beautymall/shopping/index.jsp";
	int num=0;
	if(id!=null)
	{
		all="/beautymall/search_special?id="+id+"&";
		shouye="/beautymall/shopping/index.jsp?id="+id;
	}
	else
	{
		all="/beautymall/search_special"+"?";
	}
	if(product!=null)
	{
		num=product.size();

	}
	else
	{num=0;}
	
%>
		
	<script>
	

		var name=<%=id%>;

//输入框查找-------------------------------------start	
	function search()
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
//输入框查找-------------------------------------end
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

	</script>
	
		
</head>
<body>

	<!-- 快捷导航 start-->
	<section class="shortcut">
		<div class="w">
		
			<div class="fl">
				<ul>
					<li>FaceMALL美妆商城欢迎您！&nbsp;</li>
					<li><a id="user" href="#" style="float:left;"></a><!--  -->
			</li>
					
					<li ><a id="log" href="../faces/shopping/login.xhtml" class="style_red">请登录</a>&nbsp;&nbsp;<a id="reg" href="" >立即注册</a>
					<script>
			var url=location.href;
			var num=url.indexOf("?");
			var id=<%=id%>
			if(id!=null)
			{
				document.getElementById("log").innerHTML="";
				document.getElementById("reg").innerHTML="";	
				document.getElementById("user").innerHTML=id;
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
		</script>
					</li>
			
				
				</ul>
			</div>
			<div class="fr">
				<ul >
					<li><a href="<%=shouye %>"  class="style_red">首页</a></li>
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
	<!-- 快捷导航 end-->
	
	
	<!-- 头部模块 start-->
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
	<div class="shopcar"><img src="shopping/image/shopcar.png" width="35px" height="32px" style="float:left;background-color:#b1191a"></img><a id="all_pro" href="javascript:;" onclick="jumptoall()">&nbsp;全部商品&nbsp;<img src="shopping/image/toright.png" width="8px"></img></a>
	</div>
	</header>
	<!-- 头部模块 end-->
	
	
	<!-- 显示产品 start-->
<div class="w" id="f">
	<div class="searchtype">
	<ul>
		<li><p style="margin-left:520px;margin-top:10px;float:left; font-size:20px;">全部产品</p></li>
		<li><select id="select" style="float:left; margin-left:20px;font-size:20px;margin-top:10px;" onchange="window.location=this.value;">
			<option  >请选择</option>
			<option value="<%=all %>type=<%=type %>&select=0" >综合</option>
			<option value="<%=all %>type=<%=type %>&select=1">价格升序</option>
			<option value="<%=all %>type=<%=type %>&select=2">价格降序</option>
		</select>
		</li>
	</ul>
	</div>
	<div class="showproduct">
		<form>
			<table>
				<td width="60" style="font-size:20px;margin-top:30px;">  </td>
				<td width="600"><p style="font-size:20px;margin-top:30px;">产品名称</p></td>
				<td width="100"><p style="font-size:20px;margin-top:30px;">品牌名</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">价格</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">折扣</p></td>
				<td width="60"><p style="font-size:20px;margin-top:30px;">收藏量</p></td>
				<td width="40"><p style="font-size:20px;margin-top:30px;">数量</p></td>
				<td width="60" style="font-size:20px;margin-top:30px;"></td>
				<td width="60" style="font-size:20px;margin-top:30px;"></td>
				</table>
		</form>
	</div>
	<!-- 循环输出产品 start-->
	<script>
		function buy(num)
		{
			var id="<%=id%>";
			var pid=document.getElementById("pid"+num).value;
			var pnum=document.getElementById("pnum"+num).value;
			if(id!=null&&id!="null")
			{
				alert("这里");
				window.location.href="carServlet?id="+id+"&pid="+pid+"&pnum="+pnum+"&methodName=5";
			}
			else
			{
				window.location.href="faces/shopping/login.xhtml";
			}
		}
		
		function addshopcar(num)
		{
		var id="<%=id%>";
			var pid=document.getElementById("pid"+num).value;
			var pnum=document.getElementById("pnum"+num).value;
			if(id!=null&&id!="null")
			{
				window.location.href="carServlet?id="+id+"&pid="+pid+"&pnum="+pnum+"&methodName=4";
			}
			else
			{
				window.location.href="faces/shopping/login.xhtml";
			}
		}
	
	</script>
	<%
	for(int i=0;i<num;i++)
	{
	//判断是否收藏某个产品
	 %>
	 <div class="showproduct">
		<form>
			<table>
			<input type="hidden" id="pid<%=i %>" value="<%=product.get(i).getPid()%>"></input>
				<td width="20" ><input type="button" value="收藏" style="font-size:22px;margin-top:30px;"></input></td>
				<td width="600"><p id="pname<%=i %>" style="font-size:22px;margin-top:30px;"><%=product.get(i).getPname() %></p></td>
				<td width="100"><p style="font-size:22px;margin-top:30px;"><%=product.get(i).getBrand() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;"><%=product.get(i).getPrice() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;"><%=product.get(i).getDiscount() %></p></td>
				<td width="30"><p style="font-size:22px;margin-top:30px;"><%=product.get(i).getLiked() %></p></td>
				<td width="40"><input id="pnum<%=i %>" type="text" value="1"  style="width:40px;font-size:22px;margin-top:30px;"></input></td>
				<td width="60"><input type="button" value="购买" onclick="buy(<%=i %>)" style="font-size:22px;margin-top:30px;background-color:#b1191a;color:#fff"></input></td>
				<td width="60"><input type="button" value="加入购物车" onclick="addshopcar(<%=i %>)" style="font-size:22px;margin-top:30px;"></input></td>
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