<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="/EasyBuy/css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
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
	您现在的位置：<a href="/EasyBuy/main/welcome.html">易买网</a> 
	<c:if test="${!empty epcId }">
	<c:forEach items="${parentCategorys }" var = "parentCategory">
	  <c:if test="${epcId == parentCategory.value.epcId }">
	  &gt;<a href="/EasyBuy/product/list.html?epcId=${parentCategory.value.epcId}">${parentCategory.value.epcName}</a> 
	  </c:if>
	</c:forEach>
	</c:if>
	<c:if test="${!empty epcChildId }">
	<c:forEach items="${childCategorys }" var = "childCategory">
	<c:if test="${epcChildId ==childCategory.value.epcId  }">
	 &gt;${childCategory.value.epcName}
	</c:if>
	</c:forEach>
	</c:if>
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<c:if test="${!empty parentCategorys }">
					<c:forEach items="${parentCategorys }" var = "parentCategory">
						<dt>${parentCategory.value.epcName }</dt>
						<c:if test="${!empty childCategorys}">
							<c:forEach items="${childCategorys }" var="childCategory">
								<c:if test="${childCategory.value.epcParentId == parentCategory.value.epcId}">
									<dd><a href="/EasyBuy/product/list.html?epcChildId=${childCategory.value.epcId }&epcId=${childCategory.value.epcParentId}">${childCategory.value.epcName}</a></dd>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</dl>
		</div>
	</div>
	<div id="product" class="main">
		<c:if test="${!empty productDea }">
		<h1>${productDea.epName}</h1>
		<div class="infos">
			<div class="thumb"><img src="/EasyBuy/${productDea.epFileName}" /></div>
			<div class="buy">
				<p>商城价：<span class="price">￥<fmt:formatNumber type="number" value="${productDea.epPrice*productDea.epRebate}" pattern="0.00" maxFractionDigits="2"/></span></p>
				<c:if test="${productDea.epRebate<1 }">
					<p>原   价：￥${productDea.epPrice}</p>
					<p>折  扣 ：${productDea.epRebate }折</p>
				</c:if>
				<p>库　存：${productDea.epStock}</p>
				<c:if test="${!empty  user}">
				<div class="button"><input type="button" name="button" value="" onclick="goBuy(${productDea.epId})" /><a href="/EasyBuy/shopping/addCar.html?epId=${productDea.epId}">放入购物车</a></div>				
				</c:if>
				<c:if test="${empty user }">
				<div class="button"><input type="button" name="button" value="" onclick="login()" /><a href="/EasyBuy/login.jsp">放入购物车</a></div>
				</c:if>
			</div>
			<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				${productDea.epDescription }
			</div>
		</div>
			<div class="clear"></div>
		</div>
		</c:if>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
<script type="text/javascript">
function goBuy(epId){
var url="/EasyBuy/shopping/quik.html?epId="+epId;
    window.location.href=url;
}
function login(){
var url="/EasyBuy/login.jsp";
    window.location.href=url;
}

</script>
</body>
</html>
