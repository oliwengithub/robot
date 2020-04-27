<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>网站设置</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
</head>
<body>

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">网站设置</div>
				<div class="layui-card-body" pad15>
					<form class="layui-form" lay-filter="">
						<div class="layui-col-md8 layui-col-xs12 layui-right" style="float: inherit;">
							<div class="layui-form-item">
								<label class="layui-form-label">系统名称</label>
								<div class="layui-input-block">
									<input type="text" name="name" value="${name}" class="layui-input" lay-verify="required">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">门户地址</label>
								<div class="layui-input-block">
									<input type="text" name="gateway" value="${gateway}" class="layui-input" lay-verify="url">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">授权主体</label>
								<div class="layui-input-block">
									<input type="text" name="customer" value="${customer}" class="layui-input" lay-verify="required">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">技术支持</label>
								<div class="layui-input-block">
									<input type="text" name="author" value="${author}" class="layui-input" lay-verify="required">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">支持电话</label>
								<div class="layui-input-block">
									<input type="text" name="mobile" value="${mobile}" class="layui-input" lay-verify="required">
								</div>
							</div>
							<div class="layui-form-item layui-form-text">
								<label class="layui-form-label">版权信息</label>
								<div class="layui-input-block">
									<textarea name="copyright" class="layui-textarea" lay-verify="required">${copyright}</textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
										<button class="layui-btn" lay-submit lay-filter="save">确认保存</button>
									</c:if>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<%@ include file="../layuiconfig.jsp"%>
<script type="application/javascript">
    layui.use(['form'], function(){
        var form = layui.form;

        form.on('submit(save)', function(obj){
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/config/website/update',
                data: {
                    'value': JSON.stringify(obj.field),
                },
                success: function (result) {
                    if(result.code === 1){
                        layer.msg(result.msg,{time: 500});
                    }else{
                        layer.msg('修改成功',{time: 500});
                    }
                }

            });
            return false;
        });

    });
</script>
</body>
</html>