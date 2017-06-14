<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
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
			<li><a href="/EasyBuy/main/welcome.html">首页</a></li>
			<c:if test="${!empty parentCategorys}">
				<c:forEach items="${parentCategorys }" var = "parentCategory">
				<li><a href="/EasyBuy/product/list.html?epcId=${parentCategory.value.epcId}">${parentCategory.value.epcName }</a></li>
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
		<h2>修改用户</h2>
		<div class="manage">
			<form action="manage-result.html">
				<table class="form">
					<tr>
						<td class="field">用户名：</td>
						<td><input type="text" class="text" name="userName" value="${user.euUserName }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">密码：</td>
						<td><input type="text" class="text" id="passWord" name="passWord" value="${user.euPassword }" /></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<c:if test="${user.euSex == '男' }">
						<td><input type="radio" id="sex" name="sex" value="男" checked="checked" />男 <input type="radio" id="sex" name="sex" value="女" />女</td>
						</c:if>
						<c:if test="${user.euSex == '女' }">
						<td><input type="radio" id="sex" name="sex" value="男" />男 <input id="sex" type="radio" name="sex" value="女"  checked="checked"/>女</td>
						</c:if>
						<c:if test="${empty user.euSex}">
					    <td><input type="radio" id="sex" name="sex" value="女" />男 <input id="sex" type="radio" name="sex" value="女"  />女</td>
						</c:if>
					</tr>
					<tr>
						<td class="field">手机号码：</td>
						<td><input type="text" class="text" id="mobile" name="mobile" value="${user.euMobile }" /></td>
					</tr>
					<tr>
						<td class="field">送货地址：</td>
						<td><input type="text" class="text" id="address" name="address" value="${user.euAddress }" /></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="button" onclick="formSubmit()" name="modify" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
		<a href="/EasyBuy/order/view.html">点我进入订单管理</a>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
<script type="text/javascript">
function formSubmit(){
	var passWord = $("#passWord"),
	    user = $("#userName"),
		sex = $("#sex:checked"),
		mobile = $("#mobile"),
		address = $("#address"),
		passWordVal = $.trim(passWord.val()),
		sexVal = $.trim(sex.val())
		mobileVal = $.trim(mobile.val())
		addressVal = $.trim(address.val())
	if(!passWordVal){
		layer.tips("密码不能为空！",passWord);
		passWord.focus();
		return false;
	}
	if(!sexVal){
		layer.alert("请选择性别！");
		return false;
	}
	if(!mobileVal){
		layer.tips("手机号不能为空！",mobile);
		mobile.focus();
		return false;
	}
	if(mobileVal){
	 if(!(/^1[34578]\d{9}$/.test(mobileVal))){ 
        layer.tips("手机号格式不正确！",mobile);
		mobile.focus(); 
        return false; 
    } 
	}
	if(!addressVal){
		layer.tips("送货地址不能为空！",address);
		address.focus();
		return false;
	}
		$.ajax({
			type : "post",
			url : "/EasyBuy/user/modify.html",
			data : {
				passWord : passWordVal,
				sex : sexVal,
				mobile : mobileVal,
				address : addressVal,
			},
			success : function(result) { 
				if(result== "1"){
				 layer.alert("修改成功!")
				}
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});						
	
	
	
}

</script>
</body>
</html>
