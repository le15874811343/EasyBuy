<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买购 - 首页</title>
<script type="text/javascript" src="/EasyBuy/js/jquery.min.js"></script>
<script type="text/javascript" src="/EasyBuy/js/layer/layer.js"></script>
<link type="text/css" rel="stylesheet" href="/EasyBuy/css/layer.css" />
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
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎回到易购网</h1>
			<form id="loginForm" method="post" action="/EasyBuy/user/login.html" >
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="userName" id="userName" onkeyup="value=value.replace(/[\W]/g,'') " onchange="value=value.replace(/[\W]/g,'') "  /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="password" name="password" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" id="code" name="veryCode"  /><img id="veryCode" src="/EasyBuy/identifyingCode/load.html" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" name="login" onclick="formSubmit()" value="立即登录" /></label></td>
					</tr>
					<tr>
					 <td><input type="hidden" name = "url" value = "/main/welcome.html" /></td>
				    </tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
<script>
function formSubmit(){
	var userName = $("#userName"),
		password = $("#password"),
		code = $("#code"),
		userNameVal = $.trim(userName.val()),
		passwordVal = $.trim(password.val()),
		codeVal = $.trim(code.val());
		
	if(!userNameVal){
		layer.tips("用户名不能为空！",userName);
		userName.focus();
		return false;
	}
	if(!passwordVal){
		layer.tips("密码不能为空！",password);
		password.focus();
		return false;
	}
	if(!codeVal){
		layer.tips("验证码不能为空！",code);
		code.focus();
		return false;
	}
	if(codeVal){
		if(codeVal.length<4){
			layer.tips("验证码长度不匹配！",code);
			code.focus();
			return false;
		}
	}
		$.ajax({
			type : "post",
			url : "/EasyBuy/user/match.html",
			data : {
				userName : userNameVal,
				password : passwordVal,
				code:codeVal
			},
			success : function(result) { 
				 if(result=="0"){
					layer.tips("验证码输入错误！",code);
					code.focus();
					updateIdentifyingCode();
					return false;
				}
				else if(result=="2"){
				    layer.tips("用户不存在！",userName);
					userName.focus();
					updateIdentifyingCode();
					return false;
				}
				else if(result=="3"){
				    layer.tips("用户名密码不匹配！",userName);
					userName.focus();
					updateIdentifyingCode();
					return false;
				}
				else if(result=="1"){
					document.getElementById("loginForm").submit();
				}
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});						
	
	
	
}
$(function() {
	$("#veryCode").click(function() {
		var c = $("#veryCode")[0];
		$("#veryCode").attr("src",c.src+"?t="+new Date().getTime());
		$('#code').val('');
	});
});

function updateIdentifyingCode(){
	document.getElementById("veryCode").click();
}
</script>
</body>
</html>
