<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>线程详情</title>
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
        .layui-input {
            border: 0;
            border-style: none;
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
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">交易所名称</label>
                                <div class="layui-input-block">
                                    <input class="layui-input"  type="text" readonly="true" value="${tradeThreadDetail.trade_name}">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">交易对名称</label>
                                <div class="layui-input-block">
                                    <input class="layui-input" type="text" readonly="true" value="${tradeThreadDetail.symbol}">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">交易用户ID</label>
                                <div class="layui-input-block">
                                    <input type="text" readonly="true" value="${tradeThreadDetail.client_id}" autocomplete="off" placeholder="交易用户ID" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">最小限额</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.trade_min}" placeholder="默认单笔挂单最小限额(CNY)" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">最大限额</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.trade_max}" placeholder="默认单笔挂单最大限额(CNY)" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">波动范围</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.wave_ratio}" placeholder="单价波动范围(在原始价格上波动一般为万分之一左右)" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">单价小数位</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.price_digit}" placeholder="单价小数位" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">数量小数位</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.num_digit}" maxlength="30" placeholder="数量小数位" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">调度周期</label>
                                <div class="layui-input-block">
                                    <input type="number" readonly="true" value="${tradeThreadDetail.task_cycle}" maxlength="30" placeholder="交易周期单位(ms)" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">线程状态</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="status" value="0" title="未启动" <c:if test="${tradeThreadDetail.status == 0}">checked</c:if> >
                                    <input type="radio" name="status" value="1" title="运行中" <c:if test="${tradeThreadDetail.status == 1}">checked</c:if>>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">开启时间</label>
                                <div class="layui-input-block">
                                    <input type="text" maxlength="30" readonly="true" value="${tradeThreadDetail.start_time}" placeholder="最后一次开启时间" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">关闭时间</label>
                                <div class="layui-input-block">
                                    <input type="text" name="" readonly="true" value="${tradeThreadDetail.stop_time}" maxlength="30" placeholder="最后一次关闭时间" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">创建时间</label>
                                <div class="layui-input-block">
                                    <input type="text" readonly="true" value="${tradeThreadDetail.create_time}" maxlength="30" placeholder="创建时间" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                        <%--<div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="save">确认提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                            </div>
                        </div>--%>
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
                url: '${pageContext.request.contextPath}/system/thread/update',
                data: obj.field,
                success: function (result) {
                    if(result.code === 1){
                        layer.msg(result.msg,{time: 1000});
                    }else{
                        layer.msg('修改成功',{time: 1000},function () {
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