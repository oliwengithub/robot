<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加任务</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <style>
        #image-upload-one {
            height: 100px;
            width: 100px;
        }
        #image-upload-one .layui-upload-drag >i{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body" pad15>
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择分组</label>
                            <div class="layui-input-block">
                                <select name="groupId" lay-verify="required" lay-search="">
                                    <option value="">直接选择或搜索选择</option>
                                    <c:if test="${not empty quartzGroups}">
                                        <c:forEach items="${quartzGroups}" var="quartzGroup">
                                            <option value="${quartzGroup.id}">${quartzGroup.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">任务名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" autocomplete="off" placeholder="任务名称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">全限定类名</label>
                            <div class="layui-input-block">
                                <input type="number" name="className" placeholder="全限定类名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">方法名称</label>
                            <div class="layui-input-block">
                                <input type="number" name="methodName" placeholder="methodName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">cron时间表达式</label>
                            <div class="layui-input-block">
                                <input type="number" name="cronExpression" placeholder="cron时间表达式" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">任务描述</label>
                            <div class="layui-input-block">
                                <input type="number" name="description" placeholder="任务描述" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="save">确认提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
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
    layui.use(['form', 'layer'], function(){
        var form = layui.form
            ,layer = layui.layer

        //自定义验证
        form.verify({

        });
        form.on('submit(save)', function(obj){
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/quartz/insert',
                data: obj.field,
                success: function (result) {
                    if(result.code === 1){
                        layer.msg(result.msg,{time: 1000});
                    }else{
                        layer.msg('添加成功',{time: 1000},function () {
                            parent.layer.closeAll();
                        });
                    }
                }
            });
            return false;
        });
    });

</script>
</body>
</html>