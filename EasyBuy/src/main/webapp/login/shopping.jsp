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
	您现在的位置：<a href="index.html">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form  id="buyForm" action="/EasyBuy/order/submit.html" method="post">
			<table id="product">
				<c:if test="${!empty carProduct}">
					<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				    </tr>
				    <c:set var="rows" value="1"></c:set>
				    <c:forEach items="${carProduct }" var="carProduct">
				    <tr id="product_id_1">
					<td class="thumb"><img src="/EasyBuy/${carProduct.value.epFileName }" onload='if (this.width>140 || this.height>226) if (this.width/this.height>140/226) this.width=50; else this.height=30;' /><a href="/EasyBuy/product/view.html?epId=${bestSell.value.epId}">${carProduct.value.epName }</a></td>
					<td class="price" id="price_id_1">
						<span id="${rows }price">${carProduct.value.epPrice }</span>
						<input type="hidden" value="${carProduct.value.epPrice }" />
						<input type="hidden" id="${rows }eid" value="${carProduct.value.epId }">
					</td>
					<td class="number">
						<input type="button" value="+" onclick="changeNumber('+',${rows},${carProduct.value.epId })"/><input width="10px" id="${rows }number" type="text" name="number" value="${carProduct.value.userCout }" onkeyup="numberForm(this,${carProduct.value.epId},${rows })" onchange="numberForm(this)"  /><input type="button" value="-" onclick="changeNumber('-',${rows},${carProduct.value.epId})" />
					</td>
					<td class="delete"><a href="/EasyBuy/shopping/delete.html?epId=${carProduct.value.epId}">删除</a></td>
				</tr>
				 	<c:set var="rows" value="${rows+1 }"></c:set>
				    </c:forEach>
				      <input type="hidden" id="epids" name = "epids" value=""/>
				    <input type="hidden" id="numbers" name = "numbers" value=""/>
				    <tr><td colspan="4" align="right">共计<input type="text" id="money" value="" disabled="disabled">元</td><td><input type="button" onclick="buy()" value="去支付"/></td></tr>
				</c:if>
				<c:if test="${empty carProduct}">
				<tr><td align="center">购物车空空如也~赶紧去商城逛逛吧<a href="/EasyBuy/main/welcome.html">去首页</a></td></tr>
				</c:if>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function price(epId,row){
		var product = document.getElementById("product");
		var price = 0;
		for (var i =1;i<product.rows.length-1;i++){
		  var productPrice = document.getElementById(i+"price").innerText;
		  var number = document.getElementById(i+"number").value;
		  if(number.length==0){
		    number = 1;
		    document.getElementById(i+"number").value=1;
		  }
		  price += parseFloat(productPrice)*parseFloat(number);
		  }
	     var money =  document.getElementById("money");
	     if(money != null){
	      money.value = price;
	     }
	     if(epId!=null && epId.length!=0 && row !=null && row.length!=0){
	     var number = document.getElementById(row+"number").value;
	        save(epId,number);
	     }
		}
		
		function changeNumber(id,row,epId){
		   var option = document.getElementById(row+id);
		    var number = document.getElementById(row+"number");
		    var numberVal = number.value;
		  if(id == "+"){
		      number.value = parseInt(numberVal)+1;
		      price(epId,row);
		  }
		  if(id == "-"){
		   if(numberVal >1){
		   number.value = parseInt(numberVal)-1;
		   price(epId,row);
		   }
		  }
		}
		
		function numberForm(text,epId,row){
		if(text.value.length==1){
		text.value=text.value.replace(/[^1-9]/g,'');
		}else{
		text.value=text.value.replace(/\D/g,'');
		}
		 price(epId,row);
		}
		
		function save(epId,number){
		$.ajax({
			type : "post",
			url : "/EasyBuy/shopping/update.html",
			data : {
				epId : epId,
				number : number,
			},
			success : function(result) { 
				
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});						
		}
		
		function buy(){
		var product = document.getElementById("product");
		var numbers="";
		var eids="";
		for (var i =1;i<product.rows.length-1;i++){
		     var number = document.getElementById(i+"number").value;
		     numbers += number+";"
		     var eid = document.getElementById(i+"eid").value;
		     eids += eid+";"
		  }
		$.ajax({
			type : "post",
			url : "/EasyBuy/shopping/chek.html",
			data : {
				numbers : numbers,
				eids : eids,
			},
			success : function(result) { 
				if(result == "1"){
				  var numbersave = document.getElementById("numbers");
				  numbersave.value = numbers;
				  var eidsave = document.getElementById("epids");
				  eidsave.value =eids;
				  document.getElementById("buyForm").submit();
				}else if((result == "0")){
				 layer.alert("你还未添加手机号请前往<a href='/EasyBuy/login/user.jsp'>个人中心</a>添加")
				} else if(result == "2"){
				layer.alert("你还未添加送货地址请前往<a href='/EasyBuy/login/user.jsp'>个人中心</a>添加")
				}
				else{
				var res = result.split(";");
				layer.alert(res[0]+"库存不足,目前剩余"+res[1]+"件");
				}
			},
			error : function(result) {
				layer.alert("数据读取失败,请刷新重试！");
			}
		});						
		}
		price();
	</script>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
