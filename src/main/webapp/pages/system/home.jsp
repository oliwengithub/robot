<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
</head>
<body>
<!-- 内容 内边距-->
<div class="layui-fluid">
	<!-- 白色背景-->
	<div class="layui-card">
		<!-- 内边距-->
		<div class="layui-card-body">
			<!-- 内容-->
			您好！欢迎进入${sessionScope.sessionSystemConf.name}
		</div>
	</div>
</div>
</body>
</html>