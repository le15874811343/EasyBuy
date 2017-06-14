<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
				<c:if test="${epcId == parentCategory.value.epcId }">
				<li class="current"><a href="/EasyBuy/product/list.html?epcId=${parentCategory.value.epcId}">${parentCategory.value.epcName }</a></li>
				</c:if>
				<c:if test="${epcId != parentCategory.value.epcId }">
				<li ><a href="/EasyBuy/product/list.html?epcId=${parentCategory.value.epcId}">${parentCategory.value.epcName }</a></li>
				</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${epcId != 0 }">
			<li><a href="/EasyBuy/product/list.html?epRebate=0.7">促销</a></li>
			</c:if>
			<c:if test="${epcId == 0 }">
			<li class="current"><a href="/EasyBuy/product/list.html?epRebate=0.7">促销</a></li>
			</c:if>
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
	 <c:if test="${epcId == 0 }">
	  &gt; <a href="/EasyBuy/product/list.html?epRebate=0.7">促销</a>
	  </c:if>
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
		<div class="spacer"></div>
		<div class="last-view">
		<c:if test="${!empty user}">
		<h2>最近加入购物车</h2>
		<c:if test="${empty already }">
		<dl class="clearfix">
		<dt>购物车没有任何商品~</dt>
		</dl>
		</c:if>
		<c:if test="${!empty already }">
		<dl class="clearfix">
		<c:forEach var="already" items="${already }" end="2">
		 <dt><img src="/EasyBuy/${already.value.epFileName }"  onload='if (this.width>140 || this.height>226) if (this.width/this.height>140/226) this.width=50; else this.height=30;'/></dt>
		<dd><a href="/EasyBuy/product/view.html?epId=${already.value.epId}">${already.value.epName }</a></dd>
		</c:forEach>
		</dl>
		</c:if>
		</c:if>
		</div>
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>
			<div class="pager">
				<ul class="clearfix">
				    <li>共找到${maxCount }件商品,共${maxPage }页</li>
				    <c:if test="${targetPage>1 }">
				    	<li><a href="list.html?targetPage=${targetPage-1 }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">上一页</a></li>
				    </c:if>
				    <c:set value="1" var="i"></c:set>
				    <c:forEach begin="1" end="${maxPage }">
				    	<c:if test="${targetPage == i}">
				    	 	<li class="current">${i }</li>
				    	</c:if>
				    	<c:if test="${targetPage != i}">
				    	 	<li ><a href="list.html?targetPage=${i }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">${i }</a></li>
				    	</c:if>
				    	<c:set var="i" value="${i+1 }"></c:set>
				    </c:forEach>
					<c:if test="${targetPage < maxPage}">
					    <li><a href="list.html?targetPage=${targetPage+1 }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">下一页</a></li>
					</c:if>
				</ul>
			</div>
			<div class="clear"></div>
			<ul class="product clearfix">
				<c:if test="${!empty products}">
					<c:forEach var="product" items="${products}">
						<li>
							<dl>
							<dt><a href="/EasyBuy/product/view.html?epId=${product.value.epId}" target="_blank"><img src="/EasyBuy/${product.value.epFileName }"  onload='if (this.width>140 || this.height>226) if (this.width/this.height>140/226) this.width=140; else this.height=226;' /></a></dt>
							<dd class="title"><a href="product-view.html" target="_blank">${product.value.epName }</a></dd>
							<dd class="price">￥${product.value.epPrice }</dd>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				    <li>共找到${maxCount }件商品,共${maxPage }页</li>
				    <c:if test="${targetPage>1 }">
				    	<li><a href="list.html?targetPage=${targetPage-1 }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">上一页</a></li>
				    </c:if>
				    <c:set value="1" var="i"></c:set>
				    <c:forEach begin="1" end="${maxPage }">
				    	<c:if test="${targetPage == i}">
				    	 	<li class="current">${i }</li>
				    	</c:if>
				    	<c:if test="${targetPage != i}">
				    	 	<li ><a href="list.html?targetPage=${i }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">${i }</a></li>
				    	</c:if>
				    	<c:set var="i" value="${i+1 }"></c:set>
				    </c:forEach>
					<c:if test="${targetPage < maxPage}">
					    <li><a href="list.html?targetPage=${targetPage+1 }&epcId=${epcId}&epcChildId=${epcChildId}&epRebate=${epRebate}">下一页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
