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
                            <label class="layui-form-label">选择交易所</label>
                            <div class="layui-input-block">
                                <select name="tradePlatformId" lay-verify="required" lay-search="">
                                    <option value="">直接选择或搜索选择</option>
                                    <c:if test="${not empty tradePlatforms}">
                                        <c:forEach items="${tradePlatforms}" var="tradePlatform">
                                            <option value="${tradePlatform.id}">${tradePlatform.tradeName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择交易对</label>
                            <div class="layui-input-block">
                                <select name="tradeConfigId" lay-verify="required" lay-search="">
                                    <option value="">直接选择或搜索选择</option>
                                    <c:if test="${not empty tradeConfigs}">
                                        <c:forEach items="${tradeConfigs}" var="tradeConfig">
                                            <option value="${tradeConfig.id}">${tradeConfig.symbol}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">交易用户ID</label>
                            <div class="layui-input-block">
                                <input type="text" name="clientId" autocomplete="off" placeholder="交易用户ID" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">最小限额</label>
                            <div class="layui-input-block">
                                <input type="number" name="tradeMin" placeholder="默认单笔挂单最小限额(CNY)" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">最大限额</label>
                            <div class="layui-input-block">
                                <input type="number" name="tradeMax" placeholder="默认单笔挂单最大限额(CNY)" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">波动范围</label>
                            <div class="layui-input-block">
                                <input type="number" name="waveRatio" placeholder="单价波动范围(在原始价格上波动一般为万分之一左右)" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">单价小数位</label>
                            <div class="layui-input-block">
                                <input type="number" name="priceDigit" placeholder="单价小数位" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">数量小数位</label>
                            <div class="layui-input-block">
                                <input type="text" name="numDigit" maxlength="30" placeholder="数量小数位" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">调度周期</label>
                            <div class="layui-input-block">
                                <input type="text" name="taskCycle" maxlength="30" placeholder="交易周期单位(ms)" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <%--<div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">状态</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="status" value="0" title="正常" checked>
                                    <input type="radio" name="status" value="1" title="禁用">
                                </div>
                            </div>
                        </div>
                         <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">最后开始时间</label>
                                <div class="layui-input-block">
                                    <input type="text" name="start_date_time" maxlength="30" placeholder="请谨慎填写，添加成功后，发送账号信息到此邮箱" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                         <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">最后关闭时间</label>
                                <div class="layui-input-block">
                                    <input type="text" name="stop_data_time" maxlength="30" placeholder="请谨慎填写，添加成功后，发送账号信息到此邮箱" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label"></label>
                            <div class="layui-input-block">
                                <textarea name="remark" placeholder="请用简单的话描述下此用户..." class="layui-textarea" maxlength="500"></textarea>
                            </div>
                        </div>--%>
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
                url: '${pageContext.request.contextPath}/system/thread/insert',
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