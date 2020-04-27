<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui_ext/dtree/dtree.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui_ext/dtree/font/dtreefont.css" media="all">
    <style>

        .dtree-zdy-item-this{background-color: #f2f2f2!important;color: #009688;} /* 当前选中行样式*/
        .dtree-zdy-item-this .dtree-zdy-ficon{color:#333!important;}/* 当前选中行图标样式*/
        .dtree-zdy-item:hover{background-color: #f2f2f2!important;} /* 行悬停样式*/
        .dtree-zdy-item cite{font-size:14px!important;} /* 行文字样式*/
        .dtree-zdy-item:hover cite{color:#5FB878!important;} /* 行悬停文字样式*/
        .dtree-zdy-dtreefont{font-size: 16px!important;} /* 一级图标、二级图标、复选框样式*/
        .dtree-zdy-ficon{color:#000!important;}  /*一级图标特定样式*/
        .dtree-zdy-icon{color:#000!important;}  /*二级图标特定样式*/
        .dtree-zdy-checkbox:hover{color:#5FB878!important;}  /*复选框悬停样式*/
        .dtree-zdy-choose{color:#5FB878!important;} /* 复选框选中样式*/

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

        .layui-right>button {
            display: block;
            float: right;
        }
        .auth-single {
            display: flex;
            flex-wrap: wrap;
        }

        .auth-status {
            height: 30px;
        }

        .layui-card-body {
            min-height: 90vh;
        }

        .layui-card-body>.layui-row {
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
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-md3">
                    <div class="layui-elem-field">
                        <legend>系统角色</legend>
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
                    <form class="layui-form" lay-filter="" action="">
                        <div class="layui-elem-field">
                            <legend>菜单权限</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="checkAll">全选</button>
                                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="uncheckAll">全不选</button>
                                </div>
                                <div id="menus"></div>

                            </div>

                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <button class="layui-btn" lay-submit lay-filter="save">确认提交</button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
    <%@ include file="../layuiconfig.jsp"%>
    <script type="application/javascript">
        layui.config({
            base: '${pageContext.request.contextPath}/' //静态资源所在路径根目录
        }).extend({
            authtree: "js/authtree",
            dtree:"plug/layui_ext/dtree/dtree"
        });

        layui.use(['layer', 'authtree', 'jquery', 'form', 'code','dtree'], function(){
            var $ = layui.jquery,
                layer = layui.layer,
                authtree = layui.authtree,
                form = layui.form,
                dtree = layui.dtree,
                menuList = [];

            // 初始化树
            var RoleTree = dtree.render({
                elem: "#roleTree",
                //data: data, // 使用data加载
                url:'${pageContext.request.contextPath}/system/role/get/tree',
                dataStyle: "layuiStyle",//使用layui风格的数据格式
                dataFormat: "list",//配置data的风格为list
                response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
                initLevel: 1,
                skin: "zdy",
                iconfont: ["layui-icon", "iconfont"],
                iconfontStyle: [{  // 第一个JSON
                    fnode:{
                        node:{  //非叶子节点
                            open:"dtree-icon-xiangxia1",  //节点展开
                            close:"dtree-icon-xiangyou"  //节点关闭
                        },
                        leaf:"dtree-icon-yonghu"  //叶子节点
                    },
                    snode:{  //二级节点
                        node:{  //非叶子节点
                            open:"dtree-icon-yonghu",  //节点展开
                            close:"dtree-icon-yonghu"  //节点关闭
                        },
                        leaf:""  //叶子节点
                    },
                }],
                //nodeIconArray:{"3":{"open":"dtree-icon-xiangxia1","close":"dtree-icon-xiangyou"}},  // 自定扩展的二级非最后一级图标，从1开始
                //ficon: ["3","1"],
                //icon: "-1",//隐藏二级图标
                toolbar:true,
                toolbarWay:"follow",
                //menubar:true,
                checkbar: true,
                initLevel: 2,//默认展开层级
                toolbarStyle: {
                    title: "组织",
                    area: ["40%", "350px"]
                },
                toolbarBtn:[
                    [
                        {"label":"备注","name":"remark","type":"text"},
                    ], // 这就是自定义新增中的内容
                    [
                        {"label":"备注","name":"remark","type":"text"},
                    ], // 自定义编辑页的内容
                ],
                done: function(data, obj){
                    $("#search_btn").unbind("click");
                    $("#search_btn").click(function(){
                        var value = $("#searchInput").val();
                        if(value){
                            //RoleTree.searchNode(value); // 内置方法查找节点
                            if (!RoleTree.searchNode(value)) {layer.msg("该名称节点不存在！", {time: 1000});}
                        } else {
                            RoleTree.menubarMethod().refreshTree(); // 内置方法刷新树
                        }
                    });
                },
                toolbarFun:{
                    loadToolbarBefore: function(buttons, param, $div){
                        if(param.parentId == -1){ // 如果是根节点
                            buttons.editToolbar  = "";  // 取消编辑功能
                            buttons.delToolbar  = "";  // 取消删除功能
                        }
                        return buttons; // 将按钮对象返回
                    },
                    addTreeNode: function(treeNode, $div){
                        $.ajax({
                            type: 'GET',
                            data: {
                                pid: treeNode.parentId,
                                roleName: treeNode.context,
                                remark: treeNode.remark
                            },
                            url: '${pageContext.request.contextPath}/system/role/insert',
                            success: function(result){
                                if(result.code === 1){
                                    layer.msg(result.msg,{time: 1000});
                                }else{
                                    RoleTree.changeTreeNodeAdd("refresh"); // 添加成功，局部刷新树
                                    layer.msg('添加成功',{time: 1000},function () {
                                        parent.layer.closeAll();
                                    });
                                }
                            },
                            error: function(){
                                RoleTree.changeTreeNodeAdd(false); // 添加失败
                            }
                        });

                    },
                    editTreeLoad: function(treeNode){
                        // 这里可以发送ajax请求，来获取参数值，最后将参数值以form能识别的键值对json的形式返回
                        RoleTree.changeTreeNodeDone( {remark:treeNode.remark});
                        /* $.ajax({
                             success: function(result){
                                 var param = result.data;
                                 // 这里的param格式为：  {level:treeNode.level, test:"3"};
                                 DemoTree.changeTreeNodeDone(param); // 配套使用
                             }
                         })*/
                    },
                    editTreeNode: function(treeNode, $div){
                        $.ajax({
                            type: "GET",
                            data: {
                                roleId: treeNode.nodeId,
                                roleName: treeNode.context,
                                remark: treeNode.remark
                            },
                            url:'${pageContext.request.contextPath}/system/role/edit',
                            success: function(result){
                                if(result.code === 1){
                                    layer.msg(result.msg,{time: 1000});
                                }else{
                                    RoleTree.changeTreeNodeEdit(true);// 修改成功
                                    //DTree1.changeTreeNodeEdit(result.param); // 修改成功，返回一个JSON对象
                                    layer.msg('修改成功',{time: 1000},function () {
                                        parent.layer.closeAll();
                                    });
                                }
                            },
                            error: function(){
                                RoleTree.changeTreeNodeEdit(false);//修改失败
                            }
                        });
                    },
                    delTreeNode: function(treeNode, $div){
                        $.ajax({
                            type: "GET",
                            data: {
                                roleId: treeNode.nodeId,
                            },
                            url: '${pageContext.request.contextPath}/system/role/del',
                            success: function(result){
                                if(result.code === 1){
                                    layer.msg(result.msg,{time: 1000});
                                }else{
                                    RoleTree.changeTreeNodeDel(true);// 修改成功
                                    //DTree1.changeTreeNodeDel(result.param); // 修改成功，返回一个JSON对象
                                    layer.msg('删除成功',{time: 1000},function () {
                                        parent.layer.closeAll();
                                    });
                                }
                            },
                            error: function(){
                                RoleTree.changeTreeNodeDel(false);// 删除失败
                            }
                        });
                    }
                }
            });

            // 绑定节点点击
            dtree.on("node('roleTree')" ,function(obj){
                var roleId = obj.param.nodeId;
                if(roleId == 0) {
                    return;
                }
                $.ajax({
                    type:'GET',
                    data:{
                        roleId:obj.param.nodeId
                    },
                    url:'${pageContext.request.contextPath}/system/role/role/menu',
                    success:function (result) {
                        var menus = result.data.menuIds;
                        if(menus != '' && menus != null) {
                            menus = menus.split(",");
                        }
                        // 支持自定义递归字段、数组权限判断等
                        // 深坑注意：如果API返回的数据是字符串，那么 startPid 的数据类型也需要是字符串
                        var trees = authtree.listConvert(menuList, {
                            primaryKey: 'id'
                            ,startPid: '0'
                            ,parentKey: 'pid'
                            ,nameKey: 'name'
                            ,valueKey: 'id'
                            ,checkedKey: menus
                        });
                        // 如果页面中多个树共存，需要注意 layfilter 需要不一样
                        authtree.render('#menus', trees, {
                            inputname: 'authids[]',
                            layfilter: 'lay-check-convert-auth',
                            openall: true,
                            autowidth: true,
                            autoclose: false
                        });
                        form.on('submit(save)', function(obj){
                            var menuIds = "";
                            var ids = authtree.getChecked('#menus');
                            if(ids.length > 0){
                                for (var i = 0,len = ids.length; i < len; i++) {
                                    if(i == len - 1){
                                        menuIds += ids[i];
                                    }else{
                                        menuIds += ids[i] + ',';
                                    }
                                }
                            }
                            $.ajax({
                                type: 'POST',
                                url: '${pageContext.request.contextPath}/system/role/update',
                                data: {
                                    "roleId": roleId,
                                    "menuIds": menuIds,
                                },
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
                    }
                })
            });

            $.ajax({
                url: '${pageContext.request.contextPath}/system/menu/query/all',
                dataType: 'json',
                success: function(res){
                    menuList = res.data.list;
                    // 支持自定义递归字段、数组权限判断等
                    // 深坑注意：如果API返回的数据是字符串，那么 startPid 的数据类型也需要是字符串
                    var trees = authtree.listConvert(res.data.list, {
                        primaryKey: 'id'
                        ,startPid: '0'
                        ,parentKey: 'pid'
                        ,nameKey: 'name'
                        ,valueKey: 'id'
                    });
                    // 如果页面中多个树共存，需要注意 layfilter 需要不一样
                    authtree.render('#menus', trees, {
                        inputname: 'authids[]',
                        layfilter: 'lay-check-convert-auth',
                        openall: true,
                        autowidth: true,
                        autoclose: false
                    });

                    form.on('submit(checkAll)', function(obj){
                        authtree.checkAll('#menus');
                        return false;
                    });

                    form.on('submit(uncheckAll)', function(obj){
                        authtree.uncheckAll('#menus');
                        return false;
                    });
                },
                error: function(xml, errstr, err) {
                    layer.alert('菜单获取失败');
                }
            });
        });
    </script>
</body>
</html>