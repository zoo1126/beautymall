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
			function to0()
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					window.location.href="/beautymall/search_special?id="+name+"&type=0";
				}
				else
				{
					window.location.href="/beautymall/search_special?type=0";
				}	
			}
			function to1()
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					window.location.href="/beautymall/search_special?id="+name+"&type=1";
				}
				else
				{
					window.location.href="/beautymall/search_special?type=1";
				}	
			}
			function to2()
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					window.location.href="/beautymall/search_special?id="+name+"&type=2";
				}
				else
				{
					window.location.href="/beautymall/search_special?type=2";
				}	
			}
			function to3()
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					window.location.href="/beautymall/search_special?id="+name+"&type=3";
				}
				else
				{
					window.location.href="/beautymall/search_special?type=3";
				}	
			}
			function tosort(sort)
			{
				var name="<%=id%>";
				if(name!=null&&name!="null")
				{
					window.location.href="/beautymall/search_bysort?id="+name+"&sort="+sort.id;
				}
				else
				{
					window.location.href="/beautymall/search_bysort?sort="+sort.id;
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
	
	<!-- nav -->
	<nav class="nav">
		<div class="w">
			<div class="dropdown">
				<div class="dt">全部商品分类</div>
				<div class="dd">
					<ul>
						<li><a>基础护肤</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="water" onclick="tosort(this)">水</a>&nbsp;&nbsp;<a href="javascript:;" id="jinghua" onclick="tosort(this)">精华</a>&nbsp;&nbsp;<a href="javascript:;" id="ruye" onclick="tosort(this)">乳液</a>&nbsp;&nbsp;<a href="javascript:;" id="mianshuang" onclick="tosort(this)">面霜</a>&nbsp;&nbsp;<a href="javascript:;" id="mask" onclick="tosort(this)">面膜</a></li>
						<li><a>精致妆容</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="face" onclick="tosort(this)">脸部</a>&nbsp;&nbsp;<a href="javascript:;" id="meimao" onclick="tosort(this)">眉部</a>&nbsp;&nbsp;<a href="javascript:;" id="lip" onclick="tosort(this)">唇部</a>&nbsp;&nbsp;<a href="javascript:;" id="eye" onclick="tosort(this)">眼部</a>&nbsp;&nbsp;<a href="javascript:;" id="xiurong" onclick="tosort(this)">修容</a></li>
						<li><a>个人护理</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="teeth" onclick="tosort(this)">牙刷牙膏</a>&nbsp;&nbsp;<a href="javascript:;" id="fangshai" onclick="tosort(this)">防晒</a>&nbsp;&nbsp;<a href="javascript:;" id="runfuru" onclick="tosort(this)">润肤乳</a>&nbsp;&nbsp;<a href="javascript:;" id="tuomao" onclick="tosort(this)">脱毛</a></li>
						<li><a>美发护发</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="hair" onclick="tosort(this)">发膜</a>&nbsp;&nbsp;<a href="javascript:;" id="shampoo" onclick="tosort(this)">洗发水</a>&nbsp;&nbsp;<a href="javascript:;" id="ranfa" onclick="tosort(this)">染发剂</a>&nbsp;&nbsp;<a href="javascript:;" id="hairtool" onclick="tosort(this)">美发工具</a></li>
						<li><a>气质香氛</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="womanperfume" onclick="tosort(this)">女士香水</a>&nbsp;&nbsp;<a href="javascript:;" id="manperfume" onclick="tosort(this)">男士香水</a>&nbsp;&nbsp;<a href="javascript:;" id="xianggao" onclick="tosort(this)">香膏</a></li>
						<li><a>男士护肤</a><img src="image/toright2.png" width="10px" height="15px"></img>&nbsp;&nbsp;<a href="javascript:;" id="tixu" onclick="tosort(this)">剃须刀</a>&nbsp;&nbsp;<a href="javascript:;" id="rumian" onclick="tosort(this)">乳液面霜</a>&nbsp;&nbsp;<a href="javascript:;" id="jiemian" onclick="tosort(this)">洁面产品</a></li>
					</ul>
				</div>
				
			</div>
			<div class="navitems">
				<ul>
					<li><a href="javascript:;" onclick="to0();">折扣商品</a></li>
					<li><a href="javascript:;" onclick="to1();">人气彩妆</a></li>
					<li><a href="javascript:;" onclick="to2();">美妆工具</a></li>
					<li><a href="javascript:;" onclick="to3();">大牌口红</a></li>
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