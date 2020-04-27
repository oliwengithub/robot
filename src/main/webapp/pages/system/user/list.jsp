<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
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
        .dtree-zdy-item-this {
            background-color: #f2f2f2 !important;
            color: #009688;
        }

        /* 当前选中行样式*/
        .dtree-zdy-item-this .dtree-zdy-ficon {
            color: #333 !important;
        }

        /* 当前选中行图标样式*/
        .dtree-zdy-item:hover {
            background-color: #f2f2f2 !important;
        }

        /* 行悬停样式*/
        .dtree-zdy-item cite {
            font-size: 14px !important;
        }

        /* 行文字样式*/
        .dtree-zdy-item:hover cite {
            color: #5FB878 !important;
        }

        /* 行悬停文字样式*/
        .dtree-zdy-dtreefont {
            font-size: 16px !important;
        }

        /* 一级图标、二级图标、复选框样式*/
        .dtree-zdy-ficon {
            color: #000 !important;
        }

        /*一级图标特定样式*/
        .dtree-zdy-icon {
            color: #000 !important;
        }

        /*二级图标特定样式*/
        .dtree-zdy-checkbox:hover {
            color: #5FB878 !important;
        }

        /*复选框悬停样式*/
        .dtree-zdy-choose {
            color: #5FB878 !important;
        }

        /* 复选框选中样式*/

        .dtree-toolbar-fixed a i {
            color: #333;
        }

        /*添加样式*/

        #roleTree {
            margin-bottom: 20px;
            width: calc(100% - 15px);
        }

        #searchInput {
            width: calc(100% - 15px);
        }

        .layui-card-body > .layui-row {
            margin-top: 12px;
        }

        .layui-elem-field {
            display: block;
            margin-inline-start: 2px;
            margin-inline-end: 2px;
        }

        .layui-elem-field legend {
            margin-left: 20px;
            padding: 0 10px;
            font-size: 20px;
            font-weight: 300;
            margin-top: -13px;
            background: #fff;
            display: inline-block;
            width: 80px;
            text-align: center;

        }

    </style>
</head>
<body>
<!-- 内容 内边距-->
<div class="layui-fluid">
    <!-- 白色背景-->
    <div class="layui-card">
        <!-- 内边距-->
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-md3 ">
                    <!-- 侧边菜单 -->
                    <div class="layui-elem-field">
                        <legend>组织架构</legend>
                        <div class="layui-field-box">
                            <div class="layui-row">
                                <div class="layui-col-md9">
                                    <input class="layui-input" id="searchInput" value="" placeholder="请输入查询组织名称">
                                </div>
                                <div class="layui-col-md3 layui-right">
                                    <button class="layui-btn layui-btn-primary " id="search_btn">查询</button>
                                </div>
                            </div>
                        </div>
                        <ul id="roleTree" class="dtree" data-id="-1"></ul>
                    </div>
                </div>
                <div class="layui-col-md9">
                    <div class="layui-elem-field">
                        <legend>用户列表</legend>
                        <div class="layui-field-box">
                            <form class="layui-form table-form">
                                <div class="layui-form-item">
                                    <div class="layui-col-md2">
                                        <div class="layui-inline">
                                            <div class="layui-input-inline layui-button-inline">
                                                <c:if test="${fn:contains(currMenuChildrenTags, 'add')}">
                                                    <button class="layui-btn layui-btn-primary" lay-submit=""
                                                            lay-filter="add">添加用户
                                                    </button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md9 layui-right">
                                        <div class="layui-inline">
                                            <div class="layui-input-inline">
                                                <input type="hidden" name="roleId" value="">
                                                <input type="text" name="userName" placeholder="用户名/真实姓名"
                                                       autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
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
                                        </div>
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
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 1){ }}
    <button class="layui-btn layui-btn-primary layui-btn-xs">男</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-warm layui-btn-xs">女</button>
    {{#  } }}
</script>
<script type="text/html" id="avatarTpl">
    {{#  if(d.avatar != null && d.avatar != ''){ }}
    <img src="${sessionScope.filePreviewUrl}{{ d.avatar }}" alt="" onerror="imgError(this);">
    {{#  } else { }}
    <img src="${pageContext.request.contextPath}/images/avatar.jpg">
    {{#  } }}
</script>
<script type="text/html" id="statusTpl">
    <input type="checkbox" lay-skin="switch" value="{{d.userId}}" lay-text="正常|禁用" <c:if
            test="${!fn:contains(currMenuChildrenTags, 'edit')}"> disabled</c:if> lay-filter="changeStatus" {{
           d.status== 0 ? 'checked' : '' }}>

</script>
<script type="text/html" id="toolbar">
    <c:if test="${fn:contains(currMenuChildrenTags, 'auth')}">
        <button class="layui-btn layui-btn-warm layui-btn-xs" lay-event="auth">设置权限</button>
    </c:if>
    <c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
        <button class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</button>
    </c:if>
    <c:if test="${fn:contains(currMenuChildrenTags, 'delete')}">
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
    </c:if>
</script>

<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<%@ include file="../layuiconfig.jsp" %>
<script type="application/javascript">
    layui.config({
        base: '${pageContext.request.contextPath}/' //静态资源所在路径根目录
    }).extend({
        dtree: "plug/layui_ext/dtree/dtree"
    });
    layui.use(['layer', 'jquery', 'form', 'code', 'table', 'dtree', 'util'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            dtree = layui.dtree,
            util = layui.util,
            form = layui.form,
            table = layui.table,
            nodeId = '';

        // 初始化树
        var RoleTree = dtree.render({
            elem: "#roleTree",
            //data: data, // 使用data加载
            url: '${pageContext.request.contextPath}/system/role/get/tree',
            dataStyle: "layuiStyle",//使用layui风格的数据格式
            dataFormat: "list",//配置data的风格为list
            response: {message: "msg", statusCode: 0},  //修改response中返回数据的定义
            initLevel: 1,
            skin: "zdy",
            iconfont: ["layui-icon", "iconfont"],
            iconfontStyle: [{  // 第一个JSON
                fnode: {
                    node: {  //非叶子节点
                        open: "dtree-icon-xiangxia1",  //节点展开
                        close: "dtree-icon-xiangyou"  //节点关闭
                    },
                    leaf: "dtree-icon-yonghu"  //叶子节点
                },
                snode: {  //二级节点
                    node: {  //非叶子节点
                        open: "dtree-icon-yonghu",  //节点展开
                        close: "dtree-icon-yonghu"  //节点关闭
                    },
                    leaf: ""  //叶子节点
                },
            }],
            //nodeIconArray:{"3":{"open":"dtree-icon-xiangxia1","close":"dtree-icon-xiangyou"}},  // 自定扩展的二级非最后一级图标，从1开始
            //ficon: ["3","1"],
            //icon: "-1",//隐藏二级图标
            toolbar: false,
            checkbar: true,
            initLevel: 2,//默认展开层级
            done: function (data, obj) {
                $("#search_btn").unbind("click");
                $("#search_btn").click(function () {
                    var value = $("#searchInput").val();
                    if (value) {
                        //RoleTree.searchNode(value); // 内置方法查找节点
                        if (!RoleTree.searchNode(value)) {
                            layer.msg("该名称节点不存在！", {time: 1000});
                        }
                    } else {
                        RoleTree.menubarMethod().refreshTree(); // 内置方法刷新树
                    }
                });
                $("#roleTree").find("div[dtree-click='itemNodeClick'][dtree-id='roleTree'][data-id='" + 0 + "']").click();
            },
        });

        // 绑定节点点击
        dtree.on("node('roleTree')", function (obj) {

            nodeId = obj.param.nodeId;
            //数组
            var nodeIds = new Array()
            $.each(obj.childParams, function (key, val) {
                nodeIds.push(val.nodeId)
            })
            nodeIds.push(obj.param.nodeId)
            $("input[name='roleId']").val(nodeIds);
            console.log($("input[name='roleId']").val());
            $("button[lay-filter=search]").click();
        });

        table.render({
            id: 'layui-table'
            , elem: '#layui-table'
            , url: '${pageContext.request.contextPath}/system/user/list/query'
            , request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , height: 'full-100'
            , cellMinWidth: 80
            , toolbar: "true" //导出/打印等工具条
            , cols: [[
                {type: 'checkbox'}
                , {field: 'userId', title: 'ID', width: 60, align: 'center', type: 'numbers'}
                , {field: 'userName', title: '用户名', width: 120, align: 'center'}
                , {field: 'avatar', title: '头像', width: 100, align: 'center', templet: '#avatarTpl'}
                , {field: 'nickName', title: '昵称', width: 120, align: 'center'}
                , {field: 'sex', title: '性别', width: 60, align: 'center', templet: '#sexTpl'}
                , {field: 'mobile', title: '联系电话', width: 150, align: 'center'}
                , {
                    field: 'email', title: '邮箱', minWidth: 160, templet: function (res) {
                        return '<em>' + (res.email == null ? "" : res.email) + '</em>'
                    }
                }
                , {field: 'status', title: '状态', width: 120, align: 'center', templet: '#statusTpl'}
                , {title: '操作', width: 200, align: 'center', toolbar: '#toolbar'}
            ]]
            , page: true
        });
        //正常/禁用事件
        form.on('switch(changeStatus)', function (obj) {
            updateUserStatus(this.value, (obj.elem.checked ? 0 : 1));
        });

        //表格操作事件
        table.on('tool(layui-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除此用户么', function (index) {
                    updateUserStatus(data.userId, 2);
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2
                    , anim: 4
                    , title: '用户编辑'
                    , area: ['80%', '80%']
                    , content: '${pageContext.request.contextPath}/system/user/edit?userId=' + data.userId
                    , end: function () {
                        //触发刷新当页表格，子页面不需要parent.location.reload();
                        $("button[lay-filter=searchSelf]").click();
                    }
                });
            } else if (obj.event === 'auth') {
                layer.open({
                    type: 2
                    ,
                    anim: 4
                    ,
                    title: '设置用户组织'
                    ,
                    area: ['40%', '60%']
                    ,
                    content: '${pageContext.request.contextPath}/system/auth?userId=' + data.userId + '&userName=' + data.userName
                });
            }
        });

        //查询提交事件
        form.on('submit(search)', function (data) {
            //表格重载
            table.reload('layui-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: data.field
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
                type: 2
                , anim: 4
                , title: '添加用户'
                , area: ['80%', '80%']
                , content: '${pageContext.request.contextPath}/system/user/add?roleId=' + nodeId
                , end: function () {
                    //触发刷新当页表格，子页面不需要parent.location.reload();
                    $("button[lay-filter=searchSelf]").click();
                }
            });
            return false;
        });

        function updateUserStatus(userId, status) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/user/update/status',
                data: {
                    "userId": userId,
                    "status": status
                },
                success: function (result) {
                    if (result.code === 1) {
                        layer.msg(result.msg, {time: 1000});
                    } else {
                        layer.msg('成功', {time: 1000});
                    }
                }
            });
        }
    });


</script>
</body>
</html>