<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="/EasyBuy/css/style.css" />
<script type="text/javascript" src="/EasyBuy/scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><h2 ><font color="orange">易购网</font></h2></div>
	<div class="help">
	<c:if test="${!empty user }">
	<a href="/EasyBuy/shopping/view.html" class="shopping">购物车</a>
	<a href="/EasyBuy/login/user.jsp">${user.euUserName}</a>
	<a href="/EasyBuy/order/view.html">我的订单</a>
	<a href="/EasyBuy/user/out.html">注销</a>
	<a href="/EasyBuy/comment/view.html">留言</a>
	</c:if>
	<c:if test="${empty  user}">
	<a href="/EasyBuy/login.jsp" class="shopping">购物车</a>
	<a href="/EasyBuy/login.jsp">登录</a><a href="/EasyBuy/register.jsp">注册</a>
	<a href="/EasyBuy/login.jsp">留言</a>
	</c:if>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li ><a href="/EasyBuy/main/welcome.html">首页</a></li>
			<c:if test="${!empty parentCategorys}">
				<c:forEach items="${parentCategorys }" var = "parentCategory">
				<li ><a href="/EasyBuy/product/list.html?epcId=${parentCategory.value.epcId}">${parentCategory.value.epcName }</a></li>
				</c:forEach>
			</c:if>
			<li><a href="/EasyBuy/product/list.html?epRebate=0.7">促销</a></li>
		</ul>
	</div>
</div> 
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
		<c:if test="${!empty  childCategorys}">
		<c:set var = "i" value="0"></c:set>
		<c:set var = "length" value="${fn :length(childCategorys) }"></c:set>
			<c:forEach var="childCategory" items="${childCategorys }">
				<c:if test="${i == 0}">
					<li class="first"><a href="/EasyBuy/product/list.html?epcChildId=${childCategory.value.epcId}">${childCategory.value.epcName}</a></li>
				</c:if>
				<c:if test="${i != 0 && i!= length-1}">
					<li><a href="/EasyBuy/product/list.html?epcChildId=${childCategory.value.epcId}">${childCategory.value.epcName}</a></li>
				</c:if>
				<c:if test="${i == length-1}">
					<li class="last"><a href="/EasyBuy/product/list.html?epcChildId=${childCategory.value.epcId}">${childCategory.value.epcName}</a></li>
				</c:if>
				<c:set var = "i" value="${i+1 }"></c:set>
			</c:forEach>
		</c:if>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<div class="shadow">
			<em class="corner lb"></em>
			<em class="corner rt"></em>
			<div class="box">
				<div class="msg">
					<p>恭喜：购买成功！</p>
					<p>正在进入首页...</p>
					<script type="text/javascript">
	setTimeout( function (){
    var url="/EasyBuy/main/welcome.html";
    window.location.href=url;
  }, 3000);
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
