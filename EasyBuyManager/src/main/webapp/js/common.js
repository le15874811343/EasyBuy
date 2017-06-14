function getLoginUser (){
	var user;
$.ajax({
			type : "post",
			url : "/EasyBuyManager/user/loginUser",
			async: false ,
			success : function(result) {
			   if(result != null){
			     user = result;
			   }
			},
			error : function(result) {
				layer.alert("数据读取错误");
			}
		});	
return user;
}

function loadCommon(){
	 var user = getLoginUser();
	 var date = new Date();
	 var month = date.getMonth()+1;
	 var time = date.getFullYear()+"-"+month+"-"+date.getDate()+"-"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	  $("#welcome").html("管理员"+user.euUserName+"您好,现在是"+time+"，欢迎回到管理后台。");
	  setTimeout("loadCommon();",1000);
}

function loadPage(result,type,targetPage){
	 var pageHtml = '共找到'+result.maxCount+'个'+type+',共'+result.maxPage+'页';
	   if(result.targetPage>1){
		   var page =  targetPage -1; 
	   pageHtml += '<li><a href="javascript:load('+page+');">上一页</a></li>';
	   }
	   for(var i = 1;i<=result.maxPage;i++){
	   if(i==result.targetPage){
	     pageHtml += '<li class="current">'+i+'</li>';
	   }
	   else{
	   	pageHtml += '<li ><a href="javascript:load('+i+');">'+i+'</a></li>';
	   }
	   }
	   if(result.targetPage<result.maxPage){
		 var page =  targetPage +1; 
	   pageHtml += '<li><a href="javascript:load('+page+');">下一页</a></li>';
	   }
	   $("#page").html(pageHtml);
}

function GetRequest() {   
	   var url = location.search;   
	   var theRequest = new Object();   
	   if (url.indexOf("?") != -1) {   
	      var str = url.substr(1);   
	      strs = str.split("&");   
	      for(var i = 0; i < strs.length; i ++) {   
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
	      }   
	   }   
	   return theRequest;   
	} 