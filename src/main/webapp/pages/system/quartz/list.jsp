<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>交易线程</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui_ext/dtree/dtree.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui_ext/dtree/font/dtreefont.css"
          media="all">
    <style>

    </style>
</head>
<body>
<!-- 内容 内边距-->
<div class="layui-fluid">
    <!-- 白色背景-->
    <div class="layui-card">
        <!-- 内边距-->
        <div class="layui-card-body">
            <form class="layui-form table-form">
                <div class="layui-form-item">
                    <div class="layui-col-md2">
                        <div class="layui-inline">
                            <div class="layui-input-inline layui-button-inline">
                                <c:if test="${fn:contains(currMenuChildrenTags, 'add')}">
                                    <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="add">添加任务</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md9 layui-right">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="text" name="tradeName" placeholder="交易所名称"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline layui-hide-xs layui-hide-sm layui-show-md-inline">
                                <input type="text" name="symbol" placeholder="交易对名称" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline layui-hide-xs">
                                <select name="status">
                                    <option value="">选择线程状态</option>
                                    <option value="0">暂停中</option>
                                    <option value="1">运行中</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md1 layui-col-xs1 layui-right">
                        <button class="layui-btn" lay-submit="" lay-filter="search">查询</button>
                        <button class="layui-btn hidden" lay-submit="" lay-filter="searchSelf">刷新当前表格
                        </button>
                    </div>
                </div>
            </form>
            <%--一键操作按钮--%>
            <div class="layui-form-item">
                <div class="layui-btn-group barTable">
                    <div class="layui-inline"><button class="layui-btn" data-type="start">一键开启</button></div>
                    <div class="layui-inline"><button class="layui-btn" data-type="stop">一键关闭</button></div>
                </div>
            </div>
            <table id="layui-table" lay-filter="layui-table"></table>
        </div>
    </div>
</div>
<script type="text/html" id="toolbar">
    {{#  if(d.status == 0 ){ }}
    <c:if test="${fn:contains(currMenuChildrenTags, 'start')}">
        <button class="layui-btn layui-btn-green layui-btn-xs" lay-event="start" >开启</button>
    </c:if>
    {{#  } else { }}
    <c:if test="${fn:contains(currMenuChildrenTags, 'stop')}">
        <button class="layui-btn layui-btn-warm layui-btn-xs" lay-event="stop">关闭</button>
    </c:if>
    {{#  } }}
    <c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
        <button class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</button>
    </c:if>
    <c:if test="${fn:contains(currMenuChildrenTags, 'detail')}">
        <button class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">详情</button>
    </c:if>
    <c:if test="${fn:contains(currMenuChildrenTags, 'delete')}">
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
    </c:if>
</script>

<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<%@ include file="../layuiconfig.jsp" %>
<script type="application/javascript">
    layui.use(['layer', 'jquery', 'form', 'table'], function () {
        let $ = layui.jquery,
            layer = layui.layer,
            form = layui.form,
            table = layui.table,
            active = {
                start: function(){ //获取选中数据
                    let checkStatus = table.checkStatus('layui-table')
                        ,data = checkStatus.data,
                        threadArray = new Array();
                    data.forEach(function (value, index, array) {
                        let obj = new Object();
                        obj["id"] = value.id;
                        obj["status"] = value.status,
                        obj["symbol"] = value.symbol;
                        obj["tradeName"] = value.trade_name;
                        obj["taskCycle"] = value.task_cycle;
                        threadArray.push(obj);
                    });
                    if (threadArray != null && threadArray.length > 0){
                        layer.confirm('确认启动选中所有交易线程么', function (index) {
                            oneKeyStart(threadArray);
                            $("button[lay-filter=searchSelf]").click();
                        });


                    }else {
                        layer.msg('没有选中操作项', {time: 1000});
                    }
                }
                ,stop: function() {
                    let checkStatus = table.checkStatus('layui-table')
                        , data = checkStatus.data,
                        threadArray = new Array();
                    data.forEach(function (value, index, array) {
                        let obj = new Object();
                        obj["id"] = value.id;
                        obj["status"] = value.status,
                            obj["symbol"] = value.symbol;
                        obj["tradeName"] = value.trade_name;
                        obj["taskCycle"] = value.task_cycle;
                        threadArray.push(obj);
                    });
                    if (threadArray != null && threadArray.length > 0) {
                        layer.confirm('确认关闭选中所有交易线程么', function (index) {
                            oneKeyStop(threadArray);
                            $("button[lay-filter=searchSelf]").click();
                        });
                    } else {
                        layer.msg('没有选中操作项', {time: 1000});
                    }
                }
            };
        $('.barTable .layui-btn').on('click', function(){
            let type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //监听表格复选框选择
        /*table.on('checkbox(layui-table)', function(obj){
            console.log(obj)
        });*/

        $('.barTable .layui-btn').hover(function() {
            openMsg(this);
        }, function() {
            layer.close(subTips);
        });
        function openMsg(e) {
            subTips = layer.tips('该操作将重置所有选中任务，不建议同时操作两种状态的任务', e,{tips:[3,'#FFB800'],time: 30000});
        }


        table.render({
            id: 'layui-table'
            , elem: '#layui-table'
            , url: '${pageContext.request.contextPath}/system/thread/list/query'
            , request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , height: 'full-100'
            , cellMinWidth: 80
            , toolbar: "true" //导出/打印等工具条
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: '序号', width: 60, align: 'center', type: 'numbers'}
                , {field: 'trade_name', title: '交易所名称', minWidth: 100, align: 'center'}
                , {field: 'client_id', title: '机器人ID', width: 90, align: 'center'}
                , {field: 'symbol', title: '交易对名称', width: 120, align: 'center'}
                , {field: 'trade_max', title: '默认最大限额(CNY)', width: 100, align: 'center'}
                , {field: 'trade_min', title: '默认最小限额(CNY)', width: 100, align: 'center'}
                , {field: 'wave_ratio', title: '单价波动', width: 100, align: 'center'}
                , {field: 'price_digit', title: '单价小数位', width: 100, align: 'center'}
                , {field: 'num_digit', title: '数量小数位', width: 100, align: 'center' }
                , {field: 'task_cycle', title: '周期(ms)', width: 100, align: 'center'}
                , {field: 'status', title: '状态', width: 100, align: 'center', templet: "<div>{{d.status == 0 ? '<div class=\"layui-bg-orange\">未启动</div>':'<div class=\"layui-bg-green\">运行中<div>'}}</div>"}
                , {field: 'start_time', title: '最近启动时间', width: 160, align: 'center',templet: "<div>{{d.start_time == null ? '' : layui.util.toDateString(d.start_time, 'yyyy-MM-dd HH:mm')}}</div>"}
                , {field: 'stop_time', title: '最近关闭时间', width: 160, align: 'center',templet: "<div>{{d.stop_time == null ? '' : layui.util.toDateString(d.stop_time, 'yyyy-MM-dd HH:mm')}}</div>"}
                , {title: '操作', width: 210, align: 'center', toolbar: '#toolbar'}
            ]]
            , page: true
        });

        //表格操作事件
        table.on('tool(layui-table)', function (obj) {
            let data = obj.data;
            if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    anim: 4,
                    title: '任务编辑',
                    area: ['700px', '500px'],
                    content: '${pageContext.request.contextPath}/system/thread/edit?id=' + data.id,
                    end: function () {
                        //触发刷新当页表格，子页面不需要parent.location.reload();
                        $("button[lay-filter=searchSelf]").click();
                    }
                });
            }else if (obj.event === 'detail') {
                layer.open({
                    type: 2,
                    anim: 4,
                    title: '任务编辑',
                    area: ['700px', '500px'],
                    content: '${pageContext.request.contextPath}/system/thread/detail?id=' + data.id,
                    end: function () {
                        //触发刷新当页表格，子页面不需要parent.location.reload();
                        $("button[lay-filter=searchSelf]").click();
                    }
                });
            } else if (obj.event === 'start') {
                layer.confirm('确认启动此交易线程么', function (index) {
                    $.ajax({
                        type: 'POST',
                        url: '${pageContext.request.contextPath}/system/thread/start',
                        data: {
                            "id": data.id,
                            "tradeName": data.trade_name,
                            "symbol": data.symbol,
                            "status": data.status,
                            "taskCycle": data.task_cycle,
                        },
                        success: function (result) {
                            if (result.code === 1) {
                                layer.msg(result.msg, {time: 1000});
                            } else {
                                layer.msg('启动成功', {time: 1000});
                                $("button[lay-filter=searchSelf]").click();
                            }
                        }
                    });
                    layer.close(index);
                });

            } else if (obj.event === 'stop') {
                layer.confirm('确认关闭此交易线程么', function (index) {
                    $.ajax({
                        type: 'POST',
                        url: '${pageContext.request.contextPath}/system/thread/stop',
                        data: {
                            "id": data.id,
                            "tradeName": data.trade_name,
                            "symbol": data.symbol,
                            "status": data.status,
                            "taskCycle": data.task_cycle,
                        },
                        success: function (result) {
                            if (result.code === 1) {
                                layer.msg(result.msg, {time: 1000});
                            } else {
                                layer.msg('关闭成功', {time: 1000});
                                $("button[lay-filter=searchSelf]").click();
                            }
                        }
                    });
                    layer.close(index);
                });

            }
        });

        //查询提交事件
        form.on('submit(search)', function (data) {
            //表格重载
            table.reload('layui-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: data.field
            });
            return false;
        });

        //刷新当页表格
        form.on('submit(searchSelf)', function (data) {
            //表格重载
            table.reload('layui-table', {
                where: data.field
            });
            return false;
        });

        form.on('submit(add)', function () {
            layer.open({
                type: 2,
                anim: 4,
                title: '添加任务',
                area: ['700px', '500px'],
                content: '${pageContext.request.contextPath}/system/thread/add',
                end: function () {
                    //触发刷新当页表格，子页面不需要parent.location.reload();
                    $("button[lay-filter=searchSelf]").click();
                }
            });
            return false;
        });

        function oneKeyStart(threadArray) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/thread/oneKeyStart',
                data: {
                    "threadArray": JSON.stringify(threadArray),
                },
                success: function (result) {
                    if (result.code === 1) {
                        layer.msg(result.msg, {time: 1000});
                    } else {
                        layer.msg('开启成功', {time: 1000});
                    }
                }
            });
            return false;
        }

        function oneKeyStop(threadArray) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/thread/oneKeyStop',
                data: {
                    "threadArray": JSON.stringify(threadArray),
                },
                success: function (result) {
                    if (result.code === 1) {
                        layer.msg(result.msg, {time: 1000});
                    } else {
                        layer.msg('关闭成功', {time: 1000});
                    }
                }
            });
            return false;
        }




    });
</script>
</body>
</html>