<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
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
		<h2>我的订单</h2>
		<div class="manage">
			<div class="search">
				<form method="post" action="view.html">
					订单号：<input type="text" class="text" value="${eoId }" name="eoId" /> 
					订单状态：<select id="status" name="status">
					<option value="">请选择订单状态</option>
					<option value="未发货">未发货</option>
					<option value="已发货">已发货</option>
					<option value="已收货">已收货</option>
					<option value="取消审核中">取消审核中</option>
					<option value="已取消">已取消</option>
					</select> 
					<label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
					<input type="hidden" id="sta" value="${status }"/>
				</form>
			</div>
			<div class="spacer"></div>
			<table class="list">
				<tr>
					<th>订单号</th>
					<th>商品</th>
					<th>数量</th>
					<th>金额</th>
					<th>日期</th>
					<th>发货地址</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:if test="${!empty ordervos }">
					<c:forEach var="ordervo" items="${ordervos}">
					<tr>
					<td class="first w4 c">${ordervo.eoId }</td>
					<td class="w1 c"><a href="/EasyBuy/product/view.html?epId=${ordervo.eoProId}"><img src="/EasyBuy/${ordervo.epFileName }" onload='if (this.width>140 || this.height>226) if (this.width/this.height>140/226) this.width=50; else this.height=30;'/>${ordervo.epName}</a></td>
					<td>${ordervo.eoNumber}件</td>
					<td class="w1 c">${ordervo.eoCost}元</td>
					<td class="w1 c">${ordervo.creatTime}</td>
					<td class="w1 c">${user.euAddress}</td>
					<td class="w1 c">${ordervo.eoStatus}</td>
					<c:if test="${ordervo.eoStatus != '取消审核中' && ordervo.eoStatus!='已取消' && ordervo.eoStatus != '已收货'  && ordervo.eoStatus!='未发货'}">
					<td class="w1 c"><a href="updateStatus.html?eoId=${ordervo.eoId}&status=1">取消订单</a> <a href="updateStatus.html?eoId=${ordervo.eoId}&status=2">确认收货</a></td>
					</c:if>
					<c:if test="${ordervo.eoStatus=='已取消'} ">
					<td class="w1 c">订单已取消</td>
					</c:if>
					<c:if test="${ordervo.eoStatus=='取消审核中'} ">
					<td class="w1 c">订单正在取消中</td>
					</c:if>
					<c:if test="${ordervo.eoStatus=='未发货' }">
					<td class="w1 c"><a href="updateStatus.html?eoId=${ordervo.eoId}&status=1">取消订单</a></td>
					</c:if>
					<c:if test="${ordervo.eoStatus=='已收货' }">
					<td class="w1 c"><a href="order-modify.html">订单已完成</a></td>
					</c:if>
				</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="pager">
				<ul class="clearfix">
				    <li>共找到${maxCount }条订单,共${maxPage }页</li>
				    <c:if test="${targetPage>1 }">
				    	<li><a href="view.html?targetPage=${targetPage-1 }&eoId=${eoId}&status=${status}">上一页</a></li>
				    </c:if>
				    <c:set value="1" var="i"></c:set>
				    <c:forEach begin="1" end="${maxPage }">
				    	<c:if test="${targetPage == i}">
				    	 	<li class="current">${i }</li>
				    	</c:if>
				    	<c:if test="${targetPage != i}">
				    	 	<li ><a href="view.html?targetPage=${i }&eoId=${eoId}&status=${status}">${i }</a></li>
				    	</c:if>
				    	<c:set var="i" value="${i+1 }"></c:set>
				    </c:forEach>
					<c:if test="${targetPage < maxPage}">
					    <li><a href="view.html?targetPage=${targetPage+1 }&eoId=${eoId}&status=${status}">下一页</a></li>
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
<script type="text/javascript">
function select(){
 var select = document.all("status");
 var status = document.all("sta").value;
 for(var i=0;i<select.options.length;i++){
   if(status==select.options[i].value){
    select.options[i].selected = 'selected';
    break;
   }
 }
}
select();
</script>
</body>
</html>
