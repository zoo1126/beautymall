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
	String keyword=request.getParameter("keyword");
	String all="/beautymall/search_all_servlet";
	String key="/beautymall/search_byinput";
	String shouye="/beautymall/shopping/manage_index.jsp";
	int num=0;
	if(id!=null)
	{
		all="/beautymall/search_all_servlet"+"?id="+id+"&";
		key="/beautymall/search_byinput"+"?id="+id+"&";
		shouye="/beautymall/shopping/manage_index.jsp"+"?id="+id;
	}
	else
	{
		all="/beautymall/search_all_servlet"+"?";
		key="/beautymall/search_byinput?";
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

		<!-- ???????????? -->
	<section class="shortcut">
		<div class="w">
		
			<div class="fl">
				<ul>
					<li>xx????????????????????????&nbsp;</li>
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
			function search()//???????????????????????????
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
		</script></li>
					
					
					
					
			
				
				</ul>
			</div>
			<div class="fr">
				<ul>
					<li><a href="<%=shouye %>"  class="style_red">??????</a></li>
					<li></li>
					<li><a href="">????????????</a></li>

					<li></li>
					<li><a href="<%=all %>man=true">????????????</a></li>

					
				</ul>
			</div>
		</div>
	</section>
	
	
	
	<!-- ???????????? -->
	<header class="header w">
	<!-- logo -->
		<div class="logo">
			<h1>
				<a href="index.html">??????</a>
			</h1>
		</div>
	<!-- ?????? -->
	<div class="search">
		<input type="search"  id="search" style="border: 0; outline:none" placeholder="??????????????????????????????"></input>
		<button style="border: 0; outline:none" onclick="search()">??????</button>
	</div>
	
	<!-- ???????????? -->
	
	</header>
	<!-- ???????????? end-->
	
	
	<!-- ???????????? start-->
<div class="w" id="f">
	<div class="searchtype">
	<ul>
		<li><p style="margin-left:520px;margin-top:10px;float:left; font-size:20px;">????????????</p></li>
		<li><select id="select" style="float:left; margin-left:20px;font-size:20px;margin-top:10px;" onchange="window.location=this.value;">
			<option  >?????????</option>
			<option value="<%=key %>keyword=<%=keyword %>&select=0&man=true" >??????</option>
			<option value="<%=key %>keyword=<%=keyword %>&select=1&man=true">????????????</option>
			<option value="<%=key %>keyword=<%=keyword %>&select=2&man=true">????????????</option>
		</select>
		</li>
	</ul>
	</div>
	<div class="showproduct">
		<form >
			<table>
				<td width="100"><p style="font-size:20px;margin-top:30px;">????????????</p></td>
				<td width="600"><p style="font-size:20px;margin-top:30px;">????????????</p></td>
				<td width="100"><p style="font-size:20px;margin-top:30px;">?????????</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">??????</p></td>
				<td width="60" ><p style="font-size:20px;margin-top:30px;">??????</p></td>
				<td width="60"><p style="font-size:20px;margin-top:30px;">?????????</p></td>
				<td width="60"><p style="font-size:20px;margin-top:30px;">??????</p></td>
				<td width="80" style="font-size:20px;margin-top:30px;"></td>
				<td width="80" style="font-size:20px;margin-top:30px;"></td>
				</table>
		</form>
	</div>
	<!-- ?????????????????? start-->
	<%
	for(int i=0;i<num;i++)
	{
	//??????????????????????????????
	 %>
	 <div class="showproduct">
		<form name="th" id="th">
			<table>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="oldpid"><%=product.get(i).getPid() %></p></td>
				<td width="600"><p style="font-size:22px;margin-top:30px;" id="pname"><%=product.get(i).getPname() %></p></td>
				<td width="100"><p style="font-size:22px;margin-top:30px;" id="brand"><%=product.get(i).getBrand() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="price"><%=product.get(i).getPrice() %></p></td>
				<td width="80"><p style="font-size:22px;margin-top:30px;" id="discount"><%=product.get(i).getDiscount() %></p></td>
				<td width="30"><p style="font-size:22px;margin-top:30px;" id="liked"><%=product.get(i).getLiked() %></p></td>
				<td width="40"><p style="font-size:22px;margin-top:30px;"  id="sort"><%=product.get(i).getPsort() %></p></td>
				<td width="60"><input type="button" value="??????" onclick="dele()" style="font-size:22px;margin-top:30px;background-color:#b1191a;color:#fff"></input></td>
				<td width="60"><input type="button" value="??????" onclick="jumptochange()" style="font-size:22px;margin-top:30px;"></input></td>
				</table>
		</form>
	</div>
		<%
		}
		 %>
	<!-- ?????????????????? end-->

	</div>
	<!-- ???????????? end-->
</body>

</html>