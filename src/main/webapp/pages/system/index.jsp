<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${sessionScope.sessionSystemConf.name}-主页</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" media="all">
	<style>
		.layui-avatar{
			margin: 65px 0 -30px 0;
			width: 220px;
		}
		.layui-avatar img {
			display: block;
			margin: 10px auto;
			border-radius: 100%;
			width: 60px;
			height: 60px;
		}
		.layui-avatar span{
			display: block;
			text-align: center;
			font-size: 13px;
		}
		.layadmin-side-shrink .layui-side-menu .layui-avatar{
			width: 60px;
		}
		.layadmin-side-shrink .layui-side-menu .layui-avatar img{
			width: 50px;
			height: 50px;
		}
		.layadmin-side-shrink .layui-side-menu .layui-avatar span{
			display: none;
		}
		/**二三级菜单图标样式*/
		/*.layui-side-menu .layui-nav .layui-nav-child a {
			margin-left: 20px;
		}
		.layui-side-menu .layui-nav .layui-nav-child .layui-nav-child a {
			padding-left: 45px;
			margin-left: 40px;
		}*/
		.layui-side-menu .layui-nav .layui-nav-child .layui-icon{
			display: none;
		}
		
		
		.layui-side-menu .layui-nav .layui-nav-item a{
			padding-top: 4px;
			padding-bottom: 4px;
		}
		.layui-nav * {
			font-size: 13px;
		}
		.layui-side-menu .layui-nav .layui-nav-item .layui-icon{
			font-size: 14px;
		}
		
		.layui-side-menu .layui-nav .layui-nav-item .layui-icon.layui-icon-speaker{
			font-size: 18px;
		}
	</style>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item layadmin-flexible" lay-unselect>
					<a layadmin-event="flexible" title="伸缩菜单">
						<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="${sessionScope.sessionSystemConf.gateway}" target="_blank" title="门户">
						<i class="layui-icon layui-icon-website"></i>
					</a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a layadmin-event="refresh" title="刷新当前页面">
						<i class="layui-icon layui-icon-refresh-3"></i>
					</a>
				</li>
				<%--<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search.html?keywords=">
				</li>--%>
			</ul>
			<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
				<%--<li class="layui-nav-item" lay-unselect>
					<a layadmin-event="message" lay-text="消息中心" lay-href="pages/message">
						<i class="layui-icon layui-icon-notice"></i>
						<!-- 如果有新消息，则显示小圆点 -->
						<span class="layui-badge-dot"></span>
					</a>
				</li>--%>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a layadmin-event="theme" title="主题颜色">
						<i class="layui-icon layui-icon-theme"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a layadmin-event="note" title="便签">
						<i class="layui-icon layui-icon-note"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a layadmin-event="lock" title="锁屏">
						<i class="layui-icon layui-icon-password"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a layadmin-event="fullscreen">
						<i class="layui-icon layui-icon-screen-full"></i>
					</a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a>
						<cite>${sessionScope.sessionAdminUser.nickName}</cite>
					</a>
					<dl class="layui-nav-child">
						<dd><a lay-href="${pageContext.request.contextPath}/system/user/my/info">基本资料</a></dd>
						<dd><a lay-href="${pageContext.request.contextPath}/system/user/my/password">修改密码</a></dd>
						<hr>
						<dd layadmin-event="logout" style="text-align: center;"><a>退出</a></dd>
					</dl>
				</li>
				
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
				</li>
				<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
					<a layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
				</li>
			</ul>
		</div>
		
		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
			<div class="layui-side-scroll">
				<div class="layui-logo">
					<span>${sessionScope.sessionSystemConf.name}</span>
				</div>
				<div class="layui-avatar">
					<c:if test="${empty sessionAdminUser.avatar}">
						<img src="${sessionScope.filePreviewUrl}avatar.jpg">
					</c:if>
					<c:if test="${not empty sessionAdminUser.avatar}">
						<img src="${sessionScope.filePreviewUrl}${sessionAdminUser.avatar}" onerror="avatarError(this);">
					</c:if>
					<span>你好! ${sessionScope.sessionAdminUser.nickName}</span>
				</div>
				<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
					<!-- 默认显示主页，不参与权限控制-->
					<li data-name="home" class="layui-nav-item layui-nav-itemed layui-this">
						<a lay-href="${pageContext.request.contextPath}/system/stat" lay-direction="2">
							<i class="layui-icon layui-icon-console"></i>
							<cite>工作台</cite>
						</a>
					</li>
					<c:if test="${not empty menus}">
						<c:forEach items="${menus}" var="menu">
							<li data-name="${menu.name}" class="layui-nav-item">
								<c:if test="${not empty menu.children}">
									<a lay-tips="${menu.name}">
										<i class="layui-icon ${menu.icon}"></i>
										<cite>${menu.name}</cite>
									</a>
									<dl class="layui-nav-child">
										<c:forEach items="${menu.children}" var="children">
											<dd data-name="${children.name}">
												<c:if test="${not empty children.children}">
													<a>
														<i class="layui-icon ${children.icon}"></i>
														<cite>${children.name}</cite>
													</a>
													<dl class="layui-nav-child">
														<c:forEach items="${children.children}" var="c">
															<dd data-name="${c.name}">
																<a lay-href="${pageContext.request.contextPath}${c.url}">
																	<i class="layui-icon ${c.icon}"></i>
																	<cite>${c.name}</cite>
																</a>
															</dd>
														</c:forEach>
													</dl>
												</c:if>
												<c:if test="${empty children.children}">
													<a lay-href="${pageContext.request.contextPath}${children.url}">
														<i class="layui-icon ${children.icon}"></i>
														<cite>${children.name}</cite>
													</a>
												</c:if>
											</dd>
										</c:forEach>
									</dl>
								</c:if>
								<c:if test="${empty menu.children}">
									<a lay-href="${pageContext.request.contextPath}${menu.url}" lay-tips="${menu.name}" lay-direction="2">
										<i class="layui-icon ${menu.icon}"></i>
										<cite>${menu.name}</cite>
									</a>
								</c:if>
							</li>
						</c:forEach>
					</c:if>
					<!-- 默认菜单，不参与权限控制-->
					<li data-name="my" class="layui-nav-item">
						<a lay-tips="账号管理" lay-direction="2">
							<i class="layui-icon layui-icon-user"></i>
							<cite>个人管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="基本资料">
								<a lay-href="${pageContext.request.contextPath}/system/user/my/info">基本资料</a>
							</dd>
							<dd data-name="修改密码">
								<a lay-href="${pageContext.request.contextPath}/system/user/my/password">修改密码</a>
							
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		
		<!-- 页面标签 -->
		<div class="layadmin-pagetabs" id="LAY_app_tabs">
			<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-down">
				<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
					<li class="layui-nav-item" lay-unselect>
						<a></a>
						<dl class="layui-nav-child layui-anim-fadein">
							<dd layadmin-event="refresh"><a>刷新当前页面</a></dd>
							<dd layadmin-event="closeThisTabs"><a>关闭当前标签页</a></dd>
							<dd layadmin-event="closeOtherTabs"><a>关闭其它标签页</a></dd>
							<dd layadmin-event="closeAllTabs"><a>关闭全部标签页</a></dd>
						</dl>
					</li>
				</ul>
			</div>
			<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
				<ul class="layui-tab-title" id="LAY_app_tabsheader">
					<!-- 默认页面-->
					<li lay-id="${pageContext.request.contextPath}/system/stat" lay-attr="${pageContext.request.contextPath}/system/stat" class="layui-this">
						<i class="layui-icon layui-icon-console"></i>
					</li>
				</ul>
			</div>
		</div>
		
		<!-- 主体内容 -->
		<div class="layui-body" id="LAY_app_body">
			<div class="layadmin-tabsbody-item layui-show">
				<!-- 默认页面-->
				<iframe src="${pageContext.request.contextPath}/system/stat" frameborder="0" class="layadmin-iframe"></iframe>
			</div>
		</div>
		
		<!-- 辅助元素，一般用于移动设备下遮罩 -->
		<div class="layadmin-body-shade" layadmin-event="shade"></div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/' //静态资源所在路径根目录
    }).extend({
        index: 'js/index' //主入口模块
    }).use('index');

    function avatarError(image){
        image.src = "${sessionScope.filePreviewUrl}/avatar.jpg";
    }
</script>
</body>
</html>
