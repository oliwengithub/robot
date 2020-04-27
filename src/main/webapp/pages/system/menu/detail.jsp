<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>菜单详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
</head>
<body>
  <div class="layui-fluid">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -10px;">
          <legend>菜单详情 - ${menu.name}</legend>
        </fieldset>
        <form class="layui-form" lay-filter="" action="">
            <input type="hidden" name="id" value="${menu.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">菜单名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="required" maxlength="30" value="${menu.name}" readonly>
                </div>
            </div>
            <div class="layui-form-item" id="menu-tree">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请求链接</label>
                <div class="layui-input-block">
                   <input type="url" name="url" autocomplete="off" class="layui-input" maxlength="50" value="${menu.url}" readonly  placeholder="多个菜单用英文逗号分开，如：url1,url2">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图标</label>
                <div class="layui-input-block">
                    <input type="text" name="icon" id="iconPicker" lay-filter="iconPicker" class="layui-input" value="${menu.icon}">
                </div>
            </div>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" disabled value="0" title="菜单" <c:if test="${menu.type == 0}">checked</c:if>>
                    <input type="radio" name="type" disabled value="1" title="按钮" <c:if test="${menu.type == 1}">checked</c:if>>
                    <input type="radio" name="type" disabled value="2" title="操作" <c:if test="${menu.type == 2}">checked</c:if>>
                </div>
            </div>
            <div class="layui-form-item <c:if test="${menu.type != 1}">hidden</c:if>" id="tag">
                <label class="layui-form-label">操作TAG</label>
                <div class="layui-input-inline">
                    <input type="text" name="tag" autocomplete="off" class="layui-input" maxlength="50" value="${menu.tag}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-block">
                    <input type="number" name="sort" autocomplete="off" class="layui-input" value="${menu.sort}" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">添加人</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${menu.adminName}" autocomplete="off" class="layui-input" readonly>
                    </div>
                </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">添加时间</label>
                    <div class="layui-input-inline">
                        <input type="text" value="<fmt:formatDate value="${menu.createTime}" pattern="yyyy年MM月dd日" />" autocomplete="off" class="layui-input" readonly>
                    </div>
                </div>
            </div>

        </form>
    </div>
  </div>
  <script type="text/html" id="LAY-auth-tree-convert-select">
    <label class="layui-form-label">父级菜单</label>
    <div class="layui-input-block" id="menu-list">
        <select name="pid" class="layui-input" lay-filter="{{d.layFilter}}">
        {{# layui.each(d.list, function(index, item) { }}
        <option value="{{item.value}}" title="{{item.name}}" {{item.value == d.checkValue?'selected':''}} {{item.disabled?'disabled':''}}>{{item.name}}</option>
        {{# });}}
        </select>
    </div>
  </script>
  <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
  <%@ include file="../layuiconfig.jsp"%>
  <script type="application/javascript">
    layui.config({
    base: '${pageContext.request.contextPath}/' //静态资源所在路径根目录
    }).extend({
        authtree: "js/authtree",
        iconPicker: "js/iconPicker"
    });

    layui.use(['authtree','laytpl', 'form', 'iconPicker'], function(){
        var authtree = layui.authtree
        ,laytpl = layui.laytpl
        ,form = layui.form
        ,iconPicker = layui.iconPicker;

        iconPicker.render({
            // 选择器，推荐使用input
            elem: '#iconPicker',
            // 数据类型：fontClass/unicode，推荐使用fontClass
            type: 'fontClass',
            // 是否开启搜索：true/false
            search: true,
            // 是否开启分页
            page: false,
            // 点击回调
            click: function (data) {
            },
            // 渲染成功后的回调
            success: function(d) {
            }
        });

        iconPicker.checkIcon('iconPicker', '${menu.icon}');

        $.ajax({
            url: '${pageContext.request.contextPath}/system/menu/query/all?type=0',
            dataType: 'json',
            success: function(res){
                //将菜单列表转换成菜单树
                var trees = authtree.listConvert(res.data.list, {
                    primaryKey: 'id'
                    ,startPid: '-1'
                    ,parentKey: 'pid'
                    ,nameKey: 'name'
                    ,valueKey: 'id'
                    ,checkedKey: res.data.checkedAlias
                    ,disabledKey: res.data.disabledAlias
                });
                //将树转为单选可用的 select
                var selectList = authtree.treeConvertSelect(trees, {
                childKey: 'list',
                });
                //将数据放入layui模版
                var string =  laytpl($('#LAY-auth-tree-convert-select').html()).render({
                    // 为了 layFilter 的唯一性，使用模板渲染的方式传递
                    layFilter: 'LAY-auth-tree-convert-select-input',
                    list: selectList,
                    checkValue: '${menu.pid}'
                });
                //将模版放到html中
                $('#menu-tree').html(string);
                //刷新form
                form.render('select');
                //去掉input的特殊字符
                $("#menu-list input").val($("#menu-list input").val().replace().replace(/ /g,'').replace(/　/g,'').replace(/▼/g,''));
                form.on("select", function(data){
                    var value = data.elem[data.elem.selectedIndex].title;
                    $("#menu-list input").val(value.replace().replace(/ /g,'').replace(/　/g,'').replace(/▼/g,''));
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