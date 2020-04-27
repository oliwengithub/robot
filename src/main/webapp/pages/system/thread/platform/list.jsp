<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>线程配置</title>
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
                                    <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="add">添加交易所</button>
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
                        <%--<div class="layui-inline">
                            <div class="layui-input-inline layui-hide-xs layui-hide-sm layui-show-md-inline">
                                <input type="text" name="mobile" placeholder="联系电话" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline layui-hide-xs">
                                <select name="status">
                                    <option value="">选择用户状态</option>
                                    <option value="0">正常</option>
                                    <option value="1">禁用</option>
                                </select>
                            </div>
                        </div>--%>
                    </div>
                    <div class="layui-col-md1 layui-col-xs1 layui-right">
                        <button class="layui-btn" lay-submit="" lay-filter="search">查询</button>
                        <button class="layui-btn hidden" lay-submit="" lay-filter="searchSelf">刷新当前表格
                        </button>
                    </div>
                </div>
            </form>
            <table id="layui-table" lay-filter="layui-table"></table>
        </div>
    </div>
</div>
<script type="text/html" id="toolbar">
    <c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
        <button class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</button>
    </c:if>
    <c:if test="${fn:contains(currMenuChildrenTags, 'delete')}">
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
    </c:if>
</script>

<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<%@ include file="../../layuiconfig.jsp" %>
<script type="application/javascript">
    layui.use(['layer', 'jquery', 'form', 'table'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form,
            table = layui.table;

        table.render({
            id: 'layui-table'
            , elem: '#layui-table'
            , url: '${pageContext.request.contextPath}/system/thread/platform/list/query'
            , request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , height: 'full-100'
            , cellMinWidth: 80
            //, toolbar: "true" //导出/打印等工具条
            , cols: [[
                /* {type: 'checkbox'}*/
                {field: 'id', title: 'ID', width: 60, align: 'center', type: 'numbers'}
                , {field: 'tradeName', title: '交易所名称', width: 180, align: 'center'}
                , {field: 'tradeUrl', title: '交易请求的URL', minWidth: 200, align: 'center'}
                , {field: 'totalNumUrl', title: '获取当前单价盘面数量URL', minWidth: 200, align: 'center'}
                , {field: 'createDate', title: '创建时间', width: 180, align: 'center',templet: "<div>{{layui.util.toDateString(d.createDate, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
                , {title: '操作', width: 180, align: 'center', toolbar: '#toolbar'}
            ]]
            , page: true
        });

        //表格操作事件
        table.on('tool(layui-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    anim: 4,
                    title: '编辑交易所',
                    area: ['700px', '320px'],
                    content: '${pageContext.request.contextPath}/system/thread/platform/edit?id=' + data.id,
                    end: function () {
                        //触发刷新当页表格，子页面不需要parent.location.reload();
                        $("button[lay-filter=searchSelf]").click();
                    }
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
                title: '添加交易所',
                area: ['700px', '320px'],
                content: '${pageContext.request.contextPath}/system/thread/platform/add',
                end: function () {
                    //触发刷新当页表格，子页面不需要parent.location.reload();
                    $("button[lay-filter=searchSelf]").click();
                }
            });
            return false;
        });
    });
</script>
</body>
</html>