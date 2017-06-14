<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<script type="text/javascript" src="/EasyBuy/js/jquery.min.js"></script>
<script type="text/javascript" src="/EasyBuy/js/layer/layer.js"></script>
<link type="text/css" rel="stylesheet" href="/EasyBuy/css/style.css" />
<link type="text/css" rel="stylesheet" href="/EasyBuy/css/layer.css" />
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
	您现在的位置：<a href="index.html">易买网</a> &gt; 在线留言
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
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
			 	<c:if test="${!empty comments}">
			 		<c:forEach items="${comments }" var="comments">
			 		   <li>
					<dl>
						<dt>${comments.ecContent}</dt>
						<dd class="author">网友：${comments.ecNickTime } <span class="timer">${comments.ecCreateTime }</span></dd>
						<dd>${comments.ecReply}</dd>
						<dd><input type="hidden" id="oldCount" value="${oldCount}"></dd>
					</dl>
				</li>
			 		</c:forEach>
			 	</c:if>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				    <li>共找到${maxCount }条留言,共${maxPage }页</li>
				    <c:if test="${targetPage>1 }">
				    	<li><a href="/EasyBuy/comment/view.html?targetPage=${targetPage-1 }">上一页</a></li>
				    </c:if>
				    <c:set value="1" var="i"></c:set>
				    <c:forEach begin="1" end="${maxPage }">
				    	<c:if test="${targetPage == i}">
				    	 	<li class="current">${i }</li>
				    	</c:if>
				    	<c:if test="${targetPage != i}">
				    	 	<li ><a href="/EasyBuy/comment/view.html?targetPage=${i}">${i }</a></li>
				    	</c:if>
				    	<c:set var="i" value="${i+1 }"></c:set>
				    </c:forEach>
					<c:if test="${targetPage < maxPage}">
					    <li><a href="/EasyBuy/comment/view.html?targetPage=${targetPage+1}">下一页</a></li>
					</c:if>
				</ul>
			</div>
			<div id="reply-box">
				<form>
					<table>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent" id="guestContent"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="button" onclick="formSubmit()" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
<script type="text/javascript">
function formSubmit(){
	var guestContent = $("#guestContent"),
		guestContentVal = $.trim(guestContent.val());
	if(!guestContentVal){
		layer.tips("内容不能为空！",guestContent);
		guestContent.focus();
		return false;
	}
	
		$.ajax({
			type : "post",
			url : "/EasyBuy/comment/save.html",
			data : {
				guestName : guestNameVal,
				guestContent : guestContentVal,
			},
			success : function(result) { 
				if(result == "0"){
				  layer.alert("填写的数据有误");
				}
			    if (result == "1"){
			      window.location.href="/EasyBuy/comment/view.html";
			    }
			    if(result == "3"){
			      layer.alert("发生错误,留言失败");
			    }
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});	
}

function refresh(){
var oldCount = $("#oldCount"),
    oldCountVal = $.trim(oldCount.val())
$.ajax({
			type : "post",
			url : "/EasyBuy/comment/refresh.html",
			data : {
				oldCount : oldCountVal,
			},
			success : function(result) {
				if(result == "1"){
				  window.location.href="/EasyBuy/comment/view.html";
				}
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});	
		window.setTimeout("refresh();",5000);					
}
refresh();
	</script>
</body>
</html>
