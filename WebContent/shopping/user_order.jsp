<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="product.product"%>
<%@ page import="orders.order"%>
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
	ArrayList<order> orders=(ArrayList<order>)request.getAttribute("orders");
	String id=request.getParameter("id");
	String msg=request.getParameter("msg");
	System.out.println(msg);
	String select=request.getParameter("select");
	String all="/beautymall/search_all_servlet";
	String shouye="/beautymall/shopping/index.jsp";
	int num=0;
	if(id!=null)
	{
		all="/beautymall/search_all_servlet"+"?id="+id+"&";
		shouye="/beautymall/shopping/index.jsp"+"?id="+id;
	}
	else
	{
		all="/beautymall/search_all_servlet"+"?";
	}
	if(orders!=null)
		num=orders.size();
	
	
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
					<li>FaceMALL美妆商城欢迎您！&nbsp;</li>
					<li><a id="user" href="" style="float:left;"></a><!--  -->
		<script>
		var msg="<%=msg%>";
		if(msg!=null&&msg!="null")
			{
				if(msg=="fail")
					{alert("申请失败！");}
				else
					{alert("申请成功！");}
			}
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
					
					<li ><a id="log" href="../faces/shopping/login.xhtml" class="style_red">请登录</a>&nbsp;&nbsp;<a id="reg" href="../faces/shopping/signin.xhtml" >立即注册</a></li>
			
				
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
	<div class="shopcar"><img src="shopping/image/shopcar.png" width="35px" height="32px" style="float:left;background-color:#b1191a"></img><a id="all_pro" href="javascript:;" onclick="jumptoall()">&nbsp;全部商品&nbsp;<img src="shopping/image/toright.png" width="8px"></img></a>
	</div>
	</header>
	
	<!-- 显示产品 start-->
<div class="w" id="f">
	<div class="searchtype">
	<ul>
		<li><p style="margin-left:520px;margin-top:10px;float:left; font-size:20px;">全部订单</p></li>
		
		</li>
	</ul>
	</div>
	<div class="showproduct">
		<form>
			<table>
				
				<td width="300"><p style="font-size:15px;margin-top:30px;">订单编号</p></td>
				<td width="100"><p style="font-size:15px;margin-top:30px;">产品号</p></td>
				<td width="60" ><p style="font-size:15px;margin-top:30px;">价格</p></td>
				<td width="60" ><p style="font-size:15px;margin-top:30px;">折扣</p></td>
				<td width="40"><p style="font-size:15px;margin-top:30px;">数量</p></td>
				<td width="150"><p style="font-size:15px;margin-top:30px;">下单时间</p></td>
				<td width="150" ><p style="font-size:15px;margin-top:30px;">完成时间</p></td>
				<td width="150"><p style="font-size:15px;margin-top:30px;">退款时间</p></td>
				<td width="30"><p style="font-size:15px;margin-top:30px;">订单状态</p></td>
				</table>
		</form>
	</div>
	<!-- 循环输出产品 start-->
	<script>
	function applyrefund(num)
	{
		var id="<%=id%>";
		var oid=document.getElementById("oid"+num).innerHTML;
		if(id!=null&&id!="null")
		{
			window.location.href="/beautymall/u_up_s?id="+id+"&oid="+oid;
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
			
				<td width="300"><p id="oid<%=i%>"style="font-size:15px;margin-top:30px;"><%=orders.get(i).getOid() %></p></td>
				<td width="100"><p id="pid<%=i%>"style="font-size:15px;margin-top:30px;"><%=orders.get(i).getO_pid() %></p></td>
				<td width="60" ><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getPrice() %></p></td>
				<td width="60" ><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getDiscount() %></p></td>
				<td width="40"><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getPcount()%></p></td>
				<td width="150"><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getEst_time()%></p></td>
				<td width="150" ><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getFin_time() %></p></td>
				<td width="150"><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getRet_time()%></p></td>
				<td width="30"><p style="font-size:15px;margin-top:30px;"><%=orders.get(i).getState()%></p></td>
				<td width="60"><input type="button" value="确认收货" onclick="ok(<%=i %>)" style="font-size:22px;margin-top:30px;background-color:#b1191a;color:#fff"></input></td>
				<td width="60"><input type="button" value="申请退款" onclick="applyrefund(<%=i %>)" style="font-size:22px;margin-top:30px;"></input></td>
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