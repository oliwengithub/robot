<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>菜单列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
</head>
<body>
    <!-- 内容 内边距-->
    <div class="layui-fluid">
        <!-- 白色背景-->
        <div class="layui-card">
            <!-- 内边距-->
            <div class="layui-card-body">
                <!-- 表单-->
                <form class="layui-form table-form">
                    <div class="layui-form-item">
                        <div class="layui-col-md2 layui-col-xs2">
                            <div class="layui-inline">
                                <div class="layui-input-inline layui-button-inline">
                                    <c:if test="${fn:contains(currMenuChildrenTags, 'add')}">
                                    <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="add">添加菜单</button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md9 layui-col-xs9 layui-right">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" name="name" placeholder="菜单名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline layui-hide-xs layui-hide-sm layui-show-md-inline">
                                    <input type="text" name="pName" placeholder="父菜单名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline layui-hide-xs">
                                    <select name="type">
                                        <option value="">选择类型</option>
                                        <option value="0">菜单</option>
                                        <option value="1">按钮</option>
                                        <option value="2">操作</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="orderBy" placeholder="排序" autocomplete="off" class="layui-input">
                        <div class="layui-col-md1 layui-col-xs1 layui-right">
                            <button class="layui-btn" lay-submit="" lay-filter="search">查询</button>
                            <button class="layui-btn hidden" lay-submit="" lay-filter="searchSelf">刷新当前表格</button>
                        </div>
                    </div>
                </form>
                <!-- 表格数据-->
                <table id="layui-table" lay-filter="layui-table"></table>
            </div>
        </div>
    </div>

    <script type="text/html" id="typeTpl">
        {{#  if(d.type == 0){ }}
        <button class="layui-btn layui-btn-primary layui-btn-xs">菜单</button>
        {{# } else if(d.type == 1) { }}
        <button class="layui-btn layui-btn-normal layui-btn-xs">按钮</button>
        {{#  } else { }}
        <button class="layui-btn layui-btn-warm layui-btn-xs">操作</button>
        {{#  } }}
    </script>
    <script type="text/html" id="iconTpl">
        {{#  if(d.icon != null && d.icon != ''){ }}
        <i class="layui-icon {{d.icon}}"></i>
        {{#  } else { }}

        {{#  } }}
    </script>
    <script type="text/html" id="toolbar">
        <c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
        <button class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</button>
        </c:if>
        <c:if test="${fn:contains(currMenuChildrenTags, 'delete')}">
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
        </c:if>
    </script>

    <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
    <%@ include file="../layuiconfig.jsp"%>
    <script type="application/javascript">
      var sortObj = null;//记录初始排序，如果不设的话，将无法标记表头的排序状态。
      layui.use('table', function(){
          var table = layui.table
            ,form = layui.form;

          table.render({
              id: 'layui-table'
              ,elem: '#layui-table'
              ,url:'${pageContext.request.contextPath}/system/menu/list/query'
              ,request: {
                  pageName: 'pageIndex' //页码的参数名称，默认：page
                  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
              }
              ,height: 'full-100'
              ,cellMinWidth: 80
              ,toolbar: "true" //导出/打印等工具条
              ,cols: [[
                   {field:'id', title: '序号', width:60, align: 'center',type: 'numbers'}
                  ,{field:'name', title: '菜单名称', width:150}
                  ,{field:'icon', title: '图标',  width: 80,align: 'center',templet: '#iconTpl'}
                  ,{field:'pid', title: '父菜单ID', align: 'center',width:120,sort: true}
                  ,{field:'url', title: '请求url', minWidth:150}
                  ,{field:'type', title: '类型', width:80, align: 'center',templet: '#typeTpl'}
                  ,{field:'tag', title: '权限标识', width:120}
                  ,{field:'sort', title: '排序', width:80,align: 'center',sort: true}
                  ,{title:'操作', width:150,  align: 'center', toolbar: '#toolbar'}
              ]]
              ,page: true
          });
          table.on('sort(layui-table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            if(obj.type != null && $("input[name=orderBy]").val() != obj.field + ' ' + obj.type){
                $("input[name=orderBy]").val(obj.field + ' ' + obj.type);
                sortObj = obj;
                $("button[lay-filter=search]").click();
            }
          });
          //表格操作事件
          table.on('tool(layui-table)', function(obj){
              var data = obj.data;
              if(obj.event === 'del'){
                  layer.confirm('真的删除此菜单么', function(index){
                    $.ajax({
                        type: 'POST',
                        url: '${pageContext.request.contextPath}/system/menu/update',
                        data: {
                            "id": data.id,
                            "status": "1"
                        },
                        success: function (result) {
                            layer.close(index);
                            if(result.code === 1){
                                layer.msg(result.msg,{time: 1000});
                            }else{
                                layer.msg('成功',{time: 1000},function() {
                                    obj.del();
                                });
                            }
                        }
                    });
                  });
              } else if(obj.event === 'detail'){
                  layer.open({
                      type: 2
                      ,anim: 4
                      ,shadeClose: true
                      ,title: '菜单详情'
                      ,area: ['480px', '470px']
                      ,maxmin: true
                      ,content: '${pageContext.request.contextPath}/system/menu/detail?id='+data.id
                  });
              } else if(obj.event === 'edit'){
                  layer.open({
                      type: 2
                      ,anim: 4
                      ,shadeClose: true
                      ,title: '菜单编辑'
                      ,area: ['480px', '470px']
                      ,maxmin: true
                      ,content: '${pageContext.request.contextPath}/system/menu/edit?id='+data.id
                      ,end: function () {
                          //触发刷新当页表格
                         $("button[lay-filter=searchSelf]").click();
                      }
                  });
              }
          });

          //查询提交事件
          form.on('submit(search)', function(data){
            //表格重载
            var data = {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: data.field
            };
            if(sortObj != null){
                data['initSort'] = sortObj;
            }
            table.reload('layui-table', data);
            return false;
          });

          //刷新当页表格
          form.on('submit(searchSelf)', function(data){
            //表格重载
            var data = {
                where: data.field
            };
            //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            if(sortObj != null){
                data['initSort'] = sortObj;
            }
            table.reload('layui-table', data);
            return false;
          });

          form.on('submit(add)', function(data){
            layer.open({
                type: 2
                ,anim: 4
                ,title: '添加菜单'
                ,area: ['450px', '470px']
                ,maxmin: true
                ,content: '${pageContext.request.contextPath}/system/menu/add'
                ,end: function () {
                    //触发刷新当页表格
                    $("button[lay-filter=searchSelf]").click();
                }
            });
            return false;
          });
      });
    </script>
</body>
</html>