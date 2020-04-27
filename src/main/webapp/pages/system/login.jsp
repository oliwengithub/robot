<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<title>${sessionScope.sessionSystemConf.name}-登录</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<script src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
	<style>
		*{
			margin: 0px;
			padding: 0px;
		}
		html,body{
			height: 100%;
			width: 100%;
		}
		body{
			background: url(${pageContext.request.contextPath}/images/login_bg.png) no-repeat center;
			background-size: cover;
			overflow: hidden;
			background-color: #039588;
			height: 100vh;
			padding-top: calc(50vh - 230px);
			box-sizing: border-box;
		}
		main{
			padding: 30px 20px 20px;
			width: 380px;
			height: 440px;
			background: #fff;
			margin: 0 auto;
			box-sizing: border-box;
			border-radius: 4px;
			max-width: 85%;
		}
		main h1{
			margin: 10px 0 0 -38px;
			padding: 18px 10px 18px 60px;
			background: #039588;
			position: relative;
			color: #fff;
			font-size: 16px;
			font-weight: normal;
		}
		main .aiwrap {
			background: url(${pageContext.request.contextPath}/images/aiwrap.png);
			width: 18px;
			height: 10px;
			margin: 0 0 15px -38px;
			position: relative;
		}
		.form-select{
			margin: 10px 15px 20px;
			font-size: 14px;
		}
		.form-select li{
			list-style: none;
			display: inline-block;
			padding: 8px 0;
			margin-right: 5px;
		}
		.form-select li.act{
			border-bottom: 3px solid #039588;
		}
		main .form-item{
			margin-bottom: 15px;
			clear: both;
			zoom: 1;
			margin: 0 15px 15px;
			font-size: 0px;
		}
		main .form-item img.icon{
			width: 20px;
			height: 20px;
			display: inline-block;
			color: #fff;
			margin: 10px;
		}
		main .form-item input{
			border: none;
			border-bottom: 1px solid #DCDEE0;
			vertical-align: top;
			border-radius: 2px;
			height: 40px;
			padding: 0px 16px;
			font-size: 14px;
			color: #555555;
			outline: none;
			width: calc(100% - 40px);
			box-sizing: border-box;
		}
		main .form-item input.code{
			width: calc(100% - 140px);
		}
		main .form-item img.imgcode{
			height: 40px;
			width: 100px;
			vertical-align: top;
		}
		main .form-item button{
			background-color: #039588;
			color: #fff;
			font-size: 14px;
			opacity: .9;
			width: 100%;
			line-height: 50px;
			border: none;
			border-radius: 2px;
			margin-top: 10px;
			outline: none;
		}

		main .form-footer{
			text-align: right;
			height: 30px;
			line-height: 30px;
		}
		main .form-footer dd{
			display: inline-block;
			font-size: 13px;
			vertical-align: top;
		}
		main .form-footer dt{
			display: inline-block;
			background: #039588;
			width: 3px;
			height: 16px;
			margin: 7px;
		}

		footer{
			text-align: center;
			font-size: 13px;
			color: #fff;
			position: absolute;
			bottom: 0px;
			width: 80%;
			margin: 5px 10%;
		}
		footer a{
			text-decoration: none;
			color: #fff;
		}
	</style>
</head>
<body>
<main>
	<h1>${sessionScope.sessionSystemConf.name}</h1>
	<div class="aiwrap"></div>
	<ul class="form-select">
		<li class="act">密码登录</li>
		<%--<li>手机号登录</li>--%>
	</ul>
	<form>
		<div class="form-item">
			<img class="icon" src="${pageContext.request.contextPath}/images/user.png" alt="">
			<input type="text" name="userName" class="userName" placeholder="用户名/手机号码" autocomplete="off"/>
		</div>
		<div class="form-item">
			<img class="icon" src="${pageContext.request.contextPath}/images/password.png" alt="">
			<input type="password" name="password" class="password" placeholder="用户密码" autocomplete="off"/>
		</div>
		<div class="form-item">
			<img class="icon" src="${pageContext.request.contextPath}/images/code.png" alt="">
			<input type="text" name="code" class="code" placeholder="验证码" autocomplete="off" maxlength="4"/>
			<img class="imgcode" onclick="getcode();" src="${pageContext.request.contextPath}/system/login/code">
		</div>
		<div class="form-item">
			<button type="button" onclick="login();">登录</button>
		</div>
	</form>
</main>
<footer>${sessionScope.sessionSystemConf.copyright}</footer>
</body>
<script src="${pageContext.request.contextPath}/plug/layer/layer.js"></script>
<script>

	//判断是否登录过期
	if (self.frameElement && self.frameElement.tagName == "IFRAME") {
		layer.alert('登录过期,请重新登录', {
			title: '登录过期提示' //样式类名
			,icon: 0
			,closeBtn: 0
		}, function(){
			parent.location.href = "${pageContext.request.contextPath}/system/login";
		});
	}else{
		$('.userName').focus();
	}

	$(".form-select li").click(function(){
		$(".form-select li").removeClass("act");
		$(this).addClass("act");
	});
	document.onkeydown = function (event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) {
			login();
		}
	};
	function login() {
		if($(".userName").val() == ""){
			layer.tips('用户名不能为空', '.userName');
			$('.userName').focus()
			return;
		}
		if($(".password").val() == ""){
			layer.tips('密码不能为空', '.password');
			$('.password').focus()
			return;
		}
		if($(".code").val() == ""){
			layer.tips('验证码不能为空', '.code');
			$('.code').focus()
			return;
		}
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/system/login/check',
			data: {
				'code': $(".code").val(),
				'userName': $(".userName").val(),
				'password': $(".password").val()
			},
			success: function (result) {
				if(result.code === 1){
					getcode();
					$(".code").val("");
					layer.msg(result.msg,{time: 500});
				}else{
					layer.msg('登录成功',{time: 500}, function(){
						window.location.href = "${pageContext.request.contextPath}/system/index";
					});
				}

			}

		});
	}
	function getcode() {
		var timestamp = (new Date()).getTime();
		$("img.imgcode").attr("src","${pageContext.request.contextPath}/system/login/code?time="+timestamp)
	}
</script>
</html>
